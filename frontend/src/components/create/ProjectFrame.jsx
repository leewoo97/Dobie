import { useState, useEffect } from "react";
import styles from "./ProjectFrame.module.css";
import InputBox from "../common/InputBox";
import DescBox from "../common/DescBox";
import ProjectTopCreate from "../common/ProjectTopCreate";
import githubImage from "../../assets/github.png";
import gitlabImage from "../../assets/gitlab.png";
import useProjectStore from "../../stores/projectStore";


export default function ProjectFrame() {

    // const [url, setUrl] = useState(null);
    // const [accessToken, setAccessToken] = useState(null);
    // const [webHook, setWebHook] = useState(null);
    // const [branch, setBranch] = useState(null);

    const [gittype, setGittype] = useState("gitlab");
    const {newProject, setNewProject} = useProjectStore();
    const [tempProject, setTempProject] = useState({...newProject});

    const changeUrlHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            git: {
                ...prev.git,
                gitUrl : e.target.value
            }
        }));
    }

    const changeTokenHandler = (e) => {
        setTempProject(prev => ({
            ...prev,
            git: {
                ...prev.git,
                accessToken : e.target.value
            }
        }));
    }

    useEffect(() => {
        setNewProject(tempProject);
    }, [tempProject])


    return (
        <div className={styles.page}>
            <ProjectTopCreate />
            {/* <GetBox keyName={"url"} valueName={url} onChange={(e) => setUrl(e.target.value)}/>
            <GetBox keyName={"액세스 토큰"} valueName={accessToken} onChange={(e)=> setAccessToken(e.target.value)}/>
            <GetBox keyName={"자동 배포 웹훅 설정"} valueName={webHook} onChange={(e)=> setWebHook(e.target.value)}/>
            <GetBox keyName={"브랜치"} valueName={branch} onChange={(e)=>setBranch(e.target.value)}/> */}
            <InputBox keyName={"url"} valueName={"url"} onChange={changeUrlHandler} value={tempProject.git.gitUrl}/>
            <DescBox desc={"GitLab 또는 GitHub 의 프로젝트를 클론하기 위한 URL을 등록하세요 "} />
            <InputBox keyName={"액세스 토큰"} valueName={"accessToken"} onChange={changeTokenHandler} value={tempProject.git.accessToken}/>
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

            <div>

            </div>

            <InputBox keyName={"브랜치"} valueName={"branch"} />
        </div>
    );
}