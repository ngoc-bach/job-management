/* eslint-disable no-unused-vars */
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
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { updateUser } from "../services/UserService";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { getUser } from "../services/AuthService";

const defaultTheme = createTheme();

const ProfilePage = () => {
  const bearer = sessionStorage.getItem("token");
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  const navigate = useNavigate();
  const [firstName, setFirstName] = useState(loggedInUser.firstName);
  const [lastName, setLastName] = useState(loggedInUser.lastName);
  const [email, setEmail] = useState(loggedInUser.email);
  const [location, setLocation] = useState(loggedInUser.location);
  const [username, setUsername] = useState(loggedInUser.username);
  const [password, setPassword] = useState(loggedInUser.password);
  const [role, setRole] = useState(loggedInUser.role);
  const [isSuccess, setIsSuccess] = useState(false);
  const [openMessage, setOpenMessage] = useState(false);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchUser();
  }, []);

  const fetchUser = async () => {
    const logedInUser = await getUser(bearer);
    setFirstName(logedInUser.firstName);
    setLastName(logedInUser.lastName);
    setEmail(logedInUser.email);
    setLocation(logedInUser.location);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const updatedUser = {
      firstName,
      lastName,
      email,
      location,
      username,
      password,
      role,
    };
    const userId = loggedInUser.id;
    const result = await updateUser(bearer, userId, updatedUser);
    if (result === 200) {
      setIsSuccess(true);
      setOpenMessage(true);
      setMessage("Successfully updated");
    } else {
      setIsSuccess(false);
      setOpenMessage(true);
      setMessage("Update failed.Please try again!");
    }
    fetchUser();
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
            Profile
          </Typography>
          <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={6}>
                <TextField
                  required
                  fullWidth
                  name="firstName"
                  label="FirstName"
                  type="text"
                  id="firstName"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  required
                  fullWidth
                  name="lastName"
                  label="LastName"
                  type="text"
                  id="lastName"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  required
                  fullWidth
                  name="email"
                  label="Email"
                  type="text"
                  id="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
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
              <Grid item xs={4}>
                <TextField
                  disabled
                  fullWidth
                  name="username"
                  label="Username"
                  type="text"
                  id="username"
                  value={username}
                  // onChange={(e) => setEmail(e.target.value)}
                />
              </Grid>
              <Grid item xs={4}>
                <TextField
                  disabled
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  value={password}
                />
              </Grid>
              <Grid item xs={4}>
                <TextField
                  disabled
                  fullWidth
                  name="role"
                  label="Role"
                  type="text"
                  id="role"
                  value={role}
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Save changes
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

export default ProfilePage;
