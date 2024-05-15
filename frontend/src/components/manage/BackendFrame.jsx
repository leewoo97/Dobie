import styles from "./BackendFrame.module.css";
import GetBox from "../common/GetBox";
import ProjectTop from "../common/ProjectTop";
import useProjectStore from "../../stores/projectStore";
import { useParams } from "react-router-dom";

export default function BackendFrame() {
  const { selectedProject } = useProjectStore();
  const params = useParams();

  const serviceId = params.serviceId;
  console.log(serviceId);
  const selectedBackend = new Map(
    Object.entries(selectedProject.backendMap)
  ).get(serviceId);

  return (
    <div className={styles.page}>
      <ProjectTop projectName={selectedProject.projectName} page={"backend"}/>
      <GetBox keyName={"프레임워크"} valueName={selectedBackend.framework} />
      <GetBox
        keyName={"언어 버전"}
        valueName={selectedBackend.language + " " + selectedBackend.version}
      />
      <GetBox keyName={"폴더 경로"} valueName={selectedBackend.path} />
      <GetBox keyName={"Nginx location"} valueName={selectedBackend.location} />
      <GetBox
        keyName={"내부 포트 번호"}
        valueName={selectedBackend.internalPort}
      />
    </div>
  );
}
