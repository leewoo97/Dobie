import { useState, useEffect } from "react";
import styles from "./ProjectTop.module.css";
import edit from "../../assets/editIcon.png";
import remove from "../../assets/deleteIcon.png";
import setting from "../../assets/settingIcon.png";

export default function ProjectTop({ projectName }) {
    return (
        <div className={styles.top}>
            <div>
                <div className={styles.text}>프로젝트</div>
                <div className={styles.projectName}>{projectName}</div>
            </div>
            <div className={styles.buttons}>
                <div className={styles.edit}>수정 <img src={edit} alt="" height="20px" decoding="async" className={styles.btnIcon} /></div>
                <div className={styles.remove}>삭제 <img src={remove} alt="" width="23px" decoding="async" className={styles.btnIcon} /></div>
            </div>
        </div>
    );
}