import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useModalStore from "../../stores/modalStore";
import toast from "react-hot-toast";
import styles from "./ProjectItem.module.css";
import useProjectStore from "../../stores/projectStore";
import {
  buildProject,
  getProject,
  startProject,
  stopProject,
} from "../../api/Project";
import { checkProceeding } from "../../api/CheckProcess";
import LoadingModal from "../modal/LoadingModal";

import gitlab from "../../assets/gitlab.png";
import github from "../../assets/github.png";
import run from "../../assets/run.png";
import stop from "../../assets/stop.png";
import restart from "../../assets/restart.png";
import build from "../../assets/settings.png";
import NewModal from "../modal/NewModal";

export default function ProjectItem({ project }) {
  const navigate = useNavigate();
  const { setSelectedProject } = useProjectStore();
  const { loadingModal, setLoadingModal } = useModalStore();
  const { setAction } = useModalStore();
  const [checkProceed, setCheckProceed] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    handleCheckProceding();
    setLoadingModal(false);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  //실행상태 조회
  const handleCheckProceding = async () => {
    try {
      const response = await checkProceeding(project.projectId);
      if (response.data.status === "SUCCESS") {
        setCheckProceed(response.data.data);
        setIsLoading(false);
      } else {
        setCheckProceed({ allRunning: "null" });
        const res = await getProject();
        if (res.data.data !== null) {
          toast.error(`프로젝트 실행상태를 불러올수 없습니다.`, {
            position: "top-center",
          });
        }
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

  // 프로젝트 빌드
  const handleProjectBuild = async (projectId) => {
    try {
      setAction("build");
      setLoadingModal(true);
      const response = await buildProject(projectId);
      if (response.data.status === "SUCCESS") {
        setLoadingModal(false);
        toast.success("빌드파일이 성공적으로 생성되었습니다.");
      }
    } catch (error) {
      console.log("에러발생", error);
    }
  };

  //전체 프로젝트 중지
  const handleProjectStop = async (projectId) => {
    try {
      if (checkProceed.allRunning === "Run") {
        setAction("stop");
        setLoadingModal(true);
        await stopProject(projectId).then(() => setLoadingModal(false));
        setCheckProceed({ allRunning: "null" });
        toast.success(`성공적으로 중지되었습니다. `, {
          position: "top-center",
        });
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
      await startProject(projectId).then(() => setLoadingModal(false));
      setCheckProceed({ allRunning: "Run" });
      toast.success(`프로젝트가 정상적으로 실행되었습니다. `, {
        position: "top-center",
      });
    } catch (error) {
      setLoadingModal(false);
      toast.error(`프로젝트 등록 후 빌드가 진행되어야 합니다. `, {
        position: "top-center",
      });
    }
  };

  const linkGit = () => {
    window.open(project.git.gitUrl);
  };

  return (
    <>
      {isLoading ? (
        <NewModal />
      ) : (
        <>
          <div className={styles.content} onClick={() => handleSubmit()}>
            <div key={project.projectName}>{project.projectName}</div>
            <div key={project.projectDomain}>{project.projectDomain}</div>
            <div className={styles.buildIcon}>
              <img
                src={build}
                className={styles.build}
                alt=""
                onClick={(event) => {
                  event.stopPropagation();
                  handleProjectBuild(project.projectId);
                }}
              />
            </div>
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
                onClick={linkGit}
              />
            </div>
          </div>
          {loadingModal && <LoadingModal />}
        </>
      )}
    </>
  );
}
