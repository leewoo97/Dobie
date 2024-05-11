import React, { useEffect } from "react";
import Container from "../../components/common/Container";
import mascot from "../../assets/mascot.png";
import styles from "./WelcomePage.module.css";
import { useNavigate } from "react-router-dom";
import { getUser } from "../../api/Member";

export default function WelcomePage() {
  const navigate = useNavigate();

  useEffect(() => {
    try {
      getUserInfo(); //useEffect안에서 await처리 안돼서 함수로 빼서 실행
    } catch (error) {
      console.error("유저정보 조회 실패 에러: ", error);
    }
  }, [navigate]); // navigate가 변경될 때마다 useEffect를 다시 실행하지 않도록

  const getUserInfo = async (e) => {
    try {
      const response = await getUser(); //userInfo API연결

      //response에 username이 null이면 회원가입 창(/sginup)으로 이동
      if (response.data.username === null) {
        const timer = setTimeout(() => {
          navigate("/sginup");
        }, 2000); // 2000 밀리초 후에 실행

        // 컴포넌트 언마운트 시 타이머 클리어
        return () => clearTimeout(timer);
      }

      //response에 username이 null이 아니면 로그인 창(/login)으로 이동
      else {
        const timer = setTimeout(() => {
          navigate("/login");
        }, 2000); // 2000 밀리초 후에 실행

        // 컴포넌트 언마운트 시 타이머 클리어
        return () => clearTimeout(timer);
      }
    } catch (error) {
      console.error("유저정보 조회 실패 에러: ", error);
    }
  };

  return (
    <Container>
      <div className={styles.container}>
        <div className={styles.content}>
          <div className={styles.mascot}>
            <img
              src={mascot}
              className={styles.dobieIcon}
              alt=""
              decoding="async"
            />
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
