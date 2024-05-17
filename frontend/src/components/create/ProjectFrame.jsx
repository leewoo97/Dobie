import { useState, useEffect } from "react";
import styles from "./ProjectFrame.module.css";
import InputBox from "../common/InputBox";
import DescBox from "../common/DescBox";
import ToggleBox from "../common/ToggleBox";
import RadioBox from "../common/RadioBox";
import githubImage from "../../assets/github.png";
import gitlabImage from "../../assets/gitlab.png";
import useProjectStore from "../../stores/projectStore";

export default function ProjectFrame() {

  const [gittype, setGittype] = useState(null);
  const { createdProject, setCreatedProject } = useProjectStore();
  const [tempProject, setTempProject] = useState({ ...createdProject });

  
  const changeNameHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      projectName: e.target.value,
    }));
  };
  const changeDomainHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      projectDomain: e.target.value,
    }));
  };

  const changeGitTypeHandler = (type) => {
    setGittype(type);
    setTempProject((prev) => ({
      ...prev,
      git: {
        ...prev.git,
        gitType: type,
      },
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
    setTempProject(prev => ({
      ...prev,
      git: {
        ...prev.git,
        branch: e.target.value
      }
    }));
  };
  
  const changeHttpsHandler = () => {
    setTempProject(prev => ({
      ...prev,
      usingHttps: !prev.usingHttps,
    }));
  };

  useEffect(() => {
    setCreatedProject(tempProject);
    // eslint-disable-next-line react-hooks/exhaustive-deps
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
        keyName={"Domain Name"}
        valueName={"dobie.xyz"}
        onChange={changeDomainHandler}
        value={tempProject.projectDomain}
      />
      <DescBox desc={"도메인명을 작성하세요"} />
      <RadioBox
        keyName={"Git Type"}
        valueName={"github"}
        onChange={changeGitTypeHandler}
        value={tempProject.git.gitType}
        isRadio
      />
      <DescBox desc={"Git 저장소의 타입을 선택하세요"} />
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
      <ToggleBox keyName={"https"} valueName={"true / false"} value={tempProject.usingHttps} onChange={changeHttpsHandler} isToggle/>
      <DescBox
        desc={
          "Https 사용 여부를 선택하세요 ('true'이면 사용, false'이면 미사용)"
        }
      />
    </div>
  );
}
