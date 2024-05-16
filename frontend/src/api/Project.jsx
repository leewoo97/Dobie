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
    const response = await axios.post(`${projectUrl}/create`, project);
    return response;
  } catch (error) {
    throw error;
  }
}

//프로젝트 수정
export async function updateProject(project) {
  // console.log(project);
  try {
    const response = await axios.put(`${projectUrl}/update`, project);
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

export async function buildProject(projectId) {
  try {
    const response = await axios.post(`${projectUrl}/build/${projectId}`);
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

// 파일 저장
export async function addFile(dto, files) {
  const formData = new FormData();

  // 프로젝트 데이터 JSON 추가 (Content-Type 포함)
  formData.append(
    "dto",
    new Blob([JSON.stringify(dto)], { type: "application/json" })
  );

  // 파일들 추가
  files.forEach((file, index) => {
    if (file === null) {
      // 빈 Blob 객체를 전송
      formData.append("files", new Blob(), `placeholder-${index}`);
    } else {
      formData.append("files", file, file.name);
    }
  });

  try {
    const response = await axios.post(`${projectUrl}/file`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

// 파일 조회
export async function getFile(projectId) {
  try {
    const response = await axios.get(`${projectUrl}/file/${projectId}`);
    return response;
  } catch (error) {
    throw error;
  }
}

// 파일 삭제
export async function deleteFile(dto) {
  try {
    const response = await axios.put(`${projectUrl}/file`, dto);
    return response;
  } catch (error) {
    throw error;
  }
}
