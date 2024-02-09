import { Routes, Route } from "react-router-dom";
import { useState, useEffect } from "react";
import LandingPage from "./pages/LandingPage";
import JobPage from "./pages/JobPage";
import StatPage from "./pages/StatPage";
import SharedLayout from "./partials/SharedLayout";
import AddJobPage from "./pages/AddJobPage";
import RegisterPage from "./pages/RegisterPage";
import ErrorPage from "./pages/ErrorPage";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";

function App() {
  const [bearer, setBearer] = useState("");

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
        <Route path="/" element={<SharedLayout bearer={bearer} />}>
          <Route index element={<StatPage />} />
          <Route path="/all-jobs" element={<JobPage bearer={bearer} />} />
          <Route path="/add-job" element={<AddJobPage bearer={bearer} />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="*" element={<ErrorPage />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
