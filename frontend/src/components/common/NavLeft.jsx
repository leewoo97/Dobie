import { useNavigate } from "react-router-dom";
import styles from "./NavLeft.module.css";
import s from "classnames";
import mainBtn from "../../assets/btn_main.png";
import home2 from "../../assets/homeIcon2.png";
import useProjectStore from "../../stores/projectStore";
import { useParams } from "react-router-dom";
import { useState } from "react";

export default function NavLeft({ num }){
    const navigate = useNavigate();
    const { selectedProject, setSelectedProject } = useProjectStore();

    const params = useParams();
    const serviceId = params.serviceId;
    const databaseId = params.databaseId;

    const [spreadBackend, setSpreadBackend] = useState(serviceId? true : false);
    const [spreadDatabase, setSpreadDatabase] = useState(databaseId? true : false);

    const changeStateBE = () => {
        if(!spreadBackend){
            setSpreadBackend(true);
            navigate(`/manage/backend/${Object.values(selectedProject.backendMap).at(0).serviceId}`);
        }
    
    }

    const changeStateDB = () => {
        if(!spreadDatabase){
            setSpreadBackend(true);
            navigate(`/manage/database/${Object.values(selectedProject.databaseMap).at(0).databaseId}`)
        }
    }

    return(
        <div className={s(styles.container)}>
            <div className={styles.list}>
                <p className={num === 1 ? styles.text2 : styles.text} onClick={() => navigate("/manage")}>Run</p>
                <p className={num === 2 ? styles.text2 : styles.text} onClick={() => navigate("/manage/project")}>Project</p>
                <div>
                <p className={num === 3 ? styles.text2 : styles.text} onClick={changeStateBE}>Backend</p>
                {/* 여기는 BackendMap */}
                {Object.values(selectedProject.backendMap).map((backendProject) => (
                    <div className={[spreadBackend? "" : styles.hide, backendProject.serviceId === serviceId ? styles.text3 : ""].join(" ")} 
                    key={backendProject.serviceId} onClick={()=> navigate(`/manage/backend/${backendProject.serviceId}`) }>{backendProject.framework}</div>
                ))}
                </div>
                <p className={num === 4 ? styles.text2 : styles.text} onClick={() => navigate("/manage/frontend")}>Frontend</p>
                <div>
                <p className={num === 5 ? styles.text2 : styles.text} onClick={changeStateDB}>DB</p>
                {/* 여기는 DatabaseMap */}
                {Object.values(selectedProject.databaseMap).map((databaseProject) => (
                    <div className={[spreadDatabase? "" : styles.hide, databaseProject.databaseId === databaseId ? styles.text3 : ""].join(" ")} 
                    key={databaseProject.databaseId} onClick={() => navigate(`/manage/database/${databaseProject.databaseId}`)}>{databaseProject.databaseType}</div>
                ))}
                </div>
            </div>
            <div className={styles.buttons}>
                <div className={styles.home} onClick={() => navigate("/main")}>메인페이지 <img src={home2} alt="" decoding="async" className={styles.homeIcon} /></div>
                {/* <img src={mainBtn} alt="search_icon" onClick={() => navigate("/main")} className={styles.img}/> */}
            </div>
        </div>
    );

}