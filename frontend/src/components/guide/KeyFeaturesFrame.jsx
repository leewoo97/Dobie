import styles from "./KeyFeatureFrame.module.css";
import navbarImg from "../../assets/guide/navbarImg.png";
import ProjectInfoImg from "../../assets/guide/ProjectInfoImg.png";
import LogImg1 from "../../assets/guide/LogImg1.png";
import LogImg2 from "../../assets/guide/LogImg2.png";
import Dockerfile1 from "../../assets/guide/Dockerfile1.png";
import Dockerfile2 from "../../assets/guide/Dockerfile2.png";
import DockerCompose1 from "../../assets/guide/DockerCompose1.png";
import DockerCompose2 from "../../assets/guide/DockerCompose2.png";
import Webhook1 from "../../assets/guide/Webhook1.png";
import Webhook2 from "../../assets/guide/Webhook2.png";
import github from "../../assets/github.png";
import gitlab from "../../assets/gitlab.png";
import WebhookGitLab1 from "../../assets/guide/WebhookGitLab1.png";
import WebhookGitLab2 from "../../assets/guide/WebhookGitLab2.png";
import WebhookGitLab3 from "../../assets/guide/WebhookGitLab3.png";
import WebhookGitHub1 from "../../assets/guide/WebhookGitHub1.png";
import WebhookGitHub2 from "../../assets/guide/WebhookGitHub2.png";
import WebhookGitHub3 from "../../assets/guide/WebhookGitHub3.png";
import { useState } from "react";
export default function KeyFeaturesFrame() {
  const [gitStatus, setGitStatus] = useState("GitLab");

  const gitHandle = (gitInfo) => {
    setGitStatus(gitInfo);
  };
  return (
    <div id="feature" className={styles.guideFrame}>
      <div className={styles.header}>
        <div className={styles.title}>Key Features</div>
      </div>
      <div className={styles.normal}>
        <div id="info" className={styles.section}>
          <div className={styles.strength}>1. Project Info</div>
          <div>
            프로젝트 관리 페이지에서 왼쪽 네비게이션 바를 활용하여 등록된
            프로젝트 정보를 볼 수 있습니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={navbarImg} className={styles.navbarImg} />
            <figcaption className={styles.caption}>Navigation Img</figcaption>
          </figure>
          <figure className={styles.imgBox}>
            <img
              alt="name"
              src={ProjectInfoImg}
              className={styles.ProjectInfoImg}
            />
            <figcaption className={styles.caption}>Project Info Img</figcaption>
          </figure>
          <div>
            프로젝트 정보 페이지에서 등록된 프로젝트를 수정, 삭제 할 수
            있습니다.
          </div>
        </div>
        <div id="log" className={styles.section}>
          <div className={styles.strength}>2. Log</div>
          <div>Dobie를 이용해 각 컨테이너의 log를 볼 수 있습니다.</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={LogImg1} className={styles.LogImg1} />
            <figcaption className={styles.caption}>Log Img1</figcaption>
          </figure>
          <div>
            실행중인 프로젝트의 활성화된 컨테이너를 보면 <b>로그보기</b>를
            확인할 수 있습니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={LogImg2} className={styles.LogImg2} />
            <figcaption className={styles.caption}>Log Img2</figcaption>
          </figure>
          <div>
            해당 버튼을 클릭하면 선택한 컨테이너의 로그를 볼 수 있습니다.
          </div>
        </div>
        <div id="docker" className={styles.section}>
          <div className={styles.strength}>3. Docker file</div>
          <div>
            각 컨테이너를 빌드할때 사용한 Docker file을 조회할 수 있습니다.
          </div>
          <div>
            Dobie는 초보자를 위한 서비스로 사용자 프로젝트에 맞게 설계된 Docker
            file을 볼 수 있습니다.
          </div>
          <div>
            실행중인 프로젝트의 활성화된 컨테이너를 보면 Dockerfile 조회를
            확인할 수 있습니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={Dockerfile1} className={styles.LogImg1} />
            <figcaption className={styles.caption}>Docker file 1</figcaption>
          </figure>
        </div>
        <div>
          해당 버튼을 클릭하면 선택한 컨테이너의 Docker file을 볼 수 있습니다.
        </div>
        <figure className={styles.imgBox}>
          <img alt="name" src={Dockerfile2} className={styles.LogImg2} />
          <figcaption className={styles.caption}>Docker file 2</figcaption>
        </figure>
        <div id="compose" className={styles.section}>
          <div className={styles.strength}>4. Docker compose file</div>
          <div>Docker-compose.yml 파일을 조회할 수 있습니다.</div>
          <div>
            Dobie는 각 서비스의 Dockerfile을 생성하고 Docker compose를 사용해
            한번에 실행시키는 방식으로 사용자의 프로젝트를 배포합니다.
          </div>
          <div>
            이러한 과정에서 사용되는 docker-compose.yml 파일을 조회할 수
            있습니다.
          </div>
          <figure className={styles.imgBox}>
            <img
              alt="name"
              src={DockerCompose1}
              className={styles.ProjectInfoImg}
            />
            <figcaption className={styles.caption}>
              Docker-compose.yml file 1
            </figcaption>
          </figure>
          <div>
            프로젝트 관리 페이지에서 docker-compose.yml 파일 조회 버튼을
            클릭합니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={DockerCompose2} className={styles.LogImg1} />
            <figcaption className={styles.caption}>
              Docker-compose.yml file 2
            </figcaption>
          </figure>
          <div>
            다음과 같이 docker-compose.yml 파일의 내용을 확인할 수 있습니다.
          </div>
        </div>
        <div id="webhook" className={styles.section}>
          <div className={styles.strength}>5. Webhooks(CD)</div>
          <div>
            Dobie는 간단한 Webhooks 설정을 통해 사용자의 프로젝트를 지속적으로
            배포(Continuous Deployment)합니다.
          </div>
          <div>
            설정 후 사용자가 Git Repository에 변경사항을 Commit - Push 하면
            Dobie는 해당 내용을 실행중인 프로젝트에 반영합니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={Webhook1} className={styles.ProjectInfoImg} />
            <figcaption className={styles.caption}>
              Docker-compose.yml file 2
            </figcaption>
          </figure>
          <div>프로젝트 관리 페이지의 Webhook 설정 버튼을 클릭합니다.</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={Webhook2} className={styles.ProjectInfoImg} />
            <figcaption className={styles.caption}>
              Docker-compose.yml file 2
            </figcaption>
          </figure>
          <div>
            Webhook 설정 페이지는 사용자가 Webhook 설정 시 필요한 정보들을
            제공하고 있습니다.
          </div>
          <div>
            Webhook 설정은 사용자의 Git Repository에서 이루어집니다. 사용자의
            Git Repository를 선택하세요
          </div>
          <div className={styles.gitSelectBox}>
            <figure className={styles.gitImgBox}>
              <img
                alt="name"
                src={gitlab}
                className={
                  gitStatus === "GitLab" ? styles.gitSelectIcon : styles.gitIcon
                }
                onClick={() => gitHandle("GitLab")}
              />
              <figcaption className={styles.gitCaption}>GitLab</figcaption>
            </figure>
            <figure className={styles.gitImgBox}>
              <img
                alt="name"
                src={github}
                className={
                  gitStatus === "GitHub" ? styles.gitSelectIcon : styles.gitIcon
                }
                onClick={() => gitHandle("GitHub")}
              />
              <figcaption className={styles.gitCaption}>GitHub</figcaption>
            </figure>
          </div>
          {gitStatus === "GitLab" && (
            <div>
              <div>GitLab Repository에 접속합니다.</div>
              <figure className={styles.imgBox}>
                <img
                  alt="name"
                  src={WebhookGitLab1}
                  className={styles.smallImg}
                />
                <figcaption className={styles.caption}>
                  Webhook Setting 1
                </figcaption>
              </figure>
              <div>Settings - Webhooks를 클릭합니다.</div>
              <figure className={styles.imgBox}>
                <img
                  alt="name"
                  src={WebhookGitLab2}
                  className={styles.LogImg1}
                />
                <figcaption className={styles.caption}>
                  Webhook Setting 2
                </figcaption>
              </figure>
              <div>URL에 Webhook 설정 페이지의 등록 URL을 입력합니다.</div>
              <div>
                Secret Token에 Webhook 설정 페이지의 Access Token을 입력합니다.
                복사버튼을 이용하면 쉽게 복사할 수 있습니다.
              </div>
              <div>Trigger의 Push events를 체크합니다.</div>
              <div>Add webhook 버튼을 누르면 설정이 완료됩니다.</div>
              <figure className={styles.imgBox}>
                <img
                  alt="name"
                  src={WebhookGitLab3}
                  className={styles.ProjectInfoImg}
                />
                <figcaption className={styles.caption}>
                  Webhook Setting 3
                </figcaption>
              </figure>
            </div>
          )}
          {gitStatus === "GitHub" && (
            <div>
              <div>GitHub Repository에 접속합니다.</div>
              <figure className={styles.imgBox}>
                <img
                  alt="name"
                  src={WebhookGitHub1}
                  className={styles.ProjectInfoImg}
                />
                <figcaption className={styles.caption}>
                  Webhook Setting 1
                </figcaption>
              </figure>
              <div>
                Settings - Webhooks에 들어가서 Add webhook버튼을 클릭합니다.
              </div>
              <figure className={styles.imgBox}>
                <img
                  alt="name"
                  src={WebhookGitHub2}
                  className={styles.LogImg1}
                />
                <figcaption className={styles.caption}>
                  Webhook Setting 2
                </figcaption>
              </figure>
              <div>
                Payload URL에 Webhook 설정 페이지의 등록 URL을 입력합니다.
              </div>
              <div>Content type을 application/json으로 설정합니다.</div>
              <div>Add webhook 버튼을 눌러 설정을 마무리합니다.</div>
              <figure className={styles.imgBox}>
                <img
                  alt="name"
                  src={WebhookGitHub3}
                  className={styles.LogImg1}
                />
                <figcaption className={styles.caption}>
                  Webhook Setting 3
                </figcaption>
              </figure>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
