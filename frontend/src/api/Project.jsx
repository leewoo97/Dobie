import axios from "axios";

const projectUrl = process.env.REACT_APP_SERVER + "/project";

//프로젝트 전체 조회
export async function getProject() {
  try {
    const response = await axios.get(projectUrl);
    return response;
  } catch (error) {
    throw error;
  }
}
//프로젝트 삭제
export async function deleteProject(projectId) {
  try {
    const response = await axios.delete(`${projectUrl}/delete/${projectId}`);
    return response;
  } catch (error) {
    throw error;
  }
}

//프로젝트 개별 중지
export async function stopService(containerName) {
  try {
    const response = await axios.post(projectUrl + "/stop/service", null, {
      params: { containerName },
    });
    return response;
  } catch (error) {
    throw error;
  }
}

//프로젝트 개별 실행
export async function startService(containerName) {
  try {
    const response = await axios.post(projectUrl + "/start/service", null, {
      params: { containerName },
    });
    return response;
  } catch (error) {
    throw error;
  }
}

//프로젝트 등록
export async function createProject(project) {
  try {
    const response = await axios.post(`${projectUrl}/regist`, project);
    return response;
  } catch (error) {
    throw error;
  }
}

//프로젝트 전체중지
export async function stopProject(projectId) {
  try {
    const response = await axios.post(`${projectUrl}/stop/${projectId}`);
    return response;
  } catch (error) {
    throw error;
  }
}
//프로젝트 전체실행
export async function startProject(projectId) {
  try {
    const response = await axios.post(`${projectUrl}/run/${projectId}`);
    return response;
  } catch (error) {
    throw error;
  }
}
