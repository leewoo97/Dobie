import axios from "axios";

// const axiosAPI = axios.create({ baseURL: process.env.REACT_APP_URL });
const axiosAPI = axios.create({ baseURL: "http://localhost:8080" });

// // 요청 인터셉터 추가
// axiosAPI.interceptors.request.use(
//   (config) => {
//     const token = localStorage.getItem("accessToken");
//     if (token) {
//       config.headers.Authorization = `Bearer ${token}`;
//     }

//     // 수정된 설정을 반환합니다.
//     return config;
//   },
//   (error) => {
//     console.log(error);
//     return Promise.reject(error);
//   }
// );

// axiosAPI.defaults.withCredentials = true;

export default axiosAPI;
