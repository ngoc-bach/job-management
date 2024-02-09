/* eslint-disable react/prop-types */
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { Divider } from "@mui/material";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import Stack from "@mui/material/Stack";
import { useNavigate } from "react-router-dom";
import { deleteJob } from "../services/JobService";

const JobCard = ({ job, user, bearer }) => {
  const { id, position, company, description, location, status } = job;
  const navigate = useNavigate();

  const handelDeleteJob = async (id) => {
    const foundJobId = id;
    await deleteJob(user.id, foundJobId, bearer);
    navigate("/all-jobs");
  };

  return (
    <Card sx={{ maxWidth: 450 }}>
      <CardContent>
        <Typography gutterBottom variant="h6" component="div">
          {position}
        </Typography>
        <Typography variant="subtitle1" color="text.secondary">
          {company}
        </Typography>
        <Divider />
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
      </CardContent>
      <CardActions>
        <Button size="small" onClick={() => handelDeleteJob(id)}>
          Delete
        </Button>
        <Button size="small" onClick={() => handleUpdateJob(id)}>
          Edit
        </Button>
      </CardActions>
    </Card>
  );
};

export default JobCard;
