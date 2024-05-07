import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import NavTop from "../../components/common/NavTop";
import NavLeft from "../../components/common/NavLeft";

import toast from "react-hot-toast";
import styles from "./RunPage.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";

import edit from "../../assets/editIcon.png";
import remove from "../../assets/deleteIcon.png";
import setting from "../../assets/settingIcon.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";

import { deleteProject } from "../../api/Project";
import useProjectStore from "../../stores/projectStore";
import RunProjectList from "../../components/manage/RunProjectList";

export default function RunPage() {
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

  return (
    <>
      <NavTop />
      <NavLeft num={1} />
      {/* <InnerContainer /> */}
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
                height="20px"
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
            <div
              className={styles.edit}
              onClick={() => navigate("/create/project")}
            >
              수정{" "}
              <img
                src={edit}
                alt=""
                height="20px"
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
                width="23px"
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
              <img src={run} width="40px"></img>
              <img src={rerun} width="40px"></img>
            </div>
          </div>
          <div className={styles.buttons}>
            <div className={styles.fileButton}>
              ningx.conf 파일 조회{" "}
              <img
                src={document}
                alt=""
                width="30px"
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
            <div className={styles.fileButton}>
              docker-compose.yml 파일 조회{" "}
              <img
                src={document}
                alt=""
                width="30px"
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
          </div>
        </div>
        <RunProjectList />
      </div>
    </>
  );
}
