import axiosAPI from "../Axios";
const commonUrl = "/api/project";

export async function build(projectId, object) {
  try {
    const response = await axiosAPI.post(
      commonUrl + `/build/${projectId}`,
      object
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}
