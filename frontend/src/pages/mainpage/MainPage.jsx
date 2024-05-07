import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import NavTop from "../../components/common/NavTop";
import styles from "./MainPage.module.css";
import newpjtIcon from "../../assets/newpjtIcon.png";
import guideIcon from "../../assets/guideIcon.png";

import ProjectList from "../../components/main/ProjectList";

export default function MainPage() {
  const navigate = useNavigate();

  return (
    <>
      <NavTop />
      <div className={styles.topButton}>
        <div
          className={styles.newpjtBtn}
          onClick={() => navigate("/create/project")}
        >
          새 프로젝트 등록
          <img
            src={newpjtIcon}
            alt=""
            width="30px"
            decoding="async"
            className={styles.btnIcon}
          />
        </div>
        <div
          className={styles.guideBtn}
          onClick={() => navigate("/main/guide")}
        >
          도비 가이드
          <img
            src={guideIcon}
            alt=""
            width="30px"
            decoding="async"
            className={styles.btnIcon}
          />
        </div>
      </div>

      <ProjectList />
    </>
  );
}
