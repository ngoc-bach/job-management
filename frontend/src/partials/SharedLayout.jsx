/* eslint-disable react/prop-types */
import { Outlet } from "react-router-dom";
import Container from "@mui/material/Container";
import { useEffect } from "react";
import { getUser } from "../services/AuthService";
import NavBar from "./NavBar";

const SharedLayout = (props) => {
  const { bearer } = props;
  const [user, setUser] = props.user;

  useEffect(() => {
    const fetchUser = async () => {
      const logedInUser = await getUser(bearer);
      setUser(logedInUser);
    };
    fetchUser();
  }, []);

  return (
    <>
      <NavBar user={user} />
      <Container fixed>
        <Outlet />
      </Container>
    </>
  );
};

export default SharedLayout;
