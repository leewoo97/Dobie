import { useNavigate } from "react-router-dom";
import styles from "./NavLeft.module.css";
import s from "classnames";
import mainBtn from "../../assets/btn_main.png";
import usePrjectStore from "../../stores/projectStore";
import { useParams } from "react-router-dom";

export default function NavLeft({ num }){
    const navigate = useNavigate();
    const { selectedProject, setSelectedProject } = usePrjectStore();

    const params = useParams();
    const serviceId = params.serviceId;
    const databaseId = params.databaseId;


    return(
        <div className={s(styles.container)}>
            <div className={styles.list}>
                <p className={num === 1 ? styles.text2 : styles.text} onClick={() => navigate("/manage")}>Run</p>
                <p className={num === 2 ? styles.text2 : styles.text} onClick={() => navigate("/manage/project")}>Project</p>
                <div>
                <p className={num === 3 ? styles.text2 : styles.text} >Backend</p>
                {/* 여기는 BackendMap */}
                {Object.values(selectedProject.backendMap).map((backendProject) => (
                    <div className={backendProject.serviceId === serviceId ? styles.text3 : ""} key={backendProject.serviceId} onClick={()=> navigate(`/manage/backend/${backendProject.serviceId}`) }>{backendProject.framework}</div>
                ))}
                </div>
                <p className={num === 4 ? styles.text2 : styles.text} onClick={() => navigate("/manage/frontend")}>Frontend</p>
                <div>
                <p className={num === 5 ? styles.text2 : styles.text} >DB</p>
                {/* 여기는 DatabaseMap */}
                {Object.values(selectedProject.databaseMap).map((databaseProject) => (
                    <div className={databaseProject.databaseId === databaseId ? styles.text3 : ""} key={databaseProject.databaseId} onClick={() => navigate(`/manage/database/${databaseProject.databaseId}`)}>{databaseProject.databaseType}</div>
                ))}
                </div>
            </div>
            <img src={mainBtn} alt="search_icon" onClick={() => navigate("/main")} className={styles.img}/>
        </div>
    );

}