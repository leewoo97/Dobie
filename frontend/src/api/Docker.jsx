import axios from "axios";
// const nginxUrl = "http://localhost:8080/api/nginx";
const nginxUrl = "https://api.silvstone.xyz/api/dockerfile";

export async function getDockerCompose(projectId) {
  try {
    const response = await axios.get(
      nginxUrl + "/docker-compose-file-content",
      { params: { projectId } }
    );
    console.log(response);
    return response;
  } catch (error) {
    throw error;
  }
}
