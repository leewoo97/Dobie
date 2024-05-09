import { useState, useEffect } from "react";
import styles from "./BackendFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import ProjectTopUpdate from "../common/ProjectTopUpdate";
import useProjectStore from "../../stores/projectStore";

export default function BackendFrame({serviceId}) {

    const {updatedProject, setUpdatedProject} = useProjectStore();
    const [tempProject, setTempProject] = useState({
        ...updatedProject,
        backendMap: {
            ...updatedProject.backendMap,
            [serviceId]: {...updatedProject.backendMap[serviceId]}
        }});

    const frameworkList = [
        "SpringBoot(gradle)",
        "SpringBoot(maven)",
        "Django",
    ]

    const versionList = [
        "java 8",
        "java 11",
        "java 17",
        "python 3.10",
    ]

    const [framework, setFramework] = useState("");
    const [version, setVersion] = useState("");

    const frameworkSelect = (value) => {
        setFramework(value);
        console.log(value);
    };

    const versionSelect = (value) => {
        setVersion(value);
        console.log(value);
    };

    const changePathHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            backendMap: {
                ...prev.backendMap,
                [serviceId]: {
                    ...prev.backendMap[serviceId],
                    path: e.target.value
                }
            }
        }))
    }

    const changeInternalPortHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            backendMap : {
                ...prev.backendMap,
                [serviceId]: {
                    ...prev.backendMap[serviceId],
                    internalPort : Number(e.target.value)
                }
            }
        }))
    }
    useEffect(()=>{
        setUpdatedProject(tempProject);

    },[tempProject])

    return (
        <div className={styles.page}>
            {/* <ProjectTopUpdate updatedProject={updatedProject} /> */}
            <InputSelectBox keyName={"프레임워크"} list={frameworkList} value={framework} onChange={frameworkSelect} />
            <DescBox desc={"Backend 서비스의 프레임워크를 선택하세요"} />
            
            <InputSelectBox keyName={"언어버전"} list={versionList} value={version} onChange={versionSelect} />
            <DescBox desc={"Backend 서비스의 언어 버전을 선택하세요"} />
            
            <InputBox keyName={"폴더 경로"} value={tempProject.backendMap[serviceId].path} valueName={"/frontend"} onChange={changePathHandler} />
            <DescBox desc={"프로젝트 루트 경로로부터 해당 프레임워크 폴더 경로를 작성하세요"} />
            
            {/* <InputBox keyName={"브랜치"} valueName={"dev-be"} />
            <DescBox desc={"해당 프레임워크를 빌드 시킬 브랜치명을 작성하세요"} /> */}
            
            <InputBox keyName={"내부 포트 번호"} value={tempProject.backendMap[serviceId].internalPort} valueName={"8080"}onChange={changeInternalPortHandler}/>
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

            {/* <InputBox keyName={"외부 포트 번호"} valueName={"8080"} />
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} /> */}
        </div>
    );
}