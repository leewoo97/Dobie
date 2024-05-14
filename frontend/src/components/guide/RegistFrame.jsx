import styles from "./RegistFrame.module.css";
import gitLabName from "../../assets/guide/projectNameGitLab.png";
import gitHubName from "../../assets/guide/ProjectNameGitHub.png";
import gitHubToken from "../../assets/guide/GitHubToken.png";
import gitLabToken from "../../assets/guide/GitLabToken.png";
export default function RegistFrame() {
  return (
    <div id="regist" className={styles.guideFrame}>
      <div className={styles.header}>
        <div className={styles.title}>Regist Project</div>
      </div>
      <div className={styles.normal}>
        <div id="registPJ" className={styles.section}>
          <div className={styles.strength}>Project</div>
          {/* 프로젝트 이름 */}
          <div className={styles.strengthLight}>1. 프로젝트명</div>
          <div>
            프로젝트명은 Git Repository의 프로젝트 최상단 폴더명을 입력합니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={gitLabName} className={styles.gitLabNameImg} />
            <figcaption className={styles.caption}>
              Git Lab Project Name
            </figcaption>
          </figure>
          <div>Git Lab의 test 프로젝트의 프로젝트명은 위와 같습니다.</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={gitHubName} className={styles.gitLabNameImg} />
            <figcaption className={styles.caption}>
              Git Hub Project Name
            </figcaption>
          </figure>
          <div>Git Hub의 NOAH 프로젝트의 프로젝트명은 위와 같습니다.</div>
          {/* Git clone URL */}
          <div className={styles.strengthLight}>2. Git Clone URL</div>
          <div>Repository의 Git clone URL을 입력해줍니다.</div>
          {/* Access Token */}
          <div className={styles.strengthLight}>3. Access Token</div>
          <div className={styles.smallSection}>
            1) Git Lab - Project Access Token
          </div>
          <figure className={styles.imgBox}>
            <img
              alt="name"
              src={gitLabToken}
              className={styles.gitLabNameImg}
            />
            <figcaption className={styles.caption}>
              Git Lab Project Access Token
            </figcaption>
          </figure>
          <div>Project Repository에서 Settings - Access Tokens</div>
          <div>
            add new token를 클릭 후 tokenName, date, role, scopes를
            설정해줍니다.
          </div>
          <div> 생성된 토큰을 입력합니다.</div>
          <div className={styles.smallSection}>
            2) Git Hub - Personal Access Token
          </div>
          <figure className={styles.imgBox}>
            <img
              alt="name"
              src={gitHubToken}
              className={styles.gitLabNameImg}
            />
            <figcaption className={styles.caption}>
              Git Hub Personal Access Token
            </figcaption>
          </figure>
          <div>Settings - Developer Settings - Personal access tokens에서</div>
          <div>
            Generate new tokens를 클릭 후 <b>Personal Access Token</b>을 생성 후
            입력합니다.
          </div>
          <div className={styles.strengthLight}>4. 브랜치</div>
          <div>서버에 반영할 브랜치명을 입력합니다.</div>
          <div>ex) main, master or 임의의 브랜치</div>
        </div>
        <div id="registBE" className={styles.strength}>
          Backend
        </div>
        <div id="registFE" className={styles.strength}>
          Frontend
        </div>
        <div id="registDB" className={styles.strength}>
          DB
        </div>
      </div>
    </div>
  );
}
