import { useState, useEffect } from "react";
import styles from "./DatabaseFrame.module.css";
import GetBox from "../common/GetBox";
import ProjectTop from "../common/ProjectTop";
import useProjectStore from "../../stores/projectStore";
import { useParams } from "react-router-dom"; 

export default function DatabaseFrame(){
    const {selectedProject, setSelectedProject} = useProjectStore();

    const params = useParams();

    const serviceId = params.databaseId;
    const selectedDatabase = new Map(Object.entries(selectedProject.databaseMap)).get(serviceId);
    return(
        <div className={styles.page}>
            <ProjectTop />
            <GetBox keyName={"프레임워크"} valueName={selectedDatabase.databaseType}/>
            <GetBox keyName={"Username"} valueName={selectedDatabase.username}/>
            <GetBox keyName={"Password"} valueName={selectedDatabase.password}/>
            <GetBox keyName={"내부 포트 번호"} valueName={selectedDatabase.internalPort}/>
        </div>
    );
}