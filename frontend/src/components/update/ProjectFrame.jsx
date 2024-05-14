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
        setTempProject(prev => ({
            ...prev,
            git: {
                ...prev.git,
                branch: e.target.value
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
            <div className={styles.top}>
                <div className={styles.text}>프로젝트</div>
                <div className={styles.projectName}>{updatedProject.projectName}</div>
            </div>

            <InputBox keyName={"Git Clone URL"} value={tempProject.git.gitUrl} onChange={changeUrlHandler} />
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

            <InputBox keyName={"Branch"} valueName={"main"} value={tempProject.git.branch} onChange={changeBranchHandler}/>
            <DescBox desc={"서버에 반영할 브랜치명을 수정하세요 ('main' or 'master' or 임의의 브랜치) "} />
        </div>
    );
}