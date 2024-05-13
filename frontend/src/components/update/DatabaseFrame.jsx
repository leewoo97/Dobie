import { useState, useEffect } from "react";
import styles from "./DatabaseFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import useProjectStore from "../../stores/projectStore";
import {v4 as uuidv4} from "uuid";

export default function DatabaseFrame() {

    const {updatedProject, setUpdatedProject} = useProjectStore();
    const [tempProject, setTempProject] = useState({...updatedProject });
    const [selectedKey, setSelectedKey] = useState(Object.keys(tempProject.databaseMap).at(0));
    const [selectedDatabase, setSelectedDatabase] = useState({...tempProject.databaseMap[selectedKey]});

    const clickDeleteHandler = (key) => {
        const removeKeyIndex = Object.keys(tempProject.databaseMap).indexOf(key);
        const moveKeyIndex = removeKeyIndex === 0 ? 0 : removeKeyIndex-1;
        delete tempProject.databaseMap[key];
        setSelectedKey(Object.keys(tempProject.databaseMap).at(moveKeyIndex));
        setTempProject({...tempProject});
    }

    const clickKeyHandler = (key) => {
        setSelectedKey(key);
        setSelectedDatabase()
    }

    const emptyDatabase = {
        databaseType: "",
        databaseName: "",
        schemaPath: "",
        username: "",
        password: "",
        externalPort: "",
        internalPort: ""
    }
    const addEmptyDatabase = () =>{
        const newKey = uuidv4();
        tempProject.databaseMap[newKey] = emptyDatabase;
        setSelectedDatabase(newKey);
        setTempProject({...tempProject});
    }

    const changeUsernameHandler = (e) => {
        selectedDatabase.username = e.target.value;
        tempProject.databaseMap[selectedKey] = {...selectedDatabase};
        setTempProject({...tempProject});
    }

    const changePasswordHandler = (e) => {
        selectedDatabase.password = e.target.value;
        tempProject.databaseMap[selectedKey] = {...selectedDatabase};
        setTempProject({...tempProject});
    }

    const changePathHandler = (e) => {
        selectedDatabase.path = e.target.value;
        tempProject.databaseMap[selectedKey] = {...selectedDatabase};
        setTempProject({...tempProject});
    }

    const changeInternalPortHandler = (e) => {
        selectedDatabase.internalPort = Number(e.target.value);
        tempProject.backendMap[selectedKey] = {...selectedDatabase};
        setTempProject({...tempProject});
    }

    useEffect(()=> {
        setUpdatedProject(tempProject);
    },[tempProject]);

    useEffect(()=> {
        setSelectedDatabase({...tempProject.databaseMap[selectedKey]});
    },[selectedKey]);

    const frameworkList = [
        "Mysql",
        "Mongodb",
        "Redis",
    ]

    const [framework, setFramework] = useState("");

    const frameworkSelect = (value) => {
        setFramework(value);
        // console.log(value);
    };

    return (
        <div className={styles.page}>
            {/* <ProjectTopUpdate updatedProject={updatedProject} /> */}

            {Object.keys(tempProject.databaseMap).map((key, index) => {
                return <div key={index}>    
                        <button onClick={()=> clickKeyHandler(key)}>{index+1}</button>
                        <button onClick={()=> clickDeleteHandler(key)}> x </button>
                    </div>
            })}
            <button onClick={()=> addEmptyDatabase}> + </button>

            <InputSelectBox keyName={"프레임워크"} list={frameworkList} value={framework} onChange={frameworkSelect} />
            <DescBox desc={"Database 서비스의 프레임워크를 선택하세요"} />
            
            <InputBox keyName={"Username"} value={selectedDatabase.username} valueName={"username"} onChange={changeUsernameHandler}/>
            <DescBox desc={"데이터베이스에 설정한 Username을 작성해주세요"} />
            
            <InputBox keyName={"Password"} value={selectedDatabase.password} valueName={"password"} onChange={changePasswordHandler}/>
            <DescBox desc={"데이터베이스에 설정한 Password를 작성해주세요"} />
            
            <InputBox keyName={"init.sql 경로"} value={selectedDatabase.schemaPath} valueName={"/path/to/mysql/schema.sql"} onChange={changePathHandler}/>
            <DescBox desc={"스키마 파일이 있다면 해당 파일 폴더 경로를 작성하세요"} />
            
            <InputBox keyName={"내부 포트 번호"} value={selectedDatabase.internalPort} valueName={"3306"} onChange={changeInternalPortHandler} />
            <DescBox desc={"해당 프레임워크가 사용할 내부포트 번호를 지정해주세요"} />

            {/* <InputBox keyName={"외부 포트 번호"} valueName={"3306"} />
            <DescBox desc={"해당 프레임워크가 사용할 외부포트 번호를 지정해주세요"} /> */}
        </div>
    );
}