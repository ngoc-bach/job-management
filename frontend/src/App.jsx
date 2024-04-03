import { Routes, Route } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import LandingPage from "./pages/LandingPage";
import JobListPage from "./pages/JobListPage";
import StatPage from "./pages/StatPage";
import SharedLayout from "./partials/SharedLayout";
import AddJobPage from "./pages/AddJobPage";
import RegisterPage from "./pages/RegisterPage";
import ErrorPage from "./pages/ErrorPage";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";
import JobDetailPage from "./pages/JobDetailPage";
import StatPageAdmin from "./pages/StatPageAdmin";
import EditJobPage from "./pages/EditJobPage";

function App() {
  // const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  const loggedInUserJSON = sessionStorage.getItem("loggedInUser");
  let loggedInUser = null;

  if (loggedInUserJSON) {
    try {
      loggedInUser = JSON.parse(loggedInUserJSON);
    } catch (error) {
      console.error("Error parsing JSON:", error);
    }
  }

  const [jobs, setJobs] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!loggedInUser) {
      navigate("/landing");
    }
  }, [loggedInUser]);

  return (
    <>
      <Routes>
        <Route path="/landing" element={<LandingPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/" element={<SharedLayout />}>
          {loggedInUser && loggedInUser.role === "admin" && (
            <Route index element={<StatPageAdmin jobs={jobs} />} />
          )}
          {loggedInUser && loggedInUser.role === "trainee" && (
            <Route index element={<StatPage />} />
          )}
          <Route
            path="/all-jobs"
            element={<JobListPage jobs={[jobs, setJobs]} />}
          />
          <Route path="/all-jobs/:jobId" element={<JobDetailPage />} />
          {loggedInUser && loggedInUser.role === "admin" && (
            <>
              <Route path="/add-job" element={<AddJobPage />} />
              <Route
                path="/edit-job/:jobId"
                element={<EditJobPage jobs={jobs} />}
              />
            </>
          )}

          <Route path="/profile" element={<ProfilePage />} />
        </Route>

        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </>
  );
}

export default App;
