import { useState, useEffect } from "react";
import styles from "./FrontendFrame.module.css";
import GetBox from "../common/GetBox";
import ProjectTop from "../common/ProjectTop";
import useProjectStore from "../../stores/projectStore";

export default function FrontendFrame(){
    const {selectedProject, setSelectedProject} = useProjectStore();
    return(
        <div className={styles.page}>
            <ProjectTop />
            <GetBox keyName={"프레임워크"} valueName={selectedProject.frontend.framework}/>
            <GetBox keyName={"언어 버전"} valueName={selectedProject.frontend.language + " " + selectedProject.frontend.version}/>
            <GetBox keyName={"폴더 경로"} valueName={selectedProject.frontend.path}/>
            <GetBox keyName={"nginx location"} valueName={selectedProject.frontend.location}/>
            <GetBox keyName={"내부 포트 번호"} valueName={selectedProject.frontend.internalPort}/>
        </div>
    );
}