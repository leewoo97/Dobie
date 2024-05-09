import axios from "axios";
// const nginxUrl = "http://localhost:8080/api/nginx";
// const nginxUrl = "https://api.silvstone.xyz/api/nginx";
// const nginxUrl = process.env.REACT_APP_SERVER + "/nginx";
const nginxUrl = "http://3.38.208.235:8010/api/nginx";

//프로젝트 전체 조회
export async function getNginxConf(projectId) {
  try {
    const response = await axios.get(nginxUrl, { params: { projectId } });
    console.log(response);
    return response;
  } catch (error) {
    // console.log("회원가입 실패: " + error);
    throw error;
  }
}
