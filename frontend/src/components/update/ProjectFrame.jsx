import { useState, useEffect, useRef } from "react";
import styles from "./ProjectFrame.module.css";
import InputBox from "../common/InputBox";
import DescBox from "../common/DescBox";
import githubImage from "../../assets/github.png";
import gitlabImage from "../../assets/gitlab.png";
import useProjectStore from "../../stores/projectStore";

export default function ProjectFrame() {

    const { updatedProject, setUpdatedProject } = useProjectStore();
    const [gittype, setGittype] = useState("gitlab");
    const [tempProject, setTempProject] = useState({ ...updatedProject });

    const changeUrlHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            git: {
                ...prev.git,
                gitUrl: e.target.value
            }
        }));
    };

    const changeTokenHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            git: {
                ...prev.git,
                accessToken: e.target.value
            }
        }));
    };

    const changeBranchHandler = (e) => {
        // setUrl(e.target.value);
        setUpdatedProject(prev => ({
            ...prev,
            git: {
                ...prev.git,
                accessToken: e.target.value
            }
        }));
    };

    // 파일첨부
    const [files, setFiles] = useState([]); //업로드 할 파일 목록들
    const [paths, setPaths] = useState([]); //업로드할 파일 각각의 경로들

    const fileInputRef = useRef();
    const handleClick = () => {
        fileInputRef.current.click();

    }
    const handleFileChange = (event) => {
        const newFiles = Array.from(event.target.files);
        setFiles((prevFiles) => [...prevFiles, ...newFiles]);
        setPaths((prevPaths) => [...prevPaths, ...newFiles.map(()=>"")]);
    }
    const handlePathChange = (index, event) => {
        const newPaths = [...paths];
        newPaths[index] = event.target.value;
        setPaths(newPaths);
    }
    const handleRemoveFile = (index) => {
        setFiles((prevFiles) => prevFiles.filter((_, idx) => idx !== index));
        setPaths((prevPaths) => prevPaths.filter((_, idx) => idx !== index));
    }

    console.log(tempProject);
    useEffect(() => {
        setUpdatedProject(tempProject);
    }, [tempProject]);

    return (
        <div className={styles.page}>
            {/* <ProjectTopUpdate updatedProject={updatedProject} /> */}
            <div className={styles.top}>
                <div className={styles.text}>프로젝트</div>
                <div className={styles.projectName}>{updatedProject.projectName}</div>
            </div>

            <InputBox keyName={"Git CLONE URL"} value={tempProject.git.gitUrl} onChange={changeUrlHandler} />
            <DescBox desc={"GitLab 또는 GitHub 의 프로젝트를 클론하기 위한 URL을 수정하세요 "} />
            <InputBox keyName={"Access Token"} value={tempProject.git.accessToken} onChange={changeTokenHandler} />
            <DescBox desc={"Git 저장소에 접근하기 위한 엑세스 토큰을 발급하여 등록하세요 "} />

            <div className={styles.boxFrame}>
                <div className={styles.key}></div>
                <div className={styles.desc}>
                    <div className={styles.access}>
                        <div className={styles.accessinfo}>엑세스 토큰 발급 방법</div>
                        <div className={styles.selection}>
                            <img
                                src={gitlabImage}
                                alt="GitLab Logo" width="15%"
                                className={`${styles.logo} ${gittype === "gitlab" ? styles.selected : styles.unselected}`}
                                onClick={() => setGittype("gitlab")}
                            />
                            <img
                                src={githubImage}
                                alt="GitHub Logo" width="15%"
                                className={`${styles.logo} ${gittype === "github" ? styles.selected : styles.unselected}`}
                                onClick={() => setGittype("github")}
                            />
                        </div>
                    </div>

                    {gittype === "gitlab" && (
                        <div className={styles.gitlab}>
                            <p>1. GitHub의 프로필에서 Settings &gt; Developer Settings</p>
                            <p>2. Personal access tokens &gt; Tokens (Classic) 에서 Generate New Token 클릭</p>
                            <p>3. Note에 해당 토큰 설명 작성 (선택)</p>
                            <p>4. Expiration 은 해당 토큰 유효기간 설정</p>
                            <p>5. repo 체크 한 뒤 Generate Token 클릭</p>
                            <p>6. 발급 받은 토큰 복사해서 등록하기</p>
                        </div>
                    )}
                    {gittype === "github" && (
                        <div className={styles.github}>
                            <p>1. GitHub의 프로필에서 Settings &gt; Developer Settings</p>
                            <p>2. Personal access tokens &gt; Tokens (Classic) 에서 Generate New Token 클릭</p>
                            <p>3. Note에 해당 토큰 설명 작성 (선택)</p>
                        </div>
                    )}
                </div>
            </div>

            <InputBox keyName={"Branch"} valueName={"main"} value={tempProject.branck} onChange={changeBranchHandler}/>
            <DescBox desc={"서버에 반영할 브랜치명을 수정하세요 ('main' or 'master' or 임의의 브랜치) "} />

            <div className={styles.fileFrame}>
                <div className={styles.fileTop}>
                    <div className={styles.key}>환경 설정 파일 추가</div>
                    <div className={styles.desc}>
                        <input type="file" multiple ref={fileInputRef} onChange={handleFileChange} style={{ display: "none" }} />
                        <div className={styles.addfileButton} onClick={handleClick}>
                            파일 선택
                        </div>
                    </div>
                </div>

                <div className={styles.fileBottom}>
                    <div className={styles.key}></div>
                    <div className={styles.desc}>
                        <div>
                            {files.map((file, index) => (
                                <div className={styles.file} key={index}>
                                    <input
                                        className={styles.filePath}
                                        type="text"
                                        placeholder="/backend/src/main/resources"
                                        value={paths[index]}
                                        onChange={(event) => handlePathChange(index, event)}
                                    />
                                    {/* <input className={styles.value} type="text" placeholder="/backend/src/main/resources" value={paths[index]}/> */}
                                    <div className={styles.fileName}>{file.name}</div>

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
                    ".gitignore에 등록한 .env, .yml 등 환경설정파일을 첨부하고 프로젝트 루트 경로로부터 해당 파일의 경로를 재업로드해주세요"
                }
            />
        </div>
    );
}