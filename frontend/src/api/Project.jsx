import axios from "axios";
const projectUrl = "http://localhost:8080/api/project";

//프로젝트 전체 조회
export async function getProject() {
  try {
    const response = await axios.get(projectUrl);
    return response;
  } catch (error) {
    // console.log("회원가입 실패: " + error);
    throw error;
  }
}
//프로젝트 삭제
export async function deleteProject(projectId) {
  try {
    const response = await axios.delete(`${projectUrl}/delete/${projectId}`);
    return response;
  } catch (error) {
    // console.log("회원가입 실패: " + error);
    throw error;
  }
}
