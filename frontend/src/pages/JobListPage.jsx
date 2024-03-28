/* eslint-disable react/prop-types */
import Grid from "@mui/material/Unstable_Grid2";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";

import { useState, useEffect } from "react";
import JobCard from "../components/JobCard";
import {
  applyJob,
  deleteJob,
  fetchJobs,
  searchByPosition,
} from "../services/JobService";
import SearchForm from "../components/SearchForm";
import { Typography } from "@mui/material";

const JobListPage = (props) => {
  const bearer = sessionStorage.getItem("token");
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  const [jobs, setJobs] = props.jobs;
  const [keyword, setKeyword] = useState("");
  const [isSuccess, setIsSuccess] = useState(false);
  const [openMessage, setOpenMessage] = useState(false);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const allJobs = await fetchJobs(loggedInUser.id, bearer);
      setJobs(allJobs);
    } catch (error) {
      console.error("Error fetching jobs:", error);
    }
  };

  const handleDeleteJob = async (jobId) => {
    const result = await deleteJob(user.id, jobId, bearer);
    if (result === 200) {
      setIsSuccess(true);
      setOpenMessage(true);
      setMessage("Successfully deleted");
    } else {
      setIsSuccess(false);
      setOpenMessage(true);
      setMessage("Deletion failed: Applicants have applied to this job");
    }
    fetchData();
  };

  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenMessage(false);
  };

  const handleApplyJob = async (jobId, job) => {
    await applyJob(loggedInUser.id, jobId, job, bearer);
    fetchData();
  };
  const handleSearch = async (e) => {
    e.preventDefault();
    const result = await searchByPosition(bearer, keyword);
    setJobs(result);
  };

  const handleSearchInput = (e) => {
    setKeyword(e.target.value);
  };
  return (
    <>
      <Grid
        container
        spacing={{ xs: 2, md: 3 }}
        columns={{ xs: 4, sm: 8, md: 12 }}
        sx={{ mt: "2rem" }}
      >
        <Grid xs={12} sm={12} md={12}>
          <SearchForm
            searchInput={handleSearchInput}
            handleSearch={handleSearch}
          />
        </Grid>
        <Grid xs={12} sm={12} md={12} sx={{ mt: "2rem" }}>
          <Typography variant="h6" fontWeight="bold">
            {jobs.length} Job(s) Found
          </Typography>
        </Grid>

        {jobs.map((job, index) => {
          return (
            <Grid xs={12} sm={6} md={6} key={index}>
              <JobCard
                key={job.id}
                job={job}
                user={loggedInUser}
                bearer={bearer}
                deleteJob={handleDeleteJob}
                applyJob={handleApplyJob}
              />
            </Grid>
          );
        })}
        <div>
          <Snackbar
            open={openMessage}
            autoHideDuration={3000}
            onClose={handleClose}
          >
            <Alert
              onClose={handleClose}
              severity={isSuccess ? "success" : "error"}
              variant="filled"
              sx={{ width: "100%" }}
            >
              {message}
            </Alert>
          </Snackbar>
        </div>
      </Grid>
    </>
  );
};

export default JobListPage;
