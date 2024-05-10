import axios from "axios";
// const processUrl = "http://localhost:8080/api/containercheck";
// const processUrl = "https://api.silvstone.xyz/api/containercheck";
const processUrl = process.env.REACT_APP_SERVER + "/containercheck";

// const processUrl = "http://3.38.208.235:8010/api/containercheck";

//프로젝트 전체 조회
export async function checkProceeding(projectId) {
  try {
    const response = await axios.get(processUrl + "/proceeding", {
      params: { projectId },
    });
    console.log(response);
    return response;
  } catch (error) {
    throw error;
  }
}
