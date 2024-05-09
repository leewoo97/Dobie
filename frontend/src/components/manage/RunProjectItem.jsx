import { useState, useEffect } from "react";

import styles from "./RunProjectItem.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";
import FrameworkImg from "../common/FrameworkImg";
import LoadingModal from "../../components/modal/LoadingModal";

import { getDockerFile } from "../../api/Docker";

import useProjectStore from "../../stores/projectStore";

export default function RunProjectItem({
  container,
  type,
  setModalOpen,
  setContent,
  setType,
}) {
  const { selectedProject, setSelectedProject } = useProjectStore();

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

  return (
    <>
      <div className={styles.container}>
        <div className={styles.containerButton}>
          <div className={styles.runButton}>
            <img
              src={container.running == "Running :)" ? rerun : run}
              alt=""
              onClick={() => handleRunLoadingModal()}
            />
            <img src={stop} 
            onClick={() => handleStopLoadingModal()}></img>
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
                container.running == "Running :)"
                  ? styles.running
                  : styles.stopped
              }
              key={container.running}
            >
              {container.running}
              stoped :(
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
