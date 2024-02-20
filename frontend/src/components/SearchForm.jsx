/* eslint-disable react/prop-types */
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { MenuItem } from "@mui/material";
import Paper from "@mui/material/Paper";
import { createTheme, ThemeProvider } from "@mui/material/styles";

const defaultTheme = createTheme();

const SearchForm = ({ searchInput, handleSearch }) => {
  return (
    <ThemeProvider theme={defaultTheme}>
      <Paper component="main" maxwidth="sm">
        <CssBaseline />
        <Box
          sx={{
            display: "flex",
            paddingLeft: "1rem",
            paddingBottom: "1rem",
            flexDirection: "column",
            alignItems: "flex-start",
          }}
        >
          <Box component="form" onSubmit={handleSearch} sx={{ mt: 2 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <Typography variant="h6">Search Form</Typography>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  fullWidth
                  name="searchInput"
                  label="search by position..."
                  type="text"
                  id="searchInput"
                  onChange={searchInput}
                />
              </Grid>
              <Grid item xs={4}>
                <TextField
                  select
                  fullWidth
                  label="Posted By"
                  id="job-status"
                  defaultValue="all"
                  disabled
                >
                  {["all", "Edward", "Nicholas"].map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={4}>
                <TextField
                  select
                  fullWidth
                  label="Job Status"
                  id="job-status"
                  defaultValue="all"
                  disabled
                >
                  {["all", "opening", "closed"].map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={4}>
                {" "}
                <Button type="submit" variant="contained" sx={{ mt: 1, mb: 2 }}>
                  Search
                </Button>{" "}
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Paper>
    </ThemeProvider>
  );
};

export default SearchForm;
