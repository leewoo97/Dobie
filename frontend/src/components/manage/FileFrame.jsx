import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { addFile, getFile, deleteFile } from "../../api/Project";
import styles from "./FileFrame.module.css";
import useProjectStore from "../../stores/projectStore";
import useFileStore from "../../stores/fileStore";
import DescBox from "../common/DescBox";
import toast from "react-hot-toast";

export default function FileFrame() {
  const navigate = useNavigate();
  const { selectedProject } = useProjectStore();
  const { fileList, setFileList } = useFileStore();
  const [uploadFile, setUploadFile] = useState([]);

  useEffect(() => {
    try {
      getFileList();
    } catch (error) {}
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getFileList = async (e) => {
    try {
      const response = await getFile(selectedProject.projectId);
      setFileList(response.data.data);
      const newUploadFile = Array.from(
        { length: response.data.data.length },
        () => null
      );
      setUploadFile(newUploadFile);
    } catch (error) {
      console.log("파일 리스트 조회 실패 : ", error);
    }
  };

  // 파일첨부

  // 파일이 추가될때마다 files, paths 새 배열 추가
  const handleFileChange = (event) => {
    const newFiles = Array.from(event.target.files);
    setUploadFile((prevUploadFile) => [...prevUploadFile, ...newFiles]);
    const newFileList = [
      ...fileList,
      ...newFiles.map((file, index) => ({
        fileId: "",
        filePath: "",
        fileName: file.name,
      })),
    ];
    setFileList(newFileList);
  };

  // 경로가 수정될때마다 paths의 경로에 반영하기
  const handlePathChange = (index, event) => {
    const newPaths = [...fileList];
    newPaths[index] = { ...newPaths[index], filePath: event.target.value };
    setFileList(newPaths);
  };

  // 파일 전송 API
  const handleSendFile = async () => {
    const dto = {
      projectId: selectedProject.projectId,
      projectName: selectedProject.projectName,
      fileList: fileList,
    };

    if (fileList.length === 0) {
      navigate(`..`);
      toast.success(`[파일 저장 성공] 프로젝트를 꼭 재실행하세요 !`, {
        position: "top-center",
      });
    } else {
      try {
        await addFile(dto, uploadFile);
        navigate(`..`);
        toast.success(`[파일 저장 성공] 프로젝트를 꼭 재실행하세요 !`, {
          position: "top-center",
        });
      } catch (error) {
        console.log("파일 저장 실패: " + error);
      }
    }
  };

  // 파일 삭제 API
  const handleRemoveFile = async (index) => {
    const dto = {
      projectId: selectedProject.projectId,
      projectName: selectedProject.projectName,
      fileId: fileList[index].fileId,
      filePath: fileList[index].filePath,
      fileName: fileList[index].fileName,
    };

    setUploadFile((prevFiles) => prevFiles.filter((_, idx) => idx !== index));
    const newFileList = fileList.filter((_, idx) => idx !== index);
    setFileList(newFileList);

    try {
      await deleteFile(dto);
    } catch (error) {
      console.log("파일 삭제 실패: " + error);
    }
  };

  const fileInputRef = useRef();
  const handleClick = () => {
    fileInputRef.current.click();
  };

  return (
    <div className={styles.page}>
      <div className={styles.top}>
        <div>
          <div className={styles.text}>프로젝트</div>
          <div className={styles.projectName}>
            {selectedProject.projectName}
          </div>
        </div>
        <div className={styles.saveFileButton} onClick={handleSendFile}>
          저장
        </div>
      </div>

      <div className={styles.fileFrame}>
        <div className={styles.fileTop}>
          <div className={styles.key}>환경 설정 파일 추가</div>
          <div className={styles.desc}>
            <input
              type="file"
              multiple
              ref={fileInputRef}
              onChange={handleFileChange}
              style={{ display: "none" }} // 숨김 처리
            />
            <div className={styles.addfileButton} onClick={handleClick}>
              파일 선택
            </div>
          </div>
        </div>

        <div className={styles.fileBottom}>
          <div className={styles.key}></div>
          <div className={styles.desc}>
            <div>
              {fileList.map((file, index) => (
                <div className={styles.file} key={index}>
                  {uploadFile[index] == null ? (
                    <div className={styles.filePath}>{file.filePath}</div>
                  ) : (
                    <input
                      className={styles.filePath}
                      type="text"
                      placeholder="/backend/src/main/resources"
                      onChange={(event) => handlePathChange(index, event)}
                    />
                  )}
                  {/* <input className={styles.value} type="text" placeholder="/backend/src/main/resources" value={paths[index]}/> */}
                  <div className={styles.fileName}>{file.fileName}</div>
                  <div
                    className={styles.fileRemove}
                    onClick={() => handleRemoveFile(index)}
                  >
                    X
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
      <DescBox
        desc={
          ".gitignore에 등록한 .env, .yml 등 환경설정파일을 첨부하고 \n\n 프로젝트 루트 경로로부터 해당 파일의 경로를 작성해주세요 \n\n 파일을 등록 또는 삭제 한 후 프로젝트를 전체 중지하고 다시 실행해주세요"
        }
      />

      <div className={styles.whiteSpace}></div>
    </div>
  );
}
