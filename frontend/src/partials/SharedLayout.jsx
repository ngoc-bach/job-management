/* eslint-disable react/prop-types */
import { Outlet } from "react-router-dom";
import Container from "@mui/material/Container";
import NavBar from "./NavBar";

const SharedLayout = () => {
  return (
    <>
      <NavBar />
      <Container fixed>
        <Outlet />
      </Container>
    </>
  );
};

export default SharedLayout;
