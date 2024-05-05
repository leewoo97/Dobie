import { useNavigate } from "react-router-dom";
import styles from "./NavTop.module.css";
import s from "classnames";

export default function NavTop(){

    return(
        <div className={s(styles.nav)}>
            <p>헤더</p>
        </div>
    );

}