import { useState, useEffect } from "react";
import styles from "./BackendFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import { v4 as uuidv4 } from "uuid";
import useProjectStore from "../../stores/projectStore";
import Swal from "sweetalert2";

export default function BackendFrame() {

    const { updatedProject, setUpdatedProject } = useProjectStore();
    const [tempProject, setTempProject] = useState({ ...updatedProject, });
    const [selectedKey, setSelectedKey] = useState(Object.keys(tempProject.backendMap).at(0));
    const [selectedBackend, setSelectedBackend] = useState({ ...tempProject.backendMap[selectedKey] });

    // console.log(selectedBackend);
    // console.log(tempProject.backendMap);
    const clickKeyHandler = (key) => {
        setSelectedKey(key);
    }
    const clickDeleteHandler = (key) => {
        Swal.fire({
            icon: 'warning',
            title: '프로젝트 삭제',
            text: '해당 프로젝트를 삭제 하시면 복구시킬 수 없습니다.',
            showCancelButton: true,
            confirmButtonColor: '#4FC153',
            cancelButtonColor: '#FF5370',
            confirmButtonText: '예, 삭제합니다!',
            cancelButtonText: '아니요, 취소합니다!'
        }).then((result) => {
            if (result.isConfirmed) {
                const removeKeyIndex = Object.keys(tempProject.backendMap).indexOf(key);
                const moveKeyIndex = removeKeyIndex === 0 ? 0 : removeKeyIndex - 1;
                delete tempProject.backendMap[key];
                setSelectedKey(Object.keys(tempProject.backendMap).at(moveKeyIndex));
                setTempProject({ ...tempProject });
                Swal.fire({
                    title: '삭제 완료!',
                    text: '해당 백엔드 프로젝트가 성공적으로 삭제되었습니다.',
                    icon: 'success',
                    confirmButtonColor: '#4FC153',
                    showCancelButton: false,
                    confirmButtonText: 'OK'
                })
            }
        }).catch((error) => {
            console.error("삭제 중 오류 발생: ", error);
            Swal.fire(
                '삭제 오류!',
                '삭제 처리 중 문제가 발생했습니다.',
                'error'
            );
        });
    }

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

    const addEmptyBackend = () => {
        const newKey = uuidv4();
        tempProject.backendMap[newKey] = emptyBackend;
        setSelectedKey(newKey);
        setTempProject({ ...tempProject });
    }

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
    const changeFrameworkHandler = (value) => {
        selectedBackend.framework = value;
        tempProject.backendMap[selectedKey] = { ...selectedBackend };
        setTempProject({ ...tempProject });
    }
    const changeVersionHandler = (value) => {
        const splitValue = value.split(' ');
        selectedBackend.language = splitValue[0];
        selectedBackend.version = splitValue[1];
        tempProject.backendMap[selectedKey] = { ...selectedBackend };
        setTempProject({ ...tempProject });
    }

    const changePathHandler = (e) => {
        selectedBackend.path = e.target.value;
        tempProject.backendMap[selectedKey] = { ...selectedBackend };
        setTempProject({ ...tempProject });
    }

    const changeInternalPortHandler = (e) => {
        selectedBackend.internalPort = Number(e.target.value);
        tempProject.backendMap[selectedKey] = { ...selectedBackend };
        setTempProject({ ...tempProject });
    }
    // 컴포넌트가 업데이트 될 때마다 상위 스토어의 상태를 업데이트
    useEffect(() => {
        setUpdatedProject(tempProject);
    }, [tempProject]);

    useEffect(() => {
        setSelectedBackend({ ...tempProject.backendMap[selectedKey] });
    }, [selectedKey]);

    const [showToken, setShowToken] = useState(false);
    const toggleTokenVisibility = () => {
      setShowToken(!showToken);
    };
    return (
        <div className={styles.page}>

            <div className={styles.tapBox}>
                {Object.keys(tempProject.backendMap).map((key, index) => {
                    return (
                        <div key={index} className={key === selectedKey ? styles.selectedTap : styles.tap} onClick={() => clickKeyHandler(key)}>
                            <div>{index + 1}</div>
                            <div className={styles.xMark} onClick={(event) => {
                                event.stopPropagation();
                                clickDeleteHandler(key);
                            }}> x </div>
                            
                        </div>
                    );
                })}
                <p onClick={addEmptyBackend} className={styles.tapPlus}>+</p>
            </div>

            <div className={Object.keys(tempProject.backendMap).length !== 0 ? styles.hide : ""}>
                프로젝트가 모두 삭제되었습니다. 백엔드 프로젝트를 등록해주세요.
            </div>

            <InputSelectBox keyName={"프레임워크"} list={frameworkList} value={selectedBackend.framework} onChange={changeFrameworkHandler} />
            <DescBox desc={"Backend 서비스의 프레임워크를 선택하세요"} />

            <InputSelectBox keyName={"언어버전"} list={versionList} value={selectedBackend.language + " " + selectedBackend.version} onChange={changeVersionHandler} />
            <DescBox desc={"Backend 서비스의 언어 버전을 선택하세요"} />

            <InputBox keyName={"폴더 경로"} value={selectedBackend.path} valueName={"/frontend"} onChange={changePathHandler} />
            <DescBox desc={"프로젝트 루트 경로로부터 해당 프레임워크 폴더 경로를 작성하세요"} />

            {/* <InputBox keyName={"브랜치"} valueName={"dev-be"} />
            <DescBox desc={"해당 프레임워크를 빌드 시킬 브랜치명을 작성하세요"} /> */}

            <InputBox keyName={"내부 포트 번호"} value={selectedBackend.internalPort} valueName={"8080"} onChange={changeInternalPortHandler} />
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

            {/* <InputBox keyName={"외부 포트 번호"} valueName={"8080"} />
            <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} /> */}
        </div>
    );
}