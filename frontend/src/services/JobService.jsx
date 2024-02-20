import axios from "axios";

const BASE_URL = "http://localhost:8088/api";

export const fetchJobs = async (userId, bearer) => {
  try {
    const response = await axios.get(`${BASE_URL}/jobs`, {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching jobs:", error);
  }
};

export const getJob = async (userId, jobId, bearer) => {
  try {
    const requestOptions = {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    };
    const response = await axios.get(
      `${BASE_URL}/jobs/${jobId}`,
      requestOptions
    );
    return response.data;
  } catch (error) {
    console.error("Error getting job:", error);
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
    const response = await axios.post(`${BASE_URL}/jobs`, job, requestOptions);
    return response.status;
  } catch (error) {
    return error.response.status;
  }
};

export const editJob = async (userId, jobId, job, bearer) => {
  try {
    const requestOptions = {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    };
    const response = await axios.put(
      `${BASE_URL}/jobs`,
      { ...job, id: jobId },
      requestOptions
    );
    return response.status;
  } catch (error) {
    return error.response.status;
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
    const response = await axios.delete(
      `${BASE_URL}/jobs/${jobId}`,
      requestOptions
    );
    return response.status;
  } catch (error) {
    console.error("Error deleting data:", error);
    return error.response.status;
  }
};

export const applyJob = async (userId, jobId, job, bearer) => {
  try {
    const requestOptions = {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    };
    await axios.put(
      `${BASE_URL}/jobs/apply`,
      { ...job, id: jobId },
      requestOptions
    );
  } catch (error) {
    console.error("Error applying data:", error);
  }
};

export const fetchStats = async (bearer, userId) => {
  try {
    const requestOptions = {
      headers: {
        user_id: userId,
        Authorization: bearer,
      },
    };
    const response = await axios.get(`${BASE_URL}/jobs/stat`, requestOptions);
    return response.data;
  } catch (error) {
    console.log("Error deleting data:", error);
  }
};

export const searchByPosition = async (bearer, keyword) => {
  try {
    const requestOptions = {
      headers: {
        Authorization: bearer,
      },
      params: {
        q: keyword,
      },
    };
    const response = await axios.get(`${BASE_URL}/jobs/search`, requestOptions);
    return response.data;
  } catch (error) {
    console.log("Error searching data:", error);
  }
};
