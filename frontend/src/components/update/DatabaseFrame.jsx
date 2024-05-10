import { useState, useEffect } from "react";
import styles from "./DatabaseFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import ProjectTopUpdate from "../common/ProjectTopUpdate";
import useProjectStore from "../../stores/projectStore";

export default function DatabaseFrame({databaseId}) {

    const {updatedProject, setUpdatedProject} = useProjectStore();
    const [tempProject, setTempProject] = useState({
        ...updatedProject,
        databaseMap: {
            ...updatedProject.databaseMap,
            [databaseId] : {...updatedProject.databaseMap[databaseId]}
        }
    });

    const changeUsernameHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            databaseMap:{
                ...prev.databaseMap,
                [databaseId]: {
                    ...prev.databaseMap[databaseId],
                    username: e.target.value
                }
            }
        }))
    }

    const changePasswordHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            databaseMap:{
                ...prev.databaseMap,
                [databaseId]: {
                    ...prev.databaseMap[databaseId],
                    password: e.target.value
                }
            }
        }))
    }

    const changePathHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            databaseMap:{
                ...prev.databaseMap,
                [databaseId]: {
                    ...prev.databaseMap[databaseId],
                    schemaPath: e.target.value
                }
            }
        }))
    }

    const changeInternalPortHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            databaseMap:{
                ...prev.databaseMap,
                [databaseId]: {
                    ...prev.databaseMap[databaseId],
                    internalPort: e.target.value
                }
            }
        }))
    }

    useEffect(()=> {
        setUpdatedProject(tempProject);
    },[tempProject])

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
            <InputSelectBox keyName={"프레임워크"} list={frameworkList} value={framework} onChange={frameworkSelect} />
            <DescBox desc={"Database 서비스의 프레임워크를 선택하세요"} />
            
            <InputBox keyName={"Username"} value={tempProject.databaseMap[databaseId].username} valueName={"username"} onChange={changeUsernameHandler}/>
            <DescBox desc={"데이터베이스에 설정한 Username을 작성해주세요"} />
            
            <InputBox keyName={"Password"} value={tempProject.databaseMap[databaseId].password} valueName={"password"} onChange={changePasswordHandler}/>
            <DescBox desc={"데이터베이스에 설정한 Password를 작성해주세요"} />
            
            <InputBox keyName={"init.sql 경로"} value={tempProject.databaseMap[databaseId].schemaPath} valueName={"/path/to/mysql/schema.sql"} onChange={changePathHandler}/>
            <DescBox desc={"스키마 파일이 있다면 해당 파일 폴더 경로를 작성하세요"} />
            
            <InputBox keyName={"내부 포트 번호"} value={tempProject.databaseMap[databaseId].internalPort} valueName={"3306"} onChange={changeInternalPortHandler} />
            <DescBox desc={"해당 프레임워크가 사용할 내부포트 번호를 지정해주세요"} />

            {/* <InputBox keyName={"외부 포트 번호"} valueName={"3306"} />
            <DescBox desc={"해당 프레임워크가 사용할 외부포트 번호를 지정해주세요"} /> */}
        </div>
    );
}