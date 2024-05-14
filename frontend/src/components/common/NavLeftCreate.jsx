import { useNavigate } from "react-router-dom";
import styles from "./NavLeftCreate.module.css";
import s from "classnames";
import add from "../../assets/createIcon.png";
import home2 from "../../assets/homeIcon2.png";
import { createProject } from "../../api/Project";
import useProjectStore from "../../stores/projectStore";
import Swal from "sweetalert2";

export default function NavLeftCreate({ num }) {
  const navigate = useNavigate();

  const {createdProject} = useProjectStore();
  const handleCreatProject = () => {
    createProject(createdProject);
    navigate("/main");
    Swal.fire('성공', '프로젝트를 성공적으로 등록했습니다.', 'success');
  }
  
  return (
    <div className={s(styles.container)}>
      <div className={styles.list}>
        <p
          className={num === 2 ? styles.text2 : styles.text}
          onClick={() => navigate("/create/project")}
        >
          Project
        </p>
        <p
          className={num === 3 ? styles.text2 : styles.text}
          onClick={() => navigate("/create/backend")}
        >
          Backend
        </p>
        <p
          className={num === 4 ? styles.text2 : styles.text}
          onClick={() => navigate("/create/frontend")}
        >
          Frontend
        </p>
        <p
          className={num === 5 ? styles.text2 : styles.text}
          onClick={() => navigate("/create/database")}
        >
          DB
        </p>
      </div>
      <div className={styles.buttons}>
        <div className={styles.add} onClick={handleCreatProject}>
          프로젝트 등록{" "}
          <img src={add} alt="" decoding="async" className={styles.addIcon} />
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
