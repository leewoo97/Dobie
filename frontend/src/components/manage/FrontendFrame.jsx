import { useState, useEffect } from "react";
import styles from "./ProjectFrame.module.css";
import GetBox from "../common/GetBox";

export default function FrontendFrame(){
    return(
        <div className={styles.page}>
            <p>프론트 틀</p>
            <GetBox keyName={"프레임워크"} valueName={"http://abcdefg.com"}/>
            <GetBox keyName={"언어 버전"} valueName={"FLKJ298J4F2IUFN9283JFLKSDJFSLD"}/>
            <GetBox keyName={"폴더 경로"} valueName={"머랑ㅁ ㅓㄹ;ㅣㅇ라ㅣㅁ"}/>
            <GetBox keyName={"nginx location"} valueName={"머랑ㅁ ㅓㄹ;ㅣㅇ라ㅣㅁ"}/>
            <GetBox keyName={"내부 포트 번호"} valueName={"머랑ㅁ ㅓㄹ;ㅣㅇ라ㅣㅁ"}/>
            <GetBox keyName={"외부 포트 번호"} valueName={"머랑ㅁ ㅓㄹ;ㅣㅇ라ㅣㅁ"}/>
        </div>
    );
}