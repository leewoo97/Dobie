import { useEffect, useState } from "react";
import { v4 as uuidv4 } from "uuid";
import styles from "./DatabaseFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import useProjectStore from "../../stores/projectStore";
import Swal from "sweetalert2";

export default function DatabaseFrame() {
  const { createdProject, setCreatedProject } = useProjectStore();
  const [tempProject, setTempProject] = useState({ ...createdProject });
  const [selectedKey, setSelectedKey] = useState(
    Object.keys(tempProject.databaseMap).at(0)
  );

  const [selectedDatabase, setSelectedDatabase] = useState({
    ...tempProject.databaseMap[selectedKey],
  });

  const emptyDatabase = {
    databaseType: "",
    databaseName: "",
    schemaPath: "",
    username: "",
    password: "",
    externalPort: "",
    internalPort: "",
  };

  const clickKeyHandler = (key) => {
    setSelectedKey(key);
  };

  const clickDeleteHandler = (key) => {
    Swal.fire({
      icon: "warning",
      title: "DB 삭제",
      text: "해당 DB를 삭제 하시면 복구시킬 수 없습니다.",
      showCancelButton: true,
      confirmButtonColor: "#4FC153",
      cancelButtonColor: "#FF5370",
      confirmButtonText: "예, 삭제합니다!",
      cancelButtonText: "아니요, 취소합니다!",
    })
      .then((result) => {
        if (result.isConfirmed) {
          const removeKeyIndex = Object.keys(tempProject.databaseMap).indexOf(
            key
          );
          const moveKeyIndex = removeKeyIndex === 0 ? 0 : removeKeyIndex - 1;
          delete tempProject.databaseMap[key];
          setSelectedKey(Object.keys(tempProject.databaseMap).at(moveKeyIndex));
          setTempProject({ ...tempProject });
          Swal.fire({
            title: "삭제 완료!",
            text: "해당 DB가 성공적으로 삭제되었습니다.",
            icon: "success",
            confirmButtonColor: "#4FC153",
            showCancelButton: false,
            confirmButtonText: "OK",
          });
        }
      })
      .catch((error) => {
        console.error("삭제 중 오류 발생: ", error);
        Swal.fire("삭제 오류!", "삭제 처리 중 문제가 발생했습니다.", "error");
      });
  };

  const addEmptyDatabase = () => {
    const newKey = uuidv4();
    tempProject.databaseMap[newKey] = emptyDatabase;
    setSelectedKey(newKey);
    setTempProject({ ...tempProject });
  };

  const changeDatabaseHandler = (value) => {
    selectedDatabase.databaseType = value;
    tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
    setTempProject({ ...tempProject });
  };

  const changeUsernameHandler = (e) => {
    selectedDatabase.username = e.target.value;
    tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
    setTempProject({ ...tempProject });
  };

  const changePasswordHandler = (e) => {
    selectedDatabase.password = e.target.value;
    tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
    setTempProject({ ...tempProject });
  };

  const changeSchemaPathHandler = (e) => {
    selectedDatabase.schemaPath = e.target.value;
    tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
    setTempProject({ ...tempProject });
  };

  const changeInternalPortHandler = (e) => {
    selectedDatabase.internalPort = e.target.value;
    tempProject.databaseMap[selectedKey] = { ...selectedDatabase };
    setTempProject({ ...tempProject });
  };

  useEffect(() => {
    setCreatedProject({ ...tempProject });
  }, [tempProject]);

  useEffect(() => {
    setSelectedDatabase({ ...tempProject.databaseMap[selectedKey] });
  }, [selectedKey]);

  const databaseList = ["Mysql", "Mongodb", "Redis"];

  return (
    <div className={styles.page}>
      <div className={styles.tapBox}>
        {Object.keys(tempProject.databaseMap).map((key, index) => {
          return (
            <div
              key={index}
              className={key === selectedKey ? styles.selectedTap : styles.tap}
              onClick={() => clickKeyHandler(key)}
            >
              <div>{index + 1}</div>
              <div
                className={styles.xMark}
                onClick={(event) => {
                  event.stopPropagation();
                  clickDeleteHandler(key);
                }}
              >
                {" "}
                x{" "}
              </div>
            </div>
          );
        })}
        <p onClick={addEmptyDatabase} className={styles.tapPlus}>
          +
        </p>
      </div>

      <InputSelectBox
        keyName={"데이터베이스"}
        list={databaseList}
        value={selectedDatabase.databaseType}
        onChange={changeDatabaseHandler}
      />
      <DescBox desc={"Database 서비스의 프레임워크를 선택하세요"} />

      <InputBox
        keyName={"Username"}
        valueName={"root"}
        value={selectedDatabase.username}
        onChange={changeUsernameHandler}
      />
      <DescBox desc={"데이터베이스에 설정한 Username을 작성해주세요"} />

      <InputBox
        keyName={"Password"}
        valueName={"ssafy"}
        value={selectedDatabase.password}
        onChange={changePasswordHandler}
      />
      <DescBox desc={"데이터베이스에 설정한 Password를 작성해주세요"} />

      <InputBox
        keyName={"초기 데이터 파일 경로"}
        valueName={"/DB"}
        value={selectedDatabase.schemaPath}
        onChange={changeSchemaPathHandler}
      />
      <DescBox
        desc={
          "init.sql 등 초기데이터 스키마 파일이 있다면 해당 파일 폴더 경로를 작성하세요"
        }
      />

      <InputBox
        keyName={"내부 포트 번호"}
        valueName={"3306"}
        value={selectedDatabase.internalPort}
        onChange={changeInternalPortHandler}
      />
      <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />
    </div>
  );
}
