import axios from "axios";
const dockerUrl = process.env.REACT_APP_SERVER + "/dockerfile";
const containerUrl = process.env.REACT_APP_SERVER + "/containercheck";

//도커컴포즈 파일 조회
export async function getDockerCompose(projectId) {
  try {
    const response = await axios.get(
      dockerUrl + "/docker-compose-file-content",
      { params: { projectId } }
    );
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
    return response;
  } catch (error) {
    throw error;
  }
}

//로그보기 조회
export async function getLog(mountId) {
  try {
    const response = await axios.get(dockerUrl + "/docker-container-logs", {
      params: { mountId },
    });
    return response;
  } catch (error) {
    throw error;
  }
}

// 백엔드 개별 실행 전 DB 컨테이너 상태 확인
export async function checkDbContainer(projectId) {
  try {
    const response = await axios.get(containerUrl + "/checkDB", {
      params: { projectId },
    });
    return response;
  } catch (error) {
    throw error;
  }
}

// DB 개별 실행 전 백엔드 컨테이너 상태 확인
export async function checkBackendContainer(projectId) {
  try {
    const response = await axios.get(containerUrl + "/checkBackend", {
      params: { projectId },
    });
    return response;
  } catch (error) {
    throw error;
  }
}