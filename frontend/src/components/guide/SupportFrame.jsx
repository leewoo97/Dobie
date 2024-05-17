import FrameworkImg from "../common/FrameworkImg";
import styles from "./SupportFrame.module.css";
export default function SupportFrame() {
  return (
    <div id="support" className={styles.guideFrame}>
      <div className={styles.header}>
        <div className={styles.title}>Dobie Support</div>
      </div>
      <div className={styles.normal}>
        현재 Dobie는 다음과 같은 <b>Framework, DB</b>를 지원합니다.
        <div className={styles.frameworks}>
          <div className={styles.frameworkBlock}>
            <FrameworkImg framework={"SpringBoot(gradle)"} />
            <div className={styles.annotaion}>SpringBoot</div>
          </div>
          <div className={styles.frameworkBlock}>
            <FrameworkImg framework={"Django"} />
            <div className={styles.annotaion}>Django</div>
          </div>
          <div className={styles.frameworkBlock}>
            <FrameworkImg framework={"Fastapi"} />
            <div className={styles.annotaion}>Fastapi</div>
          </div>
          <div className={styles.frameworkBlock}>
            <FrameworkImg framework={"React"} />
            <div className={styles.annotaion}>React</div>
          </div>
          <div className={styles.frameworkBlock}>
            <FrameworkImg framework={"Vue"} />
            <div className={styles.annotaion}>Vue</div>
          </div>
          <div className={styles.frameworkBlock}>
            <FrameworkImg databaseType={"Mysql"} />
            <div className={styles.annotaion}>MySQL</div>
          </div>
          <div className={styles.frameworkBlock}>
            <FrameworkImg databaseType={"Redis"} />
            <div className={styles.annotaion}>Redis</div>
          </div>
          <div className={styles.frameworkBlock}>
            <FrameworkImg databaseType={"Mongodb"} />
            <div className={styles.annotaion}>MongoDB</div>
          </div>
        </div>
        <div>
          사용자는 Dobie가 지원하는 Framework, DB의 범주에서 다양한 프로젝트를
          배포할 수 있습니다.
        </div>
      </div>
    </div>
  );
}
