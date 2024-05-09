import { useState, useEffect } from "react";
import styles from "./ProjectTopCreate.module.css";
import remove from "../../assets/deleteIcon.png";
import add from "../../assets/createIcon.png"

export default function GetBox({ keyName, valueName }) {
    return (
        <div className={styles.top}>
            <div>
                <input className={styles.title} placeholder="프로젝트명"></input>
                <div className={styles.text}>Git에 등록한 Project명 또는 Repository명을 등록하세요 <br></br>( = 최상단 폴더 이름)</div>
            </div>
            <div className={styles.buttons}>
                <div className={styles.add}>등록 <img src={add} alt="" decoding="async" className={styles.btnIcon} /></div>
            </div>
        </div>
    );
}