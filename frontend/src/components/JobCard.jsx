/* eslint-disable react/prop-types */
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { Divider } from "@mui/material";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import PersonIcon from "@mui/icons-material/Person";
import Chip from "@mui/material/Chip";
import Stack from "@mui/material/Stack";
import DoneIcon from "@mui/icons-material/Done";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

const JobCard = ({ job, user, deleteJob, applyJob }) => {
  const {
    id,
    position,
    company,
    location,
    status,
    createdDate,
    hasApplied,
    editable,
  } = job;
  const navigate = useNavigate();

  return (
    <Card sx={{ maxWidth: 500, minWidth: 320 }}>
      <Link to={`/all-jobs/${id}`}>
        <CardContent>
          <Typography
            gutterBottom
            variant="h6"
            component="div"
            sx={{ color: "#1976d2" }}
          >
            {position}
          </Typography>
          <Typography variant="subtitle1" color="text.secondary">
            {company}
          </Typography>
          <Divider />
          <Stack direction="row" spacing={4} justifyContent={"space-between"}>
            <Stack
              direction="row"
              spacing={1}
              alignItems="center"
              style={{ marginTop: "1rem" }}
            >
              <LocationOnIcon color="action" />
              <Typography variant="body2" color="text.secondary">
                {location}
              </Typography>
            </Stack>
            <Stack
              direction="row"
              spacing={1}
              alignItems="center"
              style={{ marginTop: "1rem" }}
            >
              <CalendarMonthIcon color="action" />
              <Typography variant="body2" color="text.secondary">
                {createdDate}
              </Typography>
            </Stack>
          </Stack>
          <Stack
            direction="row"
            spacing={4}
            justifyContent={"space-between"}
            alignItems={"center"}
          >
            <Stack
              direction="row"
              spacing={1}
              alignItems="center"
              style={{ marginTop: "1rem" }}
            >
              <PersonIcon color="action" />
              <Typography variant="body2" color="text.secondary">
                Posted By{" "}
                {job.accountManager.user.id === user.id
                  ? "Me"
                  : job.accountManager.firstName}
              </Typography>
            </Stack>
            <Chip
              label={job.status}
              color={job.status === "opening" ? "primary" : "error"}
              variant="outlined"
            />
          </Stack>
        </CardContent>
      </Link>
      {user.role === "admin" ? (
        <CardActions>
          <Button
            size="small"
            onClick={() => deleteJob(id)}
            disabled={editable ? false : true}
          >
            Delete
          </Button>
          <Button
            size="small"
            onClick={() => navigate(`/edit-job/${id}`)}
            disabled={editable ? false : true}
          >
            Edit
          </Button>
        </CardActions>
      ) : (
        <CardActions>
          {hasApplied ? (
            <Chip label="APPLIED" icon={<DoneIcon />} />
          ) : (
            <Button
              disabled={status === "closed" ? true : false}
              variant="contained"
              size="small"
              onClick={() => applyJob(id, job)}
            >
              Apply
            </Button>
          )}
        </CardActions>
      )}
    </Card>
  );
};

export default JobCard;
