import React from "react";
import styles from "./DobieFrame.module.css";

import DobieIcon from "../../assets/mascot.png";
import structure from "../../assets/guide/structure.png";
export default function DobieFrame() {
  // 컴포넌트 로직
  return (
    <div id="dobie" className={styles.guideFrame}>
      <div className={styles.header}>
        <div className={styles.title}>
          <img src={DobieIcon} className={styles.dobieIcon} alt="icon"></img>
          Dobie Guide
        </div>
      </div>
      <div id="overview" className="section">
        <div className={styles.normal}>
          <div className={styles.strength}>1. Dobie</div>
          <div>
            Dobie는 초보자도 쉽게 사용할 수 있는 인프라 서비스 플랫폼입니다.
          </div>
          <div>
            사용자는 간단한 정보만을 입력하고 자신의 프로젝트를 배포하는 경험을
            할 수 있습니다.
          </div>
          <div>
            Dobie는 Docker, Docker Compose를 활용하여 사용자의 프로젝트를
            컨테이너로 관리합니다.
          </div>
          <div>컨테이너는 다음과 같은 장점을 가집니다.</div>
          <li>느슨하게 격리된 환경에서 애플리케이션을 패키징하고 실행</li>
          <li>동시에 많은 컨테이너를 실행 가능</li>
          <li>가볍고 애플리케이션 실행에 필요한 모든것을 포함</li>
        </div>
      </div>
      <div id="structure" className={styles.section}>
        <div className={styles.normal}>
          <div className={styles.strength}>2. Dobie 구조</div>
          <div>Dobie는 다음과 같은 구조를 가집니다.</div>
          <img
            src={structure}
            className={styles.structureImg}
            alt="structure"
          />
          <div>Dobie는 사용자의 인스턴스에서 작동합니다. </div>
          <div>
            Dobie를 사용하기 위해 사용자는 <b>자신의 인스턴스에 Dobie를 설치</b>
            해야 합니다.
          </div>
          <a
            href="https://github.com/eunnseok/dobie-deploy"
            className={styles.link}
            target="_blank"
            rel="noopener noreferrer"
          >
            설치하기 위한 Git 링크
          </a>
          <div>
            사용자가 Dobie에 <b>프로젝트를 등록</b> 한 후 Dobie는 프로젝트에
            맞는 이미지를 빌드합니다.
          </div>
        </div>
      </div>
    </div>
  );
}
