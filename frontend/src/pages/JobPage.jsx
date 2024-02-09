/* eslint-disable react/prop-types */
import Grid from "@mui/material/Unstable_Grid2";

import { useState, useEffect } from "react";
import { useOutletContext } from "react-router-dom";
import JobCard from "../components/JobCard";
import { fetchJobs } from "../services/JobService";

const JobPage = ({ bearer }) => {
  const [jobs, setJobs] = useState([]);
  const [user, setUser] = useOutletContext();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const allJobs = await fetchJobs(bearer, user.id);
        setJobs(allJobs);
      } catch (error) {
        console.error("Error fetching jobs:", error);
      }
    };
    fetchData();
  }, [jobs]);

  return (
    <>
      <Grid
        container
        spacing={{ xs: 2, md: 3 }}
        columns={{ xs: 4, sm: 8, md: 12 }}
        style={{ margin: "2rem" }}
      >
        {jobs.map((job, index) => {
          return (
            <Grid xs={6} sm={4} md={4} key={index}>
              <JobCard key={job.id} job={job} user={user} bearer={bearer} />
            </Grid>
          );
        })}
      </Grid>
    </>
  );
};

export default JobPage;
