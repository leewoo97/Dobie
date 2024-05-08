import { useState, useEffect } from "react";
import styles from "./DescBox.module.css";


export default function DescBox({ desc, onChange }){
    return(
        <div className={styles.page}>
            {/* <p>박스</p> */}
            <div className={styles.boxFrame}>
                <div className={styles.key}></div>
                <div className={styles.desc}>{desc}</div>
            </div>
        </div>
    );
}