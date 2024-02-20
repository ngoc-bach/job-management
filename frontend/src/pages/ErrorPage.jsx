/* eslint-disable react/prop-types */
import { Link } from "react-router-dom";
import Grid from "@mui/material/Grid";
import errorImage from "../assets/error-image.svg";

const ErrorPage = () => {
  return (
    <Grid container>
      <Grid
        item
        xs={12}
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          mt: "2rem",
        }}
      >
        <Link variant="h6" to={"/landing"}>
          Back to Home
        </Link>
      </Grid>
      <Grid
        item
        xs={12}
        sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}
      >
        <img src={errorImage} alt="error-image" width="50%"></img>;{" "}
      </Grid>
    </Grid>
  );
};

export default ErrorPage;
