import { Routes, Route } from "react-router-dom";
import { useState } from "react";
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
  const bearer = sessionStorage.getItem("token");
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  const [jobs, setJobs] = useState([]);

  return (
    <>
      <Routes>
        <Route path="/landing" element={<LandingPage />} />
        <Route path="/register" element={<RegisterPage />} />
        {!bearer && <Route path="/login" element={<LoginPage />} />}
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
            <Route path="/add-job" element={<AddJobPage />} />
          )}
          <Route
            path="/edit-job/:jobId"
            element={<EditJobPage jobs={jobs} />}
          />
          <Route path="/profile" element={<ProfilePage />} />
        </Route>

        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </>
  );
}

export default App;
