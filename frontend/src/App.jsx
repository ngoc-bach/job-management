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
  const [bearer, setBearer] = useState("");
  const [user, setUser] = useState({});
  const [jobs, setJobs] = useState([]);

  return (
    <>
      <Routes>
        <Route path="/landing" element={<LandingPage />} />
        <Route path="/register" element={<RegisterPage />} />
        {bearer === "" && (
          <Route
            path="/login"
            element={<LoginPage bearer={[bearer, setBearer]} />}
          />
        )}
        {bearer && (
          <Route
            path="/"
            element={<SharedLayout bearer={bearer} user={[user, setUser]} />}
          >
            {user.role === "admin" && (
              <Route
                index
                element={
                  <StatPageAdmin bearer={bearer} user={user} jobs={jobs} />
                }
              />
            )}
            {user.role === "trainee" && (
              <Route index element={<StatPage bearer={bearer} user={user} />} />
            )}
            <Route
              path="/all-jobs"
              element={
                <JobListPage
                  bearer={bearer}
                  user={user}
                  jobs={[jobs, setJobs]}
                />
              }
            />
            <Route
              path="/all-jobs/:jobId"
              element={<JobDetailPage bearer={bearer} user={user} />}
            />
            {user.role === "admin" && (
              <Route
                path="/add-job"
                element={<AddJobPage bearer={bearer} user={user} />}
              />
            )}
            <Route
              path="/edit-job/:jobId"
              element={<EditJobPage bearer={bearer} user={user} jobs={jobs} />}
            />
            <Route
              path="/profile"
              element={<ProfilePage bearer={bearer} user={user} />}
            />
          </Route>
        )}
        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </>
  );
}

export default App;
