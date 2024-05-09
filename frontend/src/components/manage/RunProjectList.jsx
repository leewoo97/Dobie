import { useState, useEffect } from "react";

import styles from "./RunProjectList.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import setting from "../../assets/settingIcon.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";
import RunProjectItem from "./RunProjectItem";
import useProjectStore from "../../stores/projectStore";

export default function RunProjectList({ setModalOpen, setContent, setType }) {
  const { selectedProject, setSelectedProject } = useProjectStore();

  useEffect(() => {
    try {
      console.log(selectedProject);
    } catch (error) {
      console.error("유저정보 조회 실패 에러: ", error);
    }
  }, []);

  return (
    <>
      {Object.values(selectedProject.backendMap).length > 0 &&
        Object.values(selectedProject.databaseMap).length > 0 && (
          <div className={styles.bottom}>
            {Object.values(selectedProject.backendMap).map((container) => (
              <RunProjectItem
                key={container.serviceId}
                container={container}
                type="Backend"
                setModalOpen={setModalOpen}
                setContent={setContent}
                setType={setType}
              />
            ))}
            <RunProjectItem
              container={selectedProject.frontend}
              type="Frontend"
              setModalOpen={setModalOpen}
              setContent={setContent}
              setType={setType}
            />
            {Object.values(selectedProject.databaseMap).map((container) => (
              <RunProjectItem
                key={container.databaseId}
                container={container}
                type="Database"
                setModalOpen={setModalOpen}
                setContent={setContent}
                setType={setType}
              />
            ))}
          </div>
        )}
    </>
  );
}
