import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import NavTop from "../../components/common/NavTop";
import NavLeft from "../../components/common/NavLeft";

import toast from "react-hot-toast";
import styles from "./RunPage.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import close from "../../assets/close.png";
import edit from "../../assets/editIcon.png";
import remove from "../../assets/deleteIcon.png";
import setting from "../../assets/settingIcon.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";

import { deleteProject } from "../../api/Project";
import { getNginxConf } from "../../api/ngixn";
import { getDockerCompose } from "../../api/Docker";
import useProjectStore from "../../stores/projectStore";
import RunProjectList from "../../components/manage/RunProjectList";
import Modal from "../../components/modal/Modal";
import LoadingModal from "../../components/modal/LoadingModal";

export default function RunPage() {
  const [modalOpen, setModalOpen] = useState(false);
  const [content, setContent] = useState("");
  const [type, setType] = useState("");
  const [runLoadingModal, setRunLoadingModal] = useState(false);
  const [stopLoadingModal, setStopLoadingModal] = useState(false);
  // const modalBackground = useRef();

  const { selectedProject, setSelectedProject } = useProjectStore();
  const navigate = useNavigate();

  const handleDelete = async (projectId) => {
    try {
      const response = await deleteProject(projectId);
      console.log(response);
      navigate("/main");
      toast.success(`프로젝트를 삭제했습니다`, {
        position: "top-center",
      });
    } catch (error) {
      console.log("프로젝트 삭제 실패: " + error);
    }
  };
  const handleOpenNginxModal = async (projectId) => {
    try {
      console.log(projectId);
      const response = await getNginxConf(projectId);

      setModalOpen(true);
      setType("nginx");
      setContent(response.data.data);

      console.log(response.data.data);
    } catch (error) {
      console.log("nginx config 조회 실패: " + error);
    }
  };
  const handleDockerComposeModal = async (projectId) => {
    try {
      console.log(projectId);
      const response = await getDockerCompose(projectId);

      setModalOpen(true);
      setType("dockerCompose");
      setContent(response.data.data);

      console.log(response.data.data);
    } catch (error) {
      console.log("docker compose 조회 실패: " + error);
    }
  };

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
      <NavTop />
      <NavLeft num={1} />
      <div className={styles.page}>
        <div className={styles.top}>
          <div>
            <div className={styles.text}>프로젝트</div>
            <div className={styles.projectName}>
              {selectedProject.projectName}
            </div>
          </div>
          <div className={styles.buttons}>
            <div className={styles.webhook}>
              Webhook 설정{" "}
              <img
                src={setting}
                alt=""
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
            <div
              className={styles.edit}
              onClick={() => navigate("/update/project")}
            >
              수정{" "}
              <img
                src={edit}
                alt=""
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
            <div
              className={styles.remove}
              onClick={() => handleDelete(selectedProject.projectId)}
            >
              삭제{" "}
              <img
                src={remove}
                alt=""
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
          </div>
        </div>
        <div className={styles.mid}>
          <div>
            <div className={styles.text}>프로젝트 전체 실행</div>
            <div className={styles.runButton}>
              <img src={run} onClick={() => handleRunLoadingModal()}></img>
              <img src={stop} onClick={() => handleStopLoadingModal()}></img>
            </div>
          </div>
          <div className={styles.buttons}>
            <div
              className={styles.fileButton}
              onClick={() => handleOpenNginxModal(selectedProject.projectId)}
            >
              nginx.config 파일 조회{" "}
              <img
                src={document}
                alt=""
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
            <div
              className={styles.fileButton}
              onClick={() =>
                handleDockerComposeModal(selectedProject.projectId)
              }
            >
              docker-compose.yml 파일 조회{" "}
              <img
                src={document}
                alt=""
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
          </div>
        </div>
        <RunProjectList
          setModalOpen={setModalOpen}
          setContent={setContent}
          setType={setType}
        />
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
      {modalOpen && (
        <Modal content={content} type={type} setModalOpen={setModalOpen} />
      )}
    </>
  );
}
