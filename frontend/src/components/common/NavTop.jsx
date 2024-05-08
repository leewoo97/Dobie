import { useNavigate } from "react-router-dom";
import styles from "./NavTop.module.css";
import s from "classnames";
import mascot from "../../assets/mascot.png";

export default function NavTop() {

    const navigate = useNavigate();
    return (
        <div className={s(styles.nav)}>
            <div className={styles.title} onClick={()=> navigate("/main")}>
                <img src={mascot} alt="" height="90%" decoding="async" />
                <div className={styles.dobie}>Dobie</div>
            </div>
        </div>
    );

}