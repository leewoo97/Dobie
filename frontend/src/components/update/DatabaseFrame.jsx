import { useState, useEffect } from "react";
import styles from "./DatabaseFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import useProjectStore from "../../stores/projectStore";
import { v4 as uuidv4 } from "uuid";
import Swal from "sweetalert2";
import notFound from "../../assets/notFound.PNG";

export default function DatabaseFrame() {

    const { updatedProject, setUpdatedProject } = useProjectStore();
    const [tempProject, setTempProject] = useState({ ...updatedProject });
    const [selectedKey, setSelectedKey] = useState(Object.keys(tempProject.databaseMap).at(0));
    const [selectedDatabase, setSelectedDatabase] = useState({ ...tempProject.databaseMap[selectedKey] });

    const clickDeleteHandler = (key) => {
        Swal.fire({
            icon: 'warning',
            title: 'DB 삭제',
            text: '해당 DB를 삭제 하시면 복구시킬 수 없습니다.',
            showCancelButton: true,
            confirmButtonColor: '#4FC153',
            cancelButtonColor: '#FF5370',
            confirmButtonText: '예, 삭제합니다!',
            cancelButtonText: '아니요, 취소합니다!'
        }).then((result) => {
            if (result.isConfirmed) {
                const removeKeyIndex = Object.keys(tempProject.databaseMap).indexOf(key);
                const moveKeyIndex = removeKeyIndex === 0 ? 0 : removeKeyIndex - 1;
                delete tempProject.databaseMap[key];
                setSelectedKey(Object.keys(tempProject.databaseMap).at(moveKeyIndex));
                setTempProject({ ...tempProject });
                Swal.fire({
                    title: '삭제 완료!',
                    text: '해당 DB가 성공적으로 삭제되었습니다.',
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

    const clickKeyHandler = (key) => {
        setSelectedKey(key);
    }

    const emptyDatabase = {
        databaseId:"",
        databaseType: "",
        databaseName: "",
        schemaPath: "",
        username: "",
        password: "",
        internalPort: 0
    }
    const addEmptyDatabase = () => {
        const newKey = uuidv4();
        tempProject.databaseMap[newKey] = emptyDatabase;
        tempProject.databaseMap[newKey].databaseId = newKey;
        setSelectedKey(newKey);
        setTempProject({ ...tempProject });
    }

    const changeDatabaseHandler = (value) => {
        selectedDatabase.databaseType = value;
        tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
        setTempProject({ ...tempProject });
    }

    const changeUsernameHandler = (e) => {
        selectedDatabase.username = e.target.value;
        tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
        setTempProject({ ...tempProject });
    }

    const changePasswordHandler = (e) => {
        selectedDatabase.password = e.target.value;
        tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
        setTempProject({ ...tempProject });
    }

    const changeSchemaPathHandler = (e) => {
        selectedDatabase.schemaPath = e.target.value;
        tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
        setTempProject({ ...tempProject });
    }

    const changeInternalPortHandler = (e) => {
        selectedDatabase.internalPort = Number(e.target.value);
        tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
        setTempProject({ ...tempProject });
    }

    useEffect(() => {
        setUpdatedProject(tempProject);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [tempProject]);

    useEffect(() => {
        setSelectedDatabase({ ...tempProject.databaseMap[selectedKey] });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [selectedKey]);

    const databaseList = [
        "Mysql",
        "Mongodb",
        "Redis",
    ]

    return (
        <div className={styles.page}>
            <div className={styles.tapBox}>
                {Object.keys(tempProject.databaseMap).map((key, index) => {
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
                <p onClick={addEmptyDatabase} className={styles.tapPlus}>+</p>
            </div>

            <div className={Object.keys(tempProject.databaseMap).length !== 0 ? styles.hide : styles.notHide} >
                <img src={notFound} alt="" className={styles.notFound}/>
            </div>

            <div className={Object.keys(tempProject.databaseMap).length === 0 ? styles.hide : ""}>

                <InputSelectBox keyName={"데이터베이스"} list={databaseList} value={selectedDatabase.databaseType} onChange={changeDatabaseHandler} />
                <DescBox desc={"Database 서비스의 프레임워크를 선택하세요"} />

                <InputBox keyName={"Username"} value={selectedDatabase.username} valueName={"username"} onChange={changeUsernameHandler} />
                <DescBox desc={"데이터베이스에 설정한 Username을 작성해주세요"} />

                <InputBox keyName={"Password"} value={selectedDatabase.password} valueName={"password"} onChange={changePasswordHandler} />
                <DescBox desc={"데이터베이스에 설정한 Password를 작성해주세요"} />

                <InputBox keyName={"init.sql 경로"} value={selectedDatabase.schemaPath} valueName={"/path/to/mysql/schema.sql"} onChange={changeSchemaPathHandler} />
                <DescBox desc={"스키마 파일이 있다면 해당 파일 폴더 경로를 작성하세요"} />

                <InputBox keyName={"내부 포트 번호"} value={selectedDatabase.internalPort} valueName={"3306"} onChange={changeInternalPortHandler} />
                <DescBox desc={"해당 프레임워크가 사용할 내부포트 번호를 지정해주세요"} />

            </div>
        </div>
    );
}