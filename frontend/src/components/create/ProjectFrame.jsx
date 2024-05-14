import { useState, useEffect, useRef } from "react";
import styles from "./ProjectFrame.module.css";
import InputBox from "../common/InputBox";
import DescBox from "../common/DescBox";
import githubImage from "../../assets/github.png";
import gitlabImage from "../../assets/gitlab.png";
import useProjectStore from "../../stores/projectStore";

export default function ProjectFrame() {
  // const [url, setUrl] = useState(null);
  // const [accessToken, setAccessToken] = useState(null);
  // const [branch, setBranch] = useState(null);

  const [gittype, setGittype] = useState("gitlab");
  const { createdProject, setCreatedProject } = useProjectStore();
  const [tempProject, setTempProject] = useState({ ...createdProject });

  
  const changeNameHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      projectName: e.target.value,
    }));
  };

  const changeUrlHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      git: {
        ...prev.git,
        gitUrl: e.target.value,
      },
    }));
  };

  const changeTokenHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      git: {
        ...prev.git,
        accessToken: e.target.value,
      },
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

  useEffect(() => {
    setCreatedProject(tempProject);
  }, [tempProject]);

  return (
    <div className={styles.page}>

      <InputBox
        keyName={"프로젝트명"}
        valueName={"S10P31B101"}
        onChange={changeNameHandler}
        value={tempProject.projectName}
      />
      <DescBox desc={"Git 저장소의 프로젝트 최상단 폴더명을 작성하세요"} />
      <InputBox
        keyName={"Git Clone URL"}
        valueName={"https://lab.ssafy.com/s10-final/YourProject.git"}
        onChange={changeUrlHandler}
        value={tempProject.git.gitUrl}
      />
      <DescBox
        desc={
          "GitLab 또는 GitHub 의 프로젝트를 Git Clone하기 위한 URL을 등록하세요"
        }
      />
      <InputBox
        keyName={"Access Token"}
        valueName={"accessToken"}
        onChange={changeTokenHandler}
        value={tempProject.git.accessToken}
      />
      <DescBox
        desc={
          "Git 저장소에 접근하기 위한 프로젝트 엑세스 토큰을 발급하여 등록하세요 "
        }
      />

      <div className={styles.boxFrame}>
        <div className={styles.key}></div>
        <div className={styles.desc}>
          <div className={styles.access}>
            <div className={styles.accessinfo}>엑세스 토큰 발급 방법</div>
            <div className={styles.selection}>
              <img
                src={gitlabImage}
                alt="GitLab Logo"
                width="15%"
                className={`${styles.logo} ${gittype === "gitlab" ? styles.selected : styles.unselected
                  }`}
                onClick={() => setGittype("gitlab")}
              />
              <img
                src={githubImage}
                alt="GitHub Logo"
                width="15%"
                className={`${styles.logo} ${gittype === "github" ? styles.selected : styles.unselected
                  }`}
                onClick={() => setGittype("github")}
              />
            </div>
          </div>

          {gittype === "gitlab" && (
            <div className={styles.gitlab}>
              <p>1. GitLab Project Repository에서 Settings &gt; AccessTokens</p>
              <p>2. Add new token 클릭</p>
              <p>3. Token name, Expirateion date, role, scopes 설정</p>
              <p>4. Create project access token 클릭</p>
              <p>5. Your new project access token의 토큰 정보 보관</p>
              <p>6. 발급 받은 토큰 복사해서 등록하기</p>
            </div>
          )}
          {gittype === "github" && (
            <div className={styles.github}>
              <p>1. GitHub의 프로필에서 Settings &gt; Developer Settings</p>
              <p>
                2. Personal access tokens &gt; Tokens (Classic) 에서 Generate
                New Token 클릭
              </p>
              <p>3. Note에 해당 토큰 설명 작성 (선택)</p>
            </div>
          )}
        </div>
      </div>
      <InputBox keyName={"Branch"} valueName={"main"} value={tempProject.git.branch} onChange={changeBranchHandler} />
      <DescBox
        desc={
          "서버에 반영할 브랜치명을 입력하세요 ('main' or 'master' or 임의의 브랜치) "
        }
      />
    </div>
  );
}
