import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Unstable_Grid2";
import Button from "@mui/material/Button";

import { Link } from "react-router-dom";

import FDMLogo from "../assets/fdm-logo.svg";
import heroBanner from "../assets/hero-banner.svg";

function Landing() {
  return (
    <main>
      <nav>
        <AppBar position="static">
          <Container maxWidth="xl">
            <Toolbar>
              <img src={FDMLogo} alt="FDM-logo" style={{ width: "3rem" }} />
            </Toolbar>
          </Container>
        </AppBar>
      </nav>
      <section>
        <Container maxWidth="xl" style={{ padding: "1rem" }}>
          <Grid
            container
            columnSpacing={{ xs: 2, md: 10 }}
            sx={{ flexGrow: 1 }}
          >
            <Grid xs={12} md={6}>
              <h1>Placement Tracking</h1>
              <p>
                Welcome to PlacementTracker Platform, your ultimate solution for
                seamless job application management and job posting control.
                Designed with both trainees and account managers in mind
              </p>
              <Button variant="contained" size="large" id="signup_button">
                <Link to="/register" style={{ color: "white" }}>
                  Login / Register
                </Link>
              </Button>
            </Grid>
            <Grid xs={12} md={6}>
              <img alt="hero-banner" src={heroBanner} width="600px" />
            </Grid>
          </Grid>
        </Container>
      </section>
    </main>
  );
}
export default Landing;
