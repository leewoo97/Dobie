import axios from "axios";
import { useState, useEffect } from "react";
import styles from "./ProjectItem.module.css";
import gitlab from "../../assets/gitlab.png";
import github from "../../assets/github.png";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import useProjectStore from "../../stores/projectStore";
import LoadingModal from "../modal/LoadingModal";
import restart from "../../assets/restart.png";

import { startService, stopProject } from "../../api/Project";

import { useNavigate } from "react-router-dom";
import useModalStore from "../../stores/modalStore";

export default function ProjectItem({ project }) {
  const navigate = useNavigate();
  const { selectedProject, setSelectedProject } = useProjectStore();
  const { loadingModal, setLoadingModal } = useModalStore();
  const handleSubmit = async (e) => {
    try {
      setSelectedProject(project);
      navigate("/manage");
    } catch (error) {
      console.error("프로젝트 store 저장 실패:", error);
    }
  };

  const handleProjectStop = async (projectId) => {
    try {
      const response = await stopProject(projectId);
      console.log(response);
    } catch (error) {
      console.log("프로젝트 정지 실패: " + error);
    }
  };

  const handleProjectStart = async (projectId) => {
    try {
      const response = await startService(projectId);
      console.log(response);
    } catch (error) {
      console.log("프로젝트 전체실행 실패");
    }
  };

  return (
    <>
      <div className={styles.content} onClick={() => handleSubmit()}>
        <div key={project.projectName}>{project.projectName}</div>
        <div key={project.projectDomain}>{project.projectDomain}</div>
        <div className={styles.runButton}>
          <img
            src={project.running ? rerun : restart}
            alt=""
            className={styles.run}
            onClick={() => handleProjectStart(project.projectId)}
          />
          <img
            src={stop}
            alt=""
            onClick={() => handleProjectStop(project.projectId)}
          ></img>
        </div>
        <div>
          <img
            src={project.git.gitType == 1 ? gitlab : github}
            alt=""
            className={styles.gitImage}
          />
        </div>
      </div>
      {loadingModal && <LoadingModal />}
    </>
  );
}
