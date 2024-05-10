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

import { checkProceeding } from "../../api/CheckProcess";

export default function RunProjectList({
  setModalOpen,
  setContent,
  setType,
  data,
}) {
  const { selectedProject, setSelectedProject } = useProjectStore();
  // const [data, setData] = useState({});

  // useEffect(() => {
  //   try {
  //     const response = checkProceeding(selectedProject.projectId);
  //     setData(response.data);
  //     console.log(response.data[selectedProject.frontend.serviceId]);
  //   } catch (error) {
  //     console.error("컨테이너 실행 확인 에러: ", error);
  //   }
  // }, []);

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
                isRunning={data[container.serviceId]}
              />
            ))}
            <RunProjectItem
              container={selectedProject.frontend}
              type="Frontend"
              setModalOpen={setModalOpen}
              setContent={setContent}
              setType={setType}
              isRunning={data[selectedProject.frontend.serviceId]}
            />
            {Object.values(selectedProject.databaseMap).map((container) => (
              <RunProjectItem
                key={container.databaseId}
                container={container}
                type="Database"
                setModalOpen={setModalOpen}
                setContent={setContent}
                setType={setType}
                isRunning={data[container.databaseId]}
              />
            ))}
          </div>
        )}
    </>
  );
}
