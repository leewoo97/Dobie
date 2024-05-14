import styles from "./ProjectTop.module.css";
import edit from "../../assets/editIcon.png";
import remove from "../../assets/deleteIcon.png";
import useProjectStore from "../../stores/projectStore";
import { useNavigate } from "react-router-dom";

export default function ProjectTop({ projectName, page }) {

    const {updatedProject, setUpdatedProject, selectedProject} = useProjectStore();
    const navigate = useNavigate();
    
    const handleUpdateProject = () => {
      setUpdatedProject({...selectedProject});
      navigate(`/update/${page}`);
    }
  
  return (
    <div className={styles.top}>
      <div>
        <div className={styles.text}>프로젝트</div>
        <div className={styles.projectName}>{projectName}</div>
      </div>
      <div className={styles.buttons}>
        <div className={styles.edit} onClick={handleUpdateProject}>
          수정{" "}
          <img src={edit} alt="" decoding="async" className={styles.btnIcon} />
        </div>
        <div className={styles.remove}>
          삭제{" "}
          <img
            src={remove}
            alt=""
            decoding="async"
            className={styles.btnIcon}
          />
        </div>
      </div>
    </div>
  );
}
