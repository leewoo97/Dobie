import axios from "axios";
const projectUrl = "http://localhost:8080/api/project";

//회원가입 여부 확인
export async function getProject() {
  try {
    const response = await axios.get(projectUrl);
    return response;
  } catch (error) {
    // console.log("회원가입 실패: " + error);
    throw error;
  }
}
