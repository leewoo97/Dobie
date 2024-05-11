import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useModalStore from "../../stores/modalStore";
import toast from "react-hot-toast";
import styles from "./ProjectItem.module.css";
import useProjectStore from "../../stores/projectStore";
import { startProject, stopProject } from "../../api/Project";
import { checkProceeding } from "../../api/CheckProcess";
import LoadingModal from "../modal/LoadingModal";

import gitlab from "../../assets/gitlab.png";
import github from "../../assets/github.png";
import run from "../../assets/run.png";
import stop from "../../assets/stop.png";
import restart from "../../assets/restart.png";

export default function ProjectItem({ project }) {
  const navigate = useNavigate();
  const { setSelectedProject } = useProjectStore();
  const { loadingModal, setLoadingModal } = useModalStore();
  const { setAction } = useModalStore();
  const [checkProceed, setCheckProceed] = useState({});

  useEffect(() => {
    handleCheckProceding();
    setLoadingModal(false);
  }, []);

  //실행상태 조회
  const handleCheckProceding = async () => {
    try {
      const response = await checkProceeding(project.projectId);
      if (response.data.status === "SUCCESS") {
        setCheckProceed(response.data.data);
      } else {
        setCheckProceed({ allRunning: "null" });
        toast.error(`프로젝트 실행상태를 불러올수 없습니다.`, {
          position: "top-center",
        });
      }
    } catch (error) {
      console.error("컨테이너 실행 확인 에러: ", error);
    }
  };

  const handleSubmit = async (e) => {
    try {
      setSelectedProject(project);
      navigate("/manage");
    } catch (error) {
      console.error("프로젝트 store 저장 실패:", error);
    }
  };

  //전체 프로젝트 중지
  const handleProjectStop = async (projectId) => {
    try {
      if (checkProceed.allRunning === "Run") {
        setAction("stop");
        setLoadingModal(true);
        const response = await stopProject(projectId).then(() =>
          setLoadingModal(false)
        );
        window.location.replace("/main");
      } else {
        toast.error(`이미 중지된 프로젝트 입니다. `, {
          position: "top-center",
        });
      }
    } catch (error) {
      console.log("프로젝트 정지 실패: " + error);
    }
  };

  //전체 프로젝트 실행
  const handleProjectStart = async (projectId) => {
    try {
      setAction("run");
      setLoadingModal(true);
      const response = await startProject(projectId).then(() =>
        setLoadingModal(false)
      );
      window.location.replace("/main");
      if (response.data.status === "SUCCESS") {
      } else {
        toast.error(`전체 실행에 실패하였습니다. `, {
          position: "top-center",
        });
      }
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
            src={checkProceed.allRunning === "Run" ? restart : run}
            alt=""
            className={styles.run}
            onClick={(event) => {
              event.stopPropagation();
              handleProjectStart(project.projectId);
            }}
          />
          <img
            src={stop}
            alt=""
            className={styles.stop}
            onClick={(event) => {
              event.stopPropagation();
              handleProjectStop(project.projectId);
            }}
          ></img>
        </div>
        <div>
          <img
            src={project.git.gitType === 1 ? gitlab : github}
            alt=""
            className={styles.gitImage}
          />
        </div>
      </div>
      {loadingModal && <LoadingModal />}
    </>
  );
}
