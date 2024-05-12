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
      <GetBox keyName={"Git URL"} valueName={selectedProject.git.gitUrl} />
      <GetSecretBox
        keyName={"Access Token"}
        valueName={selectedProject.git.accessToken}
      />
      <GetBox
        keyName={"Branch"}
        valueName={selectedProject.backendMap.branch}
      />
    </div>
  );
}
