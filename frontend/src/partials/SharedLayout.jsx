/* eslint-disable react/prop-types */
import { Outlet } from "react-router-dom";
import Container from "@mui/material/Container";
import NavBar from "./NavBar";

const SharedLayout = () => {
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (loggedInUser) {
    return (
      <>
        <NavBar />
        <Container fixed>
          <Outlet />
        </Container>
      </>
    );
  }
};

export default SharedLayout;
