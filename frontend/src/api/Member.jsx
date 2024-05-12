import axios from "axios";

const url = process.env.REACT_APP_SERVER + "/user";

//로그인
export async function login(user) {
  try {
    localStorage.clear();

    const response = await axios.post(url + "/login", user);
    return response;
  } catch (error) {
    console.log("로그인실패: " + error);
    throw error;
  }
}

//회원가입
export async function signup(user) {
  try {
    const response = await axios.post(url + "/update", user);
    return response;
  } catch (error) {
    throw error;
  }
}

//회원가입 여부 확인
export async function getUser() {
  try {
    const response = await axios.get(url);
    return response;
  } catch (error) {
    throw error;
  }
}
