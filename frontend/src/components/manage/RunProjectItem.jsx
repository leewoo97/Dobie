import styles from "./RunProjectItem.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";
import FrameworkImg from "../common/FrameworkImg";
import toast from "react-hot-toast";

import { getDockerFile } from "../../api/Docker";
import { stopService } from "../../api/Project";
import { startService } from "../../api/Project";

import useProjectStore from "../../stores/projectStore";
import useModalStore from "../../stores/modalStore";

export default function RunProjectItem({ container, type, setContent }) {
  const { selectedProject, setSelectedProject } = useProjectStore();
  const { checkProceed, setCheckProceed } = useProjectStore();
  const { loadingModal, setLoadingModal } = useModalStore();
  const { action, setAction } = useModalStore();
  const { fileType, setFileType } = useModalStore();
  const { modalOpen, setModalOpen } = useModalStore();

  //도커파일 조회
  const handleDockerFileModal = async (projectId, serviceId, type) => {
    try {
      console.log(checkProceed[container.serviceId]);
      const response = await getDockerFile(projectId, serviceId, type);
      if (response.data.status == "SUCCESS") {
        setModalOpen(true);
        setFileType("dockerFile");
        setContent(response.data.data);
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
    try {
      if (checkProceed[containerName] == "Running :)") {
        setAction("stop");
        setLoadingModal(true);
        const response = await stopService(containerName).then(() =>
          setLoadingModal(false)
        );
        window.location.replace("/manage");
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

  //개별 서비스 실행
  const handleStartService = async (containerName) => {
    try {
      console.log(containerName);
      setAction("run");
      setLoadingModal(true);
      const response = await startService(containerName).then(() =>
        setLoadingModal(false)
      );
      window.location.replace("/manage");
      if (response.data.status == "SUCCESS") {
        console.log(response);
      } else {
        toast.error(`개별 실행에 실패하였습니다. `, {
          position: "top-center",
        });
      }
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
                src={
                  checkProceed[container.databaseId] == "Running :)"
                    ? rerun
                    : run
                }
                alt=""
                width="30px"
                onClick={() => handleStartService(container.databaseId)}
              />
            ) : (
              <img
                src={
                  checkProceed[container.serviceId] == "Running :)"
                    ? rerun
                    : run
                }
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
            {type == "Database" ? (
              <div
                className={
                  checkProceed[container.databaseId] == "Running :)"
                    ? styles.running
                    : styles.stopped
                }
                key={checkProceed[container.databaseId]}
              >
                {checkProceed[container.databaseId]}
              </div>
            ) : (
              <div
                className={
                  checkProceed[container.serviceId] == "Running :)"
                    ? styles.running
                    : styles.stopped
                }
                key={checkProceed[container.serviceId]}
              >
                {checkProceed[container.serviceId]}
              </div>
            )}
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
    </>
  );
}
