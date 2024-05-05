import { useNavigate } from "react-router-dom";
import styles from "./NavLeft.module.css";
import s from "classnames";

export default function NavLeft(){

    return(
        <div className={s(styles.nav)}>
            <p>왼쪽</p>
        </div>
    );

}