/* eslint-disable react/prop-types */
import { Outlet } from "react-router-dom";
import Container from "@mui/material/Container";
import { useEffect, useState } from "react";
import { getUser } from "../services/AuthService";
import NavBar from "./NavBar";

const SharedLayout = (props) => {
  const { bearer } = props;
  const [user, setUser] = useState({});

  useEffect(() => {
    const fetchUser = async () => {
      const requestOptions = {
        headers: {
          Authorization: bearer,
        },
      };
      const foundUser = await getUser(requestOptions);
      setUser(foundUser);
    };
    fetchUser();
  }, [bearer]);

  return (
    <>
      <NavBar user={user} />
      <Container fixed>
        <Outlet context={[user, setUser]} />
      </Container>
    </>
  );
};

export default SharedLayout;
