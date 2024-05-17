import styles from "./ProjectFrame.module.css";
import GetBox from "../common/GetBox";
import GetRadioBox from "../common/GetRadioBox";
import GetSecretBox from "../common/GetSecretBox";
import GetToggleBox from "../common/GetToggleBox";
import ProjectTop from "../common/ProjectTop";
import useProjectStore from "../../stores/projectStore";

export default function ProjectFrame() {
  const { selectedProject } = useProjectStore();

  return (
    <div className={styles.page}>
      <ProjectTop projectName={selectedProject.projectName} page={"project"}/>
      <GetBox keyName={"Domain Name"} valueName={selectedProject.projectDomain}/>
      <GetRadioBox keyName={"Git Type"} valueName={selectedProject.git.gitType} isRadio />
      <GetBox keyName={"Git URL"} valueName={selectedProject.git.gitUrl} />
      <GetSecretBox
        keyName={"Access Token"}
        valueName={selectedProject.git.accessToken}
      />
      <GetBox
        keyName={"Branch"}
        valueName={selectedProject.git.branch}
      />
      <GetToggleBox keyName={"https"} valueName={selectedProject.usingHttps} isToggle />
    </div>
  );
}
