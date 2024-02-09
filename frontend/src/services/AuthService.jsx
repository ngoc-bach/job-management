import axios from "axios";

const BASE_URL = "http://localhost:8088/api";

export const getBearerToken = async (requestOptions) => {
  try {
    const response = await axios.post(
      `${BASE_URL}/auth/login`,
      {},
      requestOptions
    );
    return `Bearer ${response.data}`;
  } catch (error) {
    console.log("Error getting bearer token:", error);
  }
};

export const getUser = async (requestOptions) => {
  try {
    const response = await axios.get(`${BASE_URL}/user`, requestOptions);
    return response.data;
  } catch (error) {
    console.log("Error getting username:", error);
  }
};
