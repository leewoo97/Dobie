import { useNavigate } from "react-router-dom";
import styles from "./NavTop.module.css";
import s from "classnames";
import mascot from "../../assets/mascot.png";

export default function NavTop() {

    return (
        <div className={s(styles.nav)}>
            <div className={styles.title}>
                <img src={mascot} alt="" width="80px" decoding="async" />
                <div className={styles.dobie}>Dobie</div>
            </div>
        </div>
    );

}