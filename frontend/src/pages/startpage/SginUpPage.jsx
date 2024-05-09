import React, { useEffect, useState } from "react";
import Container from "../../components/common/Container";
import styles from "./SginUpPage.module.css";
import mascot from "../../assets/mascot.png";
import { useNavigate } from "react-router-dom";
import useUserStore from "../../stores/userStore";
import toast, { Toaster } from "react-hot-toast";

import { signup } from "../../api/Member";
import axios from "axios";

export default function SginUpPage() {
  const navigate = useNavigate();

  const { user, setUser } = useUserStore();

  const [formData, setFormData] = useState({
    username: "",
    password: "",
    confirmPassword: "",
  });

  /* 값을 입력함과 동시에 form 데이터 동시에 갱신 */
  function handleChange(e) {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value, // 현재 변경된 입력 필드를 기존 상태에 덮어쓰기
    }));
  }

  const handleSubmit = async (e) => {
    try {
      if (formData.password != formData.confirmPassword) {
        toast.error(`비밀번호가 일치하지 않습니다.`, {
          position: "top-center",
        });
        console.log("비밀번호 불일치");
        return;
      } else {
        console.log("비밀번호 일치");

        const user = {
          username: formData.username,
          password: formData.password,
        };

        const data = await signup(user);
        console.log(data);
        if (data.status === 200) {
          setFormData({
            username: "",
            password: "",
            confirmPassword: "",
          });
          navigate(`/login`);
          toast.success(`회원가입 성공 !`, {
            position: "top-center",
          });
        } else {
          console.log(data);
          toast.error(`${data.message}`, {
            position: "top-center",
          });
        }
      }
    } catch (error) {
      // 오류 메시지 토스트로 표시
      console.error("회원가입 에러: ", error);
    }
  };

  return (
    <Container>
      <div className={styles.content}>
        <div className={styles.title}>
          <img src={mascot} alt="" height="140vh" decoding="async" />
          <div className={styles.dobie}>Dobie</div>
        </div>
        <input
        className={styles.signupInput}
          type="text"
          name="username"
          value={formData.username}
          onChange={handleChange}
          placeholder="username"
        ></input>
        <input
        className={styles.signupInput}
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          placeholder="password"
        ></input>
        <input
        className={styles.signupInput}
          type="password"
          name="confirmPassword"
          value={formData.confirmPassword}
          onChange={handleChange}
          placeholder="confirm password"
        ></input>
        <div className={styles.button}>
          <div className={styles.login} onClick={() => handleSubmit()}>
            회원가입
          </div>
        </div>
      </div>
    </Container>
  );
}
