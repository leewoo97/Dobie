import axios from "axios";
// const dockerUrl = "http://localhost:8080/api/dockerfile";
const dockerUrl = process.env.REACT_APP_SERVER + "/dockerfile";
// const dockerUrl = "https://api.silvstone.xyz/api/dockerfile";

//도커컴포즈 파일 조회
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

//도커파일 조회
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
