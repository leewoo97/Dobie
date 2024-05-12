import axios from "axios";
const nginxUrl = process.env.REACT_APP_SERVER + "/nginx";

//nginx config 파일 조회
export async function getNginxConf(projectId) {
  try {
    const response = await axios.get(nginxUrl, { params: { projectId } });
    return response;
  } catch (error) {
    throw error;
  }
}
