import styles from "./DatabaseFrame.module.css";
import { useParams } from "react-router-dom";
import useProjectStore from "../../stores/projectStore";
import ProjectTop from "../common/ProjectTop";

import GetBox from "../common/GetBox";
import GetSecretBox from "../common/GetSecretBox";

export default function DatabaseFrame() {
  const { selectedProject } = useProjectStore();

  const params = useParams();

  const serviceId = params.databaseId;
  const selectedDatabase = new Map(
    Object.entries(selectedProject.databaseMap)
  ).get(serviceId);
  return (
    <div className={styles.page}>
      <ProjectTop projectName={selectedProject.projectName} />
      <GetBox
        keyName={"프레임워크"}
        valueName={selectedDatabase.databaseType}
      />
      <GetBox keyName={"Username"} valueName={selectedDatabase.username} />
      <GetSecretBox
        keyName={"Password"}
        valueName={selectedDatabase.password}
      />
      <GetBox
        keyName={"내부 포트 번호"}
        valueName={selectedDatabase.internalPort}
      />
    </div>
  );
}
