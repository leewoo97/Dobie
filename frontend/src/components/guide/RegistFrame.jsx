import styles from "./RegistFrame.module.css";
import gitLabName from "../../assets/guide/projectNameGitLab.png";
import gitHubName from "../../assets/guide/ProjectNameGitHub.png";
import gitHubToken from "../../assets/guide/GitHubToken.png";
import gitLabToken from "../../assets/guide/GitLabToken.png";
import folderBE from "../../assets/guide/FolderBE.png";
import folderFE from "../../assets/guide/FolderFE.png";
import AWS from "../../assets/guide/AWS.png";
import AWSSetting from "../../assets/guide/AWSSetting.png";
import Https from "../../assets/guide/Https.png";
export default function RegistFrame() {
  return (
    <div id="regist" className={styles.guideFrame}>
      <div className={styles.header}>
        <div className={styles.title}>Regist Project</div>
      </div>
      <div className={styles.normal}>
        <div id="registPJ" className={styles.section}>
          <div className={styles.strength}>1. Project</div>
          {/* 프로젝트 이름 */}
          <div className={styles.strengthLight}>1) 프로젝트명</div>
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
          <div className={styles.strengthLight}>2) Domain Name</div>
          <div>사용중인 Domain Name을 입력합니다.</div>
          <div>만약 사용중인 Domain이 없다면 IP 주소를 넣어도 무방합니다.</div>
          <div> ex) 12.34.567.89</div>
          <div className={styles.strengthLight}>3) Git Type</div>
          <div>Git Lab, Git Hub 중 사용중인 플랫폼을 선택합니다.</div>
          {/* Git clone URL */}
          <div className={styles.strengthLight}>4) Git Clone URL</div>
          <div>Repository의 Git clone URL을 입력해줍니다.</div>
          {/* Access Token */}
          <div className={styles.strengthLight}>5) Access Token</div>
          <div className={styles.smallSection}>
            • Git Lab - Project Access Token
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
            • Git Hub - Personal Access Token
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
          <div className={styles.strengthLight}>6) 브랜치</div>
          <div>서버에 반영할 브랜치명을 입력합니다.</div>
          <div>ex) main, master or 임의의 브랜치</div>
          <div className={styles.strengthLight}>7) https</div>
          <div>
            Https는 기존 Http에서 통신 과정을 암호화하여 보안적으로 강화한 것
            입니다.
          </div>
          <div>
            Https를 사용하기 위해서는 SSL 인증서를 발급, 적용하는 과정이
            필요합니다.
          </div>
          <div>
            Dobie는 간단한 버튼 클릭만으로 Https 적용을 가능하게 합니다.{" "}
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={Https} className={styles.httpsButton} />
            <figcaption className={styles.caption}>Https Button</figcaption>
          </figure>
          <div>Https를 적용하려면 해당 버튼을 위와 같이 활성화 해주세요</div>
        </div>
        {/* 백엔드 */}
        <div id="registBE" className={styles.section}>
          <div className={styles.strength}>2. Backend</div>
          <div>Backend는 여러개를 등록할 수 있습니다.</div>
          <div className={styles.strengthLight}>1) 프레임워크</div>
          <div>Backend 서비스의 프레임워크 정보를 입력하세요</div>
          <div>SpringBoot는 Gradle, Maven으로 나누어져 있습니다.</div>
          <div className={styles.strengthLight}>2) 언어버젼</div>
          <div>프레임 워크에서 사용하는 언어 버젼을 입력해주세요</div>
          <div className={styles.strengthLight}>3) 폴더 경로</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={folderBE} className={styles.gitLabNameImg} />
            <figcaption className={styles.caption}>
              Backend Folder Path
            </figcaption>
          </figure>
          <div> Backend 서비스의 폴더 경로를 입력해주세요</div>
          <div>
            폴더 경로는 프로젝트 루트 경로로 부터의 서비스 폴더 경로로 위와 같은
            상황에서는 <b>/backend</b>가 됩니다.
          </div>
          <div className={styles.strengthLight}>4) Nginx location</div>
          <div>Backend의 baseURI를 입력해주세요</div>
          <div className={styles.strengthLight}>5) 내부 포트 번호</div>
          <div>Backend가 사용할 포트번호를 입력해주세요</div>
        </div>
        {/* 프론트엔드 */}
        <div id="registFE" className={styles.section}>
          <div className={styles.strength}>3. Frontend</div>
          <div className={styles.strengthLight}>1) 프레임워크</div>
          <div>Frontend 서비스의 프레임워크 정보를 입력하세요</div>
          <div className={styles.strengthLight}>2) 언어버젼</div>
          <div>프레임 워크에서 사용하는 언어 버젼을 입력해주세요</div>
          <div className={styles.strengthLight}>3) 폴더 경로</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={folderFE} className={styles.gitLabNameImg} />
            <figcaption className={styles.caption}>
              Frontend Folder Path
            </figcaption>
          </figure>
          <div> Frontend 서비스의 폴더 경로를 입력해주세요</div>
          <div>
            폴더 경로는 프로젝트 루트 경로로 부터의 서비스 폴더 경로로 위와 같은
            상황에서는 <b>/frontend</b> 가 됩니다.
          </div>
          <div className={styles.strengthLight}>4) 내부 포트 번호</div>
          <div>Frontend가 사용할 포트번호를 입력해주세요</div>
        </div>
        {/* DB */}
        <div id="registDB" className={styles.section}>
          <div className={styles.strength}>4. DB</div>
          <div className={styles.strengthLight}>1) 데이터베이스</div>
          <div>프로젝트에서 사용하는 데이터베이스의 정보를 입력하세요</div>
          <div className={styles.strengthLight}>2) Username</div>
          <div>데이터베이스에서 설정한 Username을 입력하세요</div>
          <div className={styles.strengthLight}>3) Password</div>
          <div>데이터베이스에서 설정한 Username을 입력하세요</div>
          <div className={styles.strengthLight}>4) 데이터베이스명</div>
          <div>사용할 데이터베이스명을 입력하세요</div>
          <div className={styles.strengthLight}>5) 초기데이터파일 경로</div>
          <div>만약 프로젝트에 적용할 초기데이터파일이 있다면 입력하세요</div>
          <div className={styles.strengthLight}>6) 외부 포트 번호</div>
          <div>데이터베이스에서 사용할 외부 포트번호를 입력하세요</div>
          <div className={styles.strengthLight}>7) 내부 포트 번호</div>
          <div>데이터베이스에서 사용할 내부 포트번호를 입력하세요</div>
        </div>
        {/* AWS */}
        <div id="registAWS" className={styles.section}>
          <div className={styles.strength}>5. AWS</div>
          <div className={styles.strengthLight}>1) 포트 설정</div>
          <div>
            특정 주소 또는 범위에 대해 포트를 개방하는 규칙을 생성해야 합니다.
          </div>
          <div>
            가이드에서는 <b>AWS Lightsail</b>을 기준으로 작성하였습니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={AWS} className={styles.aws} />
            <figcaption className={styles.caption}>AWS</figcaption>
          </figure>
          <div>인스턴스 정보 페이지로 이동합니다.</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={AWSSetting} className={styles.aws} />
            <figcaption className={styles.caption}>AWS Setting</figcaption>
          </figure>
          <div>프로젝트 등록 시 사용했던 포트를 설정해줍니다.</div>
          <div>IPv4 방화벽을 활성화 하면 IPv6도 활성화됩니다.</div>
        </div>
      </div>
    </div>
  );
}
