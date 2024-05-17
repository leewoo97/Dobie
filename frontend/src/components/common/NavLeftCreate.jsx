import { useNavigate } from "react-router-dom";
import styles from "./NavLeftCreate.module.css";
import s from "classnames";
import add from "../../assets/createIcon.png";
import home2 from "../../assets/homeIcon2.png";
import { createProject } from "../../api/Project";
import useProjectStore from "../../stores/projectStore";
import Swal from "sweetalert2";
import useModalStore from "../../stores/modalStore";
import LoadingModal from "../modal/LoadingModal";

export default function NavLeftCreate({ num }) {
  const navigate = useNavigate();
  const { loadingModal, setLoadingModal } = useModalStore();
  const { setAction } = useModalStore();

  const { createdProject } = useProjectStore();
  const handleCreatProject = async () => {
    if(!createdProject.projectName || !createdProject.projectDomain || !createdProject.git.gitType 
      || !createdProject.git.gitUrl || !createdProject.git.accessToken || !createdProject.git.branch){
      Swal.fire("필수 항목 누락!", "Project 정보를 모두 입력해주세요.", "warning");
      return
    }

    if(!createdProject.frontend.framework || !createdProject.frontend.language || !createdProject.frontend.version ){
      Swal.fire("필수 항목 누락!", "Frontend 정보를 모두 입력해주세요.", "warning");
      return
    }

    setAction("save");
    setLoadingModal(true);
    await createProject(createdProject).then(() => {
      setLoadingModal(false);
      Swal.fire({title: '프로젝트 등록', text: '프로젝트를 성공적으로 등록했습니다.',icon: 'success',confirmButtonColor: '#4FC153'})
        .then(() => {
          navigate("/main");
        });
    }).catch((error) => {
      console.error("등록 실패", error);
      Swal.fire('실패', '프로젝트 등록에 실패했습니다.', 'error');
    });
  };

  return (
    <>
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
      {loadingModal && <LoadingModal/>}
    </>
  );
}
