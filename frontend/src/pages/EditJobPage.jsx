/* eslint-disable react/prop-types */
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import PostAddIcon from "@mui/icons-material/PostAdd";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import { MenuItem } from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useState } from "react";
import { editJob } from "../services/JobService";
import { useParams } from "react-router-dom";

const defaultTheme = createTheme();

const EditJobPage = ({ bearer, user, jobs }) => {
  const { jobId } = useParams();
  const currentJob = jobs.find((job) => job.id === Number(jobId));
  const [position, setPosition] = useState(currentJob.position);
  const [company, setCompany] = useState(currentJob.company);
  const [location, setLocation] = useState(currentJob.location);
  const [status, setSatus] = useState(currentJob.status);
  const [description, setDescription] = useState(currentJob.description);
  const [isSuccess, setIsSuccess] = useState(false);
  const [openMessage, setOpenMessage] = useState(false);
  const [message, setMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    const job = { position, company, location, status, description };
    const userId = user.id;
    const result = await editJob(userId, jobId, job, bearer);
    if (result === 200) {
      setIsSuccess(true);
      setOpenMessage(true);
      setMessage("Successfully updated");
    } else {
      setIsSuccess(false);
      setOpenMessage(true);
      setMessage("Update failed.Please try again!");
    }
  };
  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenMessage(false);
  };
  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="md">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <PostAddIcon />
          </Avatar>

          <Typography component="h1" variant="h5">
            Edit Job
          </Typography>

          <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={6}>
                <TextField
                  required
                  fullWidth
                  name="position"
                  label="Position"
                  type="text"
                  id="position"
                  value={position}
                  onChange={(e) => setPosition(e.target.value)}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  required
                  fullWidth
                  name="company"
                  label="Company"
                  type="text"
                  id="company"
                  value={company}
                  onChange={(e) => setCompany(e.target.value)}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  required
                  fullWidth
                  name="location"
                  label="Location"
                  type="text"
                  id="location"
                  value={location}
                  onChange={(e) => setLocation(e.target.value)}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  select
                  required
                  fullWidth
                  label="Job Status"
                  id="job-status"
                  defaultValue="opening"
                  value={status}
                  onChange={(e) => setSatus(e.target.value)}
                >
                  {["opening", "closed"].map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  multiline
                  name="description"
                  label="Description"
                  type="text"
                  id="description"
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Submit
            </Button>
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
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default EditJobPage;
