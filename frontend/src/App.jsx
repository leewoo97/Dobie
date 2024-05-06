import './App.css';
import { Outlet } from "react-router-dom";
import axios from "axios";
import { useEffect } from 'react';

axios.defaults.withCredentials = true;

axios.interceptors.request.use(
  async (config) => {
    let accessToken = localStorage.getItem("accessToken");

    if(!accessToken) {
      console.log("accessToken이 없습니다.");
      return config;
    }

    config.headers["Authorization"] = `Bearer ${accessToken}`;
    return config;
  },
  (error) => {
   
    localStorage.clear();
    window.location.href = "/login";
  }
)

export default function App() {
  return (
    <>
      <Outlet />
    </>
  );
}

