import { useState, useEffect } from "react";
import styles from "./BackendFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import ProjectTopCreate from "../common/ProjectTopCreate";

export default function DatabaseFrame() {

    const frameworkList = [
        "Mysql",
        "Mongodb",
        "Redis",
    ]

    const [framework, setFramework] = useState("");

    const frameworkSelect = (value) => {
        setFramework(value);
        console.log(value);
    };

    return (
        <div className={styles.page}>
            <ProjectTopCreate />
            <InputSelectBox keyName={"프레임워크"} list={frameworkList} value={framework} onChange={frameworkSelect} />
            <DescBox desc={"Database 서비스의 프레임워크를 선택하세요"} />
            
            <InputBox keyName={"Username"} valueName={"root"} />
            <DescBox desc={"데이터베이스에 설정한 Username을 작성해주세요"} />
            
            <InputBox keyName={"Password"} valueName={"ssafy"} />
            <DescBox desc={"데이터베이스에 설정한 Password를 작성해주세요"} />
            
            <InputBox keyName={"init.sql 경로"} valueName={"/DB"} />
            <DescBox desc={"스키마 파일이 있다면 해당 파일 폴더 경로를 작성하세요"} />
            
            <InputBox keyName={"내부 포트 번호"} valueName={"3306"} />
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

            <InputBox keyName={"외부 포트 번호"} valueName={"3306"} />
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />
        </div>
    );
}