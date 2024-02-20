import axios from "axios";

const BASE_URL = "http://localhost:8088/api";

export const updateUser = async (bearer, userId, user) => {
  try {
    const requestOptions = {
      headers: {
        Authorization: bearer,
      },
    };
    const response = await axios.put(
      `${BASE_URL}/users`,
      { ...user, id: userId },
      requestOptions
    );
    return response.status;
  } catch (error) {
    console.error("Error updating data:", error);
    return error.response.status;
  }
};
