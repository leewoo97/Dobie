import { useState, useEffect } from "react";
import styles from "./GetBox.module.css";


export default function GetBox({ keyName, valueName}){
    return(
        <div className={styles.page}>
            {/* <p>박스</p> */}
            <div className={styles.boxFrame}>
                <p>{keyName}</p>
                <p>{valueName}</p>
            </div>
        </div>
    );
}