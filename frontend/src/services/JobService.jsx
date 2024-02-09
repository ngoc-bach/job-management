import axios from "axios";

const BASE_URL = "http://localhost:8088/api";

export const fetchJobs = async (bearer, userId) => {
  try {
    const response = await axios.get(`${BASE_URL}/jobs`, {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

export const addJob = async (userId, job, bearer) => {
  try {
    const requestOptions = {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    };
    await axios.post(`${BASE_URL}/jobs`, job, requestOptions);
  } catch (error) {
    console.error("Error adding data:", error);
  }
};

export const deleteJob = async (userId, jobId, bearer) => {
  try {
    const requestOptions = {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    };
    await axios.delete(`${BASE_URL}/jobs/${jobId}`, requestOptions);
  } catch (error) {
    console.error("Error deleting data:", error);
  }
};
