import { useState, useEffect } from "react";
import styles from "./ProjectFrame.module.css";
import GetBox from "../common/GetBox";


export default function ProjectFrame(){

    // const [url, setUrl] = useState(null);
    // const [accessToken, setAccessToken] = useState(null);
    // const [webHook, setWebHook] = useState(null);
    // const [branch, setBranch] = useState(null);

    return(
        <div className={styles.page}>
            <p>프로젝트 틀</p>
            {/* <GetBox keyName={"url"} valueName={url} onChange={(e) => setUrl(e.target.value)}/>
            <GetBox keyName={"액세스 토큰"} valueName={accessToken} onChange={(e)=> setAccessToken(e.target.value)}/>
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={webHook} onChange={(e)=> setWebHook(e.target.value)}/>
            <GetBox keyName={"브랜치"} valueName={branch} onChange={(e)=>setBranch(e.target.value)}/> */}
            <GetBox keyName={"url"} valueName={"url"}/>
            <GetBox keyName={"액세스 토큰"} valueName={"accessToken"}/>
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={"webHook"}/>
            <GetBox keyName={"브랜치"} valueName={"branch"}/>
        </div>
    );
}