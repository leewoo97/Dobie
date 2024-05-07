import { useNavigate } from "react-router-dom";
import styles from "./InnerContainer.module.css";
import s from "classnames";
import NavLeft from "./NavLeft";
import { useState, useEffect } from "react";

export default function InnerContainer({ num }){

    // const [num, setNum] = useState(null);
    return(
        <div className={s(styles.container)}>
            <NavLeft num={num}/>
               {/* 컴포넌트들 넣기 */}
               <div>
                <p>오른쪽</p>
               </div>
        </div>
    );

}