import { useState, useEffect } from "react";
import styles from "./GetBox.module.css";


export default function GetBox({ keyName, valueName}){
    return(
        <div className={styles.page}>
            {/* <p>박스</p> */}
            <div className={styles.boxFrame}>
                <p className={styles.key}>{keyName}</p>
                <p className={styles.value}>{valueName}</p>
            </div>
        </div>
    );
}