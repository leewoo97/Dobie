import React from "react";
import styles from "./WebhookFrame.module.css";
import useProjectStore from "../../stores/projectStore";
import GetBox from "../common/GetBox";
import GetCopyBox from "../common/GetCopyBox";
import GetLinkBox from "../common/GetLinkBox";
import GetSecretBox from "../common/GetSecretBox";
import webhooks from "../../assets/webhook/webhooks.png";
import webhookbutton from "../../assets/webhook/webhookbutton.png";
import webhookregist from "../../assets/webhook/webhookregist.png";

export default function WebhookFrame() {
  const { selectedProject } = useProjectStore();

  return (
    <div className={styles.page}>
      <div className={styles.top}>
        <div>
          <div className={styles.text}>프로젝트</div>
          <div className={styles.projectName}>
            {selectedProject.projectName}
          </div>
        </div>
      </div>
      <GetBox
        keyName={"Git"}
        valueName={selectedProject.git.gitType === 1 ? "GitLab" : "GitHub"}
      />
      <GetLinkBox
        keyName={"Git URL"}
        valueName={selectedProject.git.gitUrl}
        linkValue={selectedProject.git.gitUrl}
      />
      <GetSecretBox
        keyName={"AccessToken"}
        valueName={selectedProject.git.accessToken}
      />
      <GetCopyBox
        keyName={"등록 URL"}
        valueName={`http://${selectedProject.projectDomain}:8010/api/project/webhook/${selectedProject.projectId}`}
      />

      <div className={styles.manual}>
        <div className={styles.manualName}>webhook 메뉴얼</div>
        <div className={styles.part}>
          <div className={styles.left}>
            <img src={webhooks} className={styles.webhooksImg} />
          </div>
          <div className={styles.right}>
            <div className={styles.ment}>
              사용자의 Git Repository에 접속합니다.
            </div>
            <div className={styles.ment}>Settings - Webhooks를 선택합니다.</div>
          </div>
        </div>
        <div className={styles.part}>
          <div className={styles.left}>
            <img
              src={webhookbutton}
              alt="webhook"
              className={styles.webhookbutton}
            />
          </div>
          <div className={styles.right}>
            <div className={styles.ment}>
              Add new Webhook 버튼을 클릭합니다.
            </div>
          </div>
        </div>
        <div className={styles.part}>
          <div className={styles.left}>
            <img
              src={webhookregist}
              alt="webhook"
              className={styles.webhookregist}
            />
          </div>
          <div className={styles.right}>
            <div className={styles.ment}>등록 URL의 URL을 입력합니다.</div>
            <div className={styles.ment}>AccessToken을 입력합니다.</div>
            <div className={styles.ment}>
              Trigger의 Push Event를 체크합니다.
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
