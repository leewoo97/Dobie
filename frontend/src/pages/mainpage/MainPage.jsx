import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import NavTop from "../../components/common/NavTop";
import styles from "./MainPage.module.css";
import newpjtIcon from "../../assets/newpjtIcon.png";
import guideIcon from "../../assets/guideIcon.png";

import ProjectList from "../../components/main/ProjectList";
import useProjectStore from "../../stores/projectStore";

export default function MainPage() {
  const navigate = useNavigate();
  const { selectedProject, setSelectedProject, newProject, makeNewProject } = useProjectStore();

  useEffect(() => {
    try {
      setSelectedProject({});
    } catch (error) {
      console.error("setSelectedProject초기화 에러: ", error);
    }
  }, []);

  const createClickHandler = () => {
    makeNewProject();
    navigate("/create/project")
  }

  return (
    <>
      <NavTop />
      <div className={styles.topButton}>
        <div
          className={styles.newpjtBtn}
          onClick={createClickHandler}
        >
          새 프로젝트 등록
          <img
            src={newpjtIcon}
            alt=""
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
            decoding="async"
            className={styles.btnIcon}
          />
        </div>
      </div>

      <ProjectList />
    </>
  );
}
