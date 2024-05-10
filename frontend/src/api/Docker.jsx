import axios from "axios";
// const dockerUrl = "http://localhost:8080/api/dockerfile";
// const dockerUrl = process.env.REACT_APP_SERVER + "/dockerfile";
// const dockerUrl = "https://api.silvstone.xyz/api/dockerfile";
const dockerUrl = "http://3.38.208.235:8010/api/containercheck";

export async function getDockerCompose(projectId) {
  try {
    const response = await axios.get(
      dockerUrl + "/docker-compose-file-content",
      { params: { projectId } }
    );
    console.log(response);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function getDockerFile(projectId, serviceId, type) {
  try {
    const response = await axios.get(dockerUrl + "/dockerfile-content", {
      params: { projectId, serviceId, type },
    });
    console.log(response);
    return response;
  } catch (error) {
    throw error;
  }
}
