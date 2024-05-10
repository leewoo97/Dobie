import { useState, useEffect } from "react";

import styles from "./RunProjectItem.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";
import FrameworkImg from "../common/FrameworkImg";
import toast from "react-hot-toast";
import LoadingModal from "../../components/modal/LoadingModal";

import { getDockerFile } from "../../api/Docker";
import { stopService } from "../../api/Project";
import { startService } from "../../api/Project";

import useProjectStore from "../../stores/projectStore";

export default function RunProjectItem({
  container,
  type,
  setModalOpen,
  setContent,
  setType,
  isRunning,
}) {
  const { selectedProject, setSelectedProject } = useProjectStore();
  console.log(isRunning);

  const handleDockerFileModal = async (projectId, serviceId, type) => {
    try {
      console.log(projectId);
      const response = await getDockerFile(projectId, serviceId, type);

      setModalOpen(true);
      setType("dockerFile");
      setContent(response.data.data);

      console.log(response.data.data);
    } catch (error) {
      console.log("docker File 조회 실패: " + error);
    }
  };

  const handleStopService = async (containerName) => {
    try {
      if (isRunning == "Running :)") {
        console.log(containerName);
        const response = await stopService(containerName);
        console.log(response);
      } else {
        toast.error(`이미 중지된 컨테이너 입니다. `, {
          position: "top-center",
        });
      }
    } catch (error) {
      console.log("개별중지 실패: " + error);
    }
  };
  const handleStartService = async (containerName) => {
    try {
      console.log(containerName);
      const response = await startService(containerName);
      console.log(response);
    } catch (error) {
      console.log("개별실행 실패: " + error);
    }
  };


 
  return (
    <>
      <div className={styles.container}>
        <div className={styles.containerButton}>
          <div className={styles.runButton}>

            {type == "Database" ? (
              <img
                src={isRunning == "Running :)" ? rerun : run}
                alt=""
                width="30px"
                onClick={() => handleStartService(container.databaseId)}
              />
            ) : (
              <img
                src={isRunning == "Running :)" ? rerun : run}
                alt=""
                width="30px"
                onClick={() => handleStartService(container.serviceId)}
              />
            )}

            {(type == "Backend" || type == "Frontend") && (
              <img
                src={stop}
                width="30px"
                onClick={() => handleStopService(container.serviceId)}
              ></img>
            )}
            {type == "Database" && (
              <img
                src={stop}
                width="30px"
                onClick={() => handleStopService(container.databaseId)}
              ></img>
            )}

          </div>
          {(type == "Backend" || type == "Frontend") && (
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
        <div className={styles.box}>
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
                  {type == "Backend" || type == "Frontend" ? (
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
            <div
              className={
                isRunning == "Running :)" ? styles.running : styles.stopped
              }
              key={isRunning}
            >
              {isRunning}
            </div>
            <div className={styles.log}>
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
