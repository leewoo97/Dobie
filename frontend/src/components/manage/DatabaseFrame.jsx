import styles from "./DatabaseFrame.module.css";
import { useParams } from "react-router-dom";
import useProjectStore from "../../stores/projectStore";
import ProjectTop from "../common/ProjectTop";

import GetBox from "../common/GetBox";
import GetSecretBox from "../common/GetSecretBox";
import nopage from "../../assets/nopage.PNG";

export default function DatabaseFrame() {
  const { selectedProject } = useProjectStore();

  const params = useParams();

  const serviceId = params.databaseId;

  if(serviceId === "databaseProjectNotFound"){
    return(
      <>
        <div className={styles.page}>
          <ProjectTop projectName={selectedProject.projectName} page={"database"} />
          <div className={styles.con}>
            <img src={nopage} alt={"등록된 페이지 없음"} className={styles.img}/>
          </div>
        </div>
      </>
    )
  }

  console.log(selectedProject);
  const selectedDatabase = new Map(
    Object.entries(selectedProject.databaseMap)
  ).get(serviceId);
  console.log(serviceId);

  return (
    <div className={styles.page}>
      <ProjectTop projectName={selectedProject.projectName} page={"database"}/>
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
        keyName={"데이터베이스명"}
        valueName={selectedDatabase.databaseName}
      />
      <GetBox
        keyName={"초기 데이터 파일 경로"}
        valueName={selectedDatabase.schemaPath}
      />
      <GetBox
        keyName={"외부 포트 번호"}
        valueName={selectedDatabase.externalPort}
      />
      <GetBox
        keyName={"내부 포트 번호"}
        valueName={selectedDatabase.internalPort}
      />
    </div>
  );
}
