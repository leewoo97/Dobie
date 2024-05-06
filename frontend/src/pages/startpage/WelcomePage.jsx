import React, { useEffect, useState } from "react";
import Container from "../../components/common/Container";
import mascot from "../../assets/mascot.png";
import styles from "./WelcomePage.module.css";

import { useNavigate } from "react-router-dom";

export default function WelcomePage() {
  const navigate = useNavigate();

  // useEffect(() => {
  //     const timer = setTimeout(() => {
  //         navigate('/login');  // "/login"은 LoginPage 경로입니다.
  //     }, 2000);  // 2000 밀리초 후에 실행

  //     // 컴포넌트 언마운트 시 타이머 클리어
  //     return () => clearTimeout(timer);
  // }, [navigate]);  // navigate가 변경될 때마다 useEffect를 다시 실행하지 않도록

  return (
    <Container>
      <div className={styles.container}>
        <div className={styles.content}>
          <div className={styles.mascot}>
            <img src={mascot} alt="" width="200px" decoding="async" />
          </div>
          <div className={styles.text}>
            <div className={styles.dobie}>Dobie</div>
            <div className={styles.eng}>
              <div className={styles.line1}>Docker newbie</div>
              <div className={styles.line2}>Docker is free</div>
            </div>
            <div className={styles.kor}>
              <div className={styles.line3}>인프라로부터</div>
              <div className={styles.line4}>자유로워지자</div>
            </div>
          </div>
        </div>
      </div>
    </Container>
  );
}
