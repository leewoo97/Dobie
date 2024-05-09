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

import { stopProject } from "../../api/Project";

import { useNavigate } from "react-router-dom";

export default function ProjectItem({ project }) {
  const navigate = useNavigate();
  const { selectedProject, setSelectedProject } = useProjectStore();
  const handleSubmit = async (e) => {
    try {
      setSelectedProject(project);
      navigate("/manage");
    } catch (error) {
      console.error("프로젝트 store 저장 실패:", error);
    }
  };

  const [runLoadingModal, setRunLoadingModal] = useState(false);
  const [stopLoadingModal, setStopLoadingModal] = useState(false);

  const handleRunLoadingModal = async () => {
    try {
      setRunLoadingModal(true);
    } catch (error) {

    }
  };

  const handleStopLoadingModal = async () => {
    try {
      setStopLoadingModal(true);
    } catch (error) {

    }
  };

  const handleProjectStop = async (projectId) => {
    try {
      const response = await stopProject(projectId);
      console.log(response);
    } catch (error) {
      console.log("프로젝트 정지 실패: " + error);
    }

  }

  return (
    <>
      <div className={styles.content} onClick={() => handleSubmit()}>
        <div key={project.projectName}>{project.projectName}</div>
        <div key={project.projectDomain}>{project.projectDomain}</div>
        <div className={styles.runButton}>
          <img src={project.running ? rerun : restart} alt="" className={styles.run} onClick={() => handleRunLoadingModal()} />
          <img src={stop} alt="" onClick={() => handleStopLoadingModal()}></img>
        </div>
        <div>
          <img
            src={project.git.gitType == 1 ? gitlab : github}
            alt=""
            className={styles.gitImage}
          />
        </div>
      </div>
      {
        runLoadingModal && (
          <LoadingModal action={"run"} setModalOpen={setRunLoadingModal}/>
        )
      }
      {
        stopLoadingModal && (
          <LoadingModal action={"stop"} setModalOpen={setStopLoadingModal}/>
        )
      }
    </>
  );
}
