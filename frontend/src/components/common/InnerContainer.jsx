import { useNavigate } from "react-router-dom";
import styles from "./InnerContainer.module.css";
import s from "classnames";
import NavLeft from "./NavLeft";

export default function InnerContainer(){

    return(
        <div className={s(styles.container)}>
            <NavLeft num={1}/>
               {/* 컴포넌트들 넣기 */}
               <div>
                <p>오른쪽</p>
               </div>

        </div>
    );

}