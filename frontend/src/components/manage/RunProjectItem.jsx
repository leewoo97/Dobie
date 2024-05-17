import styles from "./RunProjectItem.module.css";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";
import FrameworkImg from "../common/FrameworkImg";
import toast from "react-hot-toast";
import LogMadal from "../modal/LogModal";

import {
  getDockerFile,
  getLog,
  checkDbContainer,
  checkBackendContainer,
} from "../../api/Docker";
import { stopService } from "../../api/Project";
import { startService } from "../../api/Project";
import { useNavigate } from "react-router-dom";

import useProjectStore from "../../stores/projectStore";
import useModalStore from "../../stores/modalStore";
import RunButton from "./RunButton";
import StopButton from "./StopButton";
import { useEffect, useState } from "react";

export default function RunProjectItem({ container, type, setContent }) {
  const { selectedProject } = useProjectStore();
  const { checkProceed } = useProjectStore();
  const { setLoadingModal } = useModalStore();
  const { setAction } = useModalStore();
  const { setFileType } = useModalStore();
  const {
    setModalOpen,
    logModalOpen,
    setLogModalOpen,
    logContent,
    setLogContent,
  } = useModalStore();
  const [runStatus, setRunStatus] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    if (
      checkProceed[container.serviceId || container.databaseId] === "Running :)"
    ) {
      setRunStatus(true);
    } else {
      setRunStatus(false);
    }
  }, []);

  //도커파일 조회
  const handleDockerFileModal = async (projectId, serviceId, type) => {
    try {
      const response = await getDockerFile(projectId, serviceId, type);
      if (response.status === 200) {
        setModalOpen(true);
        setFileType("dockerFile");
        setContent(response.data);
      } else {
        toast.error(`도커 파일 조회 실패`, {
          position: "top-center",
        });
      }
    } catch (error) {
      console.log("docker File 조회 실패: " + error);
    }
  };

  //개별 서비스 중지
  const handleStopService = async (containerName) => {
    // Db 개별 중지 전 확인
    if (type === "Database") {
      const response = await checkBackendContainer(selectedProject.projectId);

      if (!(response.data.data === "Pass")) {
        toast.error(
          `백엔드 컨테이너가 실행중이어서 DB를 종료할 수 없습니다. `,
          {
            position: "top-center",
          }
        );
        return;
      }
    }

    try {
      if (runStatus === true) {
        setAction("stop");
        setLoadingModal(true);
        await stopService(containerName).then(() => setLoadingModal(false));
        setRunStatus(false);
        toast.success(`성공적으로 정지했습니다.`, {
          position: "top-center",
        });
      } else {
        toast.error(`이미 중지된 컨테이너 입니다. `, {
          position: "top-center",
        });
      }
    } catch (error) {
      console.log("개별중지 실패: " + error);
    }
  };

  //개별 서비스 실행
  const handleStartService = async (containerName) => {
    // 백엔드 개별 실행 전 확인
    if (type === "Backend") {
      const response = await checkDbContainer(selectedProject.projectId);
      if (!(response.data.data === "Pass")) {
        toast.error(
          `데이터베이스 컨테이너가 정지중이어서 백엔드를 실행할 수 없습니다. `,
          {
            position: "top-center",
          }
        );
        return;
      }
    }

    try {
      setAction("run");
      setLoadingModal(true);
      await startService(containerName).then(() => setLoadingModal(false));
      setRunStatus(true);
      toast.success(`성공적으로 실행했습니다.`, {
        position: "top-center",
      });
    } catch (error) {
      console.log("개별실행 실패: " + error);
    }
  };

  // 로그 보기
  const handleLogModal = async (serviceId) => {
    try {
      const response = await getLog(serviceId);
      if (response.status === 200) {
        setLogContent(response.data);
        setLogModalOpen(true);
      } else {
        toast.error(`로그 조회 실패`, {
          position: "top-center",
        });
      }
    } catch (error) {
      console.log("로그 조회 실패: " + error);
    }
  };

  const handleIntoContainer = () => {
    if (type === "Backend") {
      navigate(`/manage/backend/${container.serviceId}`);
    } else if (type === "Frontend") {
      navigate(`/manage/frontend`);
    } else {
      navigate(`/manage/database/${container.databaseId}`);
    }
  };

  return (
    <>
      {!(
        type === "Frontend" &&
        !(container.framework === "React" || container.framework === "Vue")
      ) && (
        <div className={styles.container}>
          <div className={styles.containerButton}>
            <div className={styles.runButton}>
              <RunButton
                type={type}
                container={container}
                isRunning={runStatus}
                handleStartService={handleStartService}
              />

              <StopButton
                type={type}
                container={container}
                handleStopService={handleStopService}
              />
            </div>
            {(type === "Backend" || type === "Frontend") && (
              <div
                className={styles.fileButton}
                onClick={() =>
                  handleDockerFileModal(
                    selectedProject.projectId,
                    container.serviceId,
                    type
                  )
                }
              >
                Dockerfile 파일 조회
                <img
                  src={document}
                  alt=""
                  decoding="async"
                  className={styles.btnIcon}
                />
              </div>
            )}
          </div>
          <div className={styles.box} onClick={() => handleIntoContainer()}>
            <div className={styles.boxTop}>
              <table>
                <tbody>
                  <tr>
                    <td key={type} className={styles.serviceName}>
                      {type}
                    </td>
                    <td key={container.externalPort}>
                      외부포트 {container.externalPort}
                    </td>
                  </tr>
                  <tr>
                    {type === "Backend" || type === "Frontend" ? (
                      <td key={container.framework}>{container.framework}</td>
                    ) : (
                      <td key={container.databaseType}>
                        {container.databaseType}
                      </td>
                    )}

                    <td key={container.internalPort}>
                      내부포트 {container.internalPort}
                    </td>
                  </tr>
                </tbody>
              </table>
              <FrameworkImg
                framework={container.framework}
                databaseType={container.databaseType}
              />
            </div>
            <div className={styles.line}></div>
            <div className={styles.boxBottom}>
              <div className={runStatus ? styles.running : styles.stopped}>
                {checkProceed[container.serviceId || container.databaseId]}
              </div>
              <div
                className={styles.log}
                onClick={(event) => {
                  event.stopPropagation();
                  // handleLogModal(container.serviceId);
                  handleLogModal(container.serviceId || container.databaseId);
                }}
              >
                <img
                  src={log}
                  alt=""
                  decoding="async"
                  className={styles.btnIcon}
                />
                로그 보기
              </div>
            </div>
          </div>
        </div>
      )}
      {logModalOpen && <LogMadal content={logContent} />}
    </>
  );
}
