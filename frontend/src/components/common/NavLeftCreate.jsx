import { useNavigate } from "react-router-dom";
import styles from "./NavLeftCreate.module.css";
import s from "classnames";
import mainBtn from "../../assets/btn_main.png";

export default function NavLeftCreate({ num }){
    const navigate = useNavigate();
    return(
        <div className={s(styles.container)}>
            <div className={styles.list}>
                <p className={num === 2 ? styles.text2 : styles.text} onClick={() => navigate("/create/project")}>Project</p>
                <p className={num === 3 ? styles.text2 : styles.text} onClick={() => navigate("/create/backend")}>Backend</p>
                <p className={num === 4 ? styles.text2 : styles.text} onClick={() => navigate("/create/frontend")}>Frontend</p>
                <p className={num === 5 ? styles.text2 : styles.text} onClick={() => navigate("/create/database")}>DB</p>
            </div>
            <img src={mainBtn} alt="search_icon" onClick={() => navigate("/main")} className={styles.img}/>
        </div>
    );

}