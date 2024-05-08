import { useState, useEffect } from "react";
import styles from "./ProjectFrame.module.css";
import GetBox from "../common/GetBox";
import ProjectTop from "../common/ProjectTop";
import useProjectStore from "../../stores/projectStore";

export default function ProjectFrame() {

    const {selectedProject, setSelectedProject} = useProjectStore();

    return (
        <div className={styles.page}>
            <ProjectTop />
            {/* <GetBox keyName={"url"} valueName={url} onChange={(e) => setUrl(e.target.value)}/>
            <GetBox keyName={"액세스 토큰"} valueName={accessToken} onChange={(e)=> setAccessToken(e.target.value)}/>
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={webHook} onChange={(e)=> setWebHook(e.target.value)}/>
            <GetBox keyName={"브랜치"} valueName={branch} onChange={(e)=>setBranch(e.target.value)}/> */}
            <GetBox keyName={"url"} valueName={selectedProject.git.gitUrl} />
            <GetBox keyName={"액세스 토큰"} valueName={selectedProject.git.accessToken} />
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={"webHook"} />
            <GetBox keyName={"브랜치"} valueName={selectedProject.backendMap.branch} />
        </div>
    );
}