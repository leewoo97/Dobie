import { useNavigate } from "react-router-dom";
import styles from "./NavLeftUpdate.module.css";
import s from "classnames";
import add from "../../assets/createIcon.png";
import home2 from "../../assets/homeIcon2.png";
import useProjectStore from "../../stores/projectStore";
import { updateProject } from "../../api/Project";
import Swal from "sweetalert2";

export default function NavLeftUpdate({ num }) {

  const navigate = useNavigate();
  const {updatedProject} = useProjectStore();

  const handleUpdateProject = () => {
    updateProject(updatedProject);
    navigate("/main");
    Swal.fire('성공', '프로젝트가 성공적으로 수정되었습니다.', 'success');
  }

  return (
    <div className={s(styles.container)}>
      <div className={styles.list}>
        <p
          className={num === 2 ? styles.text2 : styles.text}
          onClick={() => navigate("/update/project")}
        >
          Project
        </p>
        <p
          className={num === 3 ? styles.text2 : styles.text}
          onClick={() => navigate("/update/backend")}
        >
          Backend
        </p>
        <p
          className={num === 4 ? styles.text2 : styles.text}
          onClick={() => navigate("/update/frontend")}
        >
          Frontend
        </p>
        <p
          className={num === 5 ? styles.text2 : styles.text}
          onClick={() => navigate("/update/database")}
        >
          DB
        </p>
      </div>
      <div className={styles.buttons}>
        <div className={styles.add}  onClick={handleUpdateProject}>
          프로젝트 수정{" "}
          <img src={add} alt="" decoding="async" className={styles.addIcon}/>
        </div>
        <div className={styles.home} onClick={() => navigate("/main")}>
          메인페이지{" "}
          <img
            src={home2}
            alt=""
            decoding="async"
            className={styles.homeIcon}
          />
        </div>
        {/* <img src={mainBtn} alt="search_icon" onClick={() => navigate("/main")} className={styles.img}/> */}
      </div>
    </div>
  );
}
