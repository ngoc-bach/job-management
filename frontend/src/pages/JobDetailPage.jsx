/* eslint-disable react/prop-types */
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Stack from "@mui/material/Stack";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import DescriptionIcon from "@mui/icons-material/Description";
import Chip from "@mui/material/Chip";
import DoneIcon from "@mui/icons-material/Done";
import Button from "@mui/material/Button";
import CardActions from "@mui/material/CardActions";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { getJob, applyJob, deleteJob } from "../services/JobService";

const JobDetailPage = () => {
  const bearer = sessionStorage.getItem("token");
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  const [job, setJob] = useState({});
  const { jobId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    fetchJob();
  }, []);

  const fetchJob = async () => {
    try {
      const foundJob = await getJob(loggedInUser.id, jobId, bearer);
      setJob(foundJob);
    } catch (error) {
      console.log("Error getting job:", error);
    }
  };

  const handleDeleteJob = async (jobId) => {
    await deleteJob(loggedInUser.id, jobId, bearer);
    fetchJob();
    navigate("/all-jobs");
  };

  const handleApplyJob = async (job) => {
    await applyJob(loggedInUser.id, jobId, job, bearer);
    fetchJob();
  };
  return (
    <Box sx={{ width: "100%", margin: "2rem" }}>
      <Typography variant="h3" gutterBottom>
        {job.position}
      </Typography>
      <Typography variant="h5" color="text.secondary">
        {job.company}
      </Typography>
      <Stack
        direction="row"
        spacing={1}
        alignItems="center"
        style={{ marginTop: "1rem" }}
      >
        <LocationOnIcon color="action" />
        <Typography variant="subtitle1" color="text.secondary">
          {job.location}
        </Typography>
      </Stack>
      <Stack
        direction="row"
        spacing={1}
        alignItems="center"
        style={{ marginTop: "1rem" }}
      >
        <CalendarMonthIcon color="action" />
        <Typography variant="subtitle1" color="text.secondary">
          Posted on: {job.createdDate}
        </Typography>
      </Stack>
      <Stack
        direction="row"
        spacing={1}
        alignItems="center"
        style={{ marginTop: "1rem" }}
      >
        <DescriptionIcon color="action" />
        <Typography variant="subtitle1" color="text.secondary">
          Description
        </Typography>
      </Stack>
      <Typography variant="body1" gutterBottom sx={{ mt: "0.7rem" }}>
        {job.description}
      </Typography>
      {loggedInUser.role === "admin" ? (
        <CardActions>
          <Button
            size="large"
            variant="outlined"
            startIcon={<DeleteIcon />}
            onClick={() => handleDeleteJob(job.id)}
            disabled={job.editable ? false : true}
          >
            Delete
          </Button>
          <Button
            size="large"
            variant="contained"
            endIcon={<EditIcon />}
            onClick={() => navigate(`/edit-job/${jobId}`)}
            disabled={job.editable ? false : true}
          >
            Edit
          </Button>
        </CardActions>
      ) : (
        <CardActions>
          {job.hasApplied ? (
            <Chip label="APPLIED" icon={<DoneIcon />} />
          ) : (
            <Button
              disabled={job.status === "closed" ? true : false}
              variant="contained"
              size="large"
              onClick={() => handleApplyJob(job)}
            >
              Apply
            </Button>
          )}
        </CardActions>
      )}
    </Box>
  );
};

export default JobDetailPage;
