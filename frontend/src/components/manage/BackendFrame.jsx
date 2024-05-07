import { useState, useEffect } from "react";
import styles from "./ProjectFrame.module.css";
import GetBox from "../common/GetBox";

export default function BackendFrame(){
    return(
        <div className={styles.page}>
            <p>백엔드 틀</p>
            <GetBox keyName={"url"} valueName={"http://abcdefg.com"}/>
            <GetBox keyName={"액세스 토큰"} valueName={"FLKJ298J4F2IUFN9283JFLKSDJFSLD"}/>
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={"머랑ㅁ ㅓㄹ;ㅣㅇ라ㅣㅁ"}/>
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={"머랑ㅁ ㅓㄹ;ㅣㅇ라ㅣㅁ"}/>
        </div>
    );
}