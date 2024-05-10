import { useState, useEffect } from "react";
import {v4 as uuidv4} from "uuid";
import styles from "./BackendFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import ProjectTopCreate from "../common/ProjectTopCreate";
import useProjectStore from "../../stores/projectStore";


export default function BackendFrame() {
    const {createdProject, setCreatedProject} = useProjectStore(); 
    const [tempProject, setTempProject] = useState({...createdProject});
    const [selectedKey, setSelectedKey] = useState(Object.keys(tempProject.backendMap).at(0));
    
    const [selectedBackend, setSelectedBackend] = useState({...tempProject.backendMap[selectedKey]});

    const emptyBackend = {
        serviceName: "",
        language: "",
        version: "",
        framework: "",
        path: "",
        branch: "",
        location: "",
        externalPort: "",
        internalPort: ""
      };

    const clickKeyHandler = (key) => {
        setSelectedKey(key);
    }

    const clickDeleteHandler = (key) => {
        const removeKeyIndex = Object.keys(tempProject.backendMap).indexOf(key);
        const moveKeyIndex = removeKeyIndex === 0 ? 0 : removeKeyIndex-1;
        delete tempProject.backendMap[key];
        setSelectedKey(Object.keys(tempProject.backendMap).at(moveKeyIndex));
        setTempProject({...tempProject});
    }

    const addEmptyBackend = () => {
        const newKey = uuidv4();
        tempProject.backendMap[newKey] = emptyBackend;
        setSelectedKey(newKey);
        setTempProject({...tempProject});
    }

    const changePathHandler = (e) => {
        selectedBackend.path = e.target.value;
        tempProject.backendMap[selectedKey] = {...selectedBackend};
        setTempProject({...tempProject});
    }

    const changeInternalPortHandler = (e) => {
        selectedBackend.internalPort = Number(e.target.value);
        tempProject.backendMap[selectedKey] = {...selectedBackend};
        setTempProject({...tempProject});
    }

    useEffect(() => {
        setCreatedProject({...tempProject});
    }, [tempProject]);

    useEffect(() => {
        setSelectedBackend({...tempProject.backendMap[selectedKey]});
    }, [selectedKey])


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


    
 
    return (
        <div className={styles.page}>
            <ProjectTopCreate />
            {Object.keys(tempProject.backendMap).map((key, index)=>{
                return <div key={index}>
                        <button onClick={()=>clickKeyHandler(key)}>{index+1}</button>
                        <button onClick={()=>clickDeleteHandler(key)} > x </button>
                    </div>
            })}
            <button onClick={addEmptyBackend}>+</button>

            {/* <div>{selectedKey}</div> */}
            
            <InputSelectBox keyName={"프레임워크"} list={frameworkList} value={framework} onChange={frameworkSelect} />
            <DescBox desc={"Backend 서비스의 프레임워크를 선택하세요"} />
            
            <InputSelectBox keyName={"언어버전"} list={versionList} value={version} onChange={versionSelect} />
            <DescBox desc={"Backend 서비스의 언어 버전을 선택하세요"} />
            
            <InputBox keyName={"폴더 경로"} valueName={"/backend"} value={selectedBackend.path} onChange={changePathHandler}/>
            <DescBox desc={"프로젝트 루트 경로로부터 해당 프레임워크 폴더 경로를 작성하세요"} />
            
            {/* <InputBox keyName={"브랜치"} valueName={"dev-be"} value={selectedBackend.branch}/>
            <DescBox desc={"해당 프레임워크를 빌드 시킬 브랜치명을 작성하세요"} /> */}
            
            <InputBox keyName={"내부 포트 번호"} valueName={"8080"} value={selectedBackend.internalPort} onChange=  {changeInternalPortHandler}/>
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

            {/* <InputBox keyName={"외부 포트 번호"} valueName={"8080"} value={selectedBackend.externalPort}/>
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} /> */}
        </div>
    );
}