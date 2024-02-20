/* eslint-disable react/prop-types */
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Link from "@mui/material/Link";

import { fetchStats } from "../services/JobService";
import { useEffect } from "react";
import { useState } from "react";
import { Container } from "@mui/material";
import { useNavigate } from "react-router-dom";

const createData = (position, company, location, status, id) => {
  return { position, company, location, status, id };
};

const StatPageAdmin = ({ bearer, user, jobs }) => {
  const [statData, setStatData] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchStats(bearer, user.id);
        setStatData(data);
      } catch (error) {
        console.error("Error fetching job stat:", error);
      }
    };
    fetchData();
  }, []);

  const rows = statData.map((job) =>
    createData(job.position, job.company, job.location, job.status, job.id)
  );

  return (
    <Container>
      <Typography
        gutterBottom
        variant="h5"
        component="div"
        sx={{ margin: "2rem" }}
      >
        Posted: {statData.length} job(s)
      </Typography>

      <TableContainer component={Paper} sx={{ mt: "2rem" }}>
        <Table sx={{ minWidth: 500 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>POSITION</TableCell>
              <TableCell align="right">COMPANY NAME</TableCell>
              <TableCell align="right">LOCATION</TableCell>
              <TableCell align="right">STATUS</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow
                key={row.id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {row.position}
                </TableCell>
                <TableCell align="right">{row.company}</TableCell>
                <TableCell align="right">{row.location}</TableCell>
                {row.status === "opening" ? (
                  <TableCell align="right">{row.status}</TableCell>
                ) : (
                  <TableCell align="right" sx={{ color: "red" }}>
                    {row.status}
                  </TableCell>
                )}

                <TableCell align="right">
                  {jobs.length > 0 && (
                    <Link
                      onClick={() => navigate(`/all-jobs/${row.id}`)}
                      component="button"
                      variant="body2"
                    >
                      Detail
                    </Link>
                  )}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Container>
  );
};

export default StatPageAdmin;
