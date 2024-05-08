import { useState, useEffect } from "react";
import styles from "./ProjectFrame.module.css";
import InputBox from "../common/InputBox";
import DescBox from "../common/DescBox";
import ProjectTopCreate from "../common/ProjectTopCreate";

export default function ProjectFrame() {

    // const [url, setUrl] = useState(null);
    // const [accessToken, setAccessToken] = useState(null);
    // const [webHook, setWebHook] = useState(null);
    // const [branch, setBranch] = useState(null);

    return (
        <div className={styles.page}>
            <ProjectTopCreate />
            {/* <GetBox keyName={"url"} valueName={url} onChange={(e) => setUrl(e.target.value)}/>
            <GetBox keyName={"액세스 토큰"} valueName={accessToken} onChange={(e)=> setAccessToken(e.target.value)}/>
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={webHook} onChange={(e)=> setWebHook(e.target.value)}/>
            <GetBox keyName={"브랜치"} valueName={branch} onChange={(e)=>setBranch(e.target.value)}/> */}
            <InputBox keyName={"url"} valueName={"url"} />
            <DescBox desc={"GitLab 또는 GitHub 의 프로젝트를 클론하기 위한 URL을 등록하세요 "} />
            <InputBox keyName={"액세스 토큰"} valueName={"accessToken"} />
            <DescBox desc={"Git 저장소에 접근하기 위한 엑세스 토큰을 발급하여 등록하세요 "} />
            <InputBox keyName={"브랜치"} valueName={"branch"} />
        </div>
    );
}