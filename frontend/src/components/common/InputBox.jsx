import { useState, useEffect } from "react";
import styles from "./InputBox.module.css";


export default function InputBox({ keyName, valueName, onChange }){
    return(
        <div className={styles.page}>
            {/* <p>박스</p> */}
            <div className={styles.boxFrame}>
                <p className={styles.key}>{keyName}</p>
                <input className={styles.value} type="text" placeholder={valueName} onChange={onChange}/>
            </div>
        </div>
    );
}