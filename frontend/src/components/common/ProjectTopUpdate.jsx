import styles from "./ProjectTopUpdate.module.css";
import add from "../../assets/createIcon.png";
import useProjectStore from "../../stores/projectStore";

export default function ProjectTopUpdate({ updatedProject }) {
  const { selectedProject, setSelectedProject } = useProjectStore();

  return (
    <div className={styles.top}>
      <div>
        <div className={styles.text}>프로젝트</div>
        <div className={styles.projectName}>{updatedProject.projectName}</div>
      </div>
      <div className={styles.buttons}>
        <div className={styles.add}>
          수정
          <img
            src={add}
            alt=""
            width="23px"
            decoding="async"
            className={styles.btnIcon}
          />
        </div>
      </div>
    </div>
  );
}
