import axios from "axios";
import { useState, useEffect } from "react";
import styles from "./ProjectItem.module.css";
import gitlab from "../../assets/gitlab.png";
import github from "../../assets/github.png";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import useProjectStore from "../../stores/projectStore";
import LoadingModal from "../../components/modal/LoadingModal";

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

  const [loadingModal, setLoadingModal] = useState(false);
  const handleLoadingModal = async () => {
    try {
      setLoadingModal(true);
    } catch (error) {

    }
  };

  return (
    <>
      <div className={styles.content} onClick={() => handleSubmit()}>
        <div key={project.projectName}>{project.projectName}</div>
        <div key={project.projectDomain}>{project.projectDomain}</div>
        <div className={styles.runButton}>
          <img src={project.running ? rerun : run} alt="" width="50px" className={styles.run} onClick={() => handleLoadingModal()} />
          <img src={stop} alt="" width="50px"></img>
        </div>
        <div>
          <img
            src={project.git.gitType == 1 ? gitlab : github}
            alt=""
            width="50px"
          />
        </div>
      </div>
      {
        loadingModal && (
          <LoadingModal setModalOpen={setLoadingModal}/>
        )
      }
    </>
  );
}
