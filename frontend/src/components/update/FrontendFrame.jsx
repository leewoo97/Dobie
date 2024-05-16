import { useState, useEffect } from "react";
import styles from "./FrontendFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import useProjectStore from "../../stores/projectStore";

export default function FrontendFrame() {

    const { updatedProject, setUpdatedProject } = useProjectStore();
    const [tempProject, setTempProject] = useState({ ...updatedProject });

    const frameworkList = ["React", "Vue"];
    const versionList = ["node 20.11.0"];

    const changeFrameworkHandler = (value) => {
        setTempProject((prev) => ({
            ...prev,
            frontend: {
                ...prev.frontend,
                framework: value,
            },
        }));
    };

    const changeVersionHandler = (value) => {
        const splitValue = value.split(' ');
        setTempProject((prev) => ({
            ...prev,
            frontend: {
                ...prev.frontend,
                language: splitValue[0],
                version: splitValue[1],
            }
        }))
    }
    const changePathHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            frontend: {
                ...prev.frontend,
                path: e.target.value
            }
        }))
    }

    const changeLocationHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            frontend: {
                ...prev.frontend,
                location: e.target.value
            }
        }))
    }

    const changeInternalPortHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            frontend: {
                ...prev.frontend,
                internalPort: Number(e.target.value)
            }
        }))
    }

    useEffect(() => {
        setUpdatedProject(tempProject);

    }, [tempProject])

    return (
        <div className={styles.page}>
            <InputSelectBox keyName={"프레임워크"} list={frameworkList} value={tempProject.frontend.framework} onChange={changeFrameworkHandler} />
            <DescBox desc={"Frontend 서비스의 프레임워크를 선택하세요"} />

            <InputSelectBox keyName={"언어버전"} list={versionList} value={tempProject.frontend.language+" "+tempProject.frontend.version} onChange={changeVersionHandler} />
            <DescBox desc={"Frontend 서비스의 언어 버전을 선택하세요"} />

            <InputBox keyName={"폴더 경로"} value={tempProject.frontend.path} valueName={"/frontend"} onChange={changePathHandler} />
            <DescBox desc={"프로젝트 루트 경로로부터 해당 프레임워크 폴더 경로를 작성하세요"} />

            <InputBox keyName={"Nginx location"} value={tempProject.frontend.location} valueName={"/api"} onChange={changeLocationHandler} />
            <DescBox desc={"Nginx location을 작성하세요"} />

            <InputBox keyName={"내부 포트 번호"} value={tempProject.frontend.internalPort} valueName={"3000"} onChange={changeInternalPortHandler} />
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

        </div>
    );
}