import styles from "./ProjectFrame.module.css";
import GetBox from "../common/GetBox";
import GetSecretBox from "../common/GetSecretBox";
import ProjectTop from "../common/ProjectTop";
import useProjectStore from "../../stores/projectStore";

export default function ProjectFrame() {
  const { selectedProject } = useProjectStore();

  return (
    <div className={styles.page}>
      <ProjectTop projectName={selectedProject.projectName} />
      <GetBox keyName={"url"} valueName={selectedProject.git.gitUrl} />
      <GetSecretBox
        keyName={"액세스 토큰"}
        valueName={selectedProject.git.accessToken}
      />
      {/* <GetBox keyName={"자동 배포 웹훅 설정"} valueName={"webHook"} /> */}
      <GetBox
        keyName={"브랜치"}
        valueName={selectedProject.backendMap.branch}
      />
    </div>
  );
}
