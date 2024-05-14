import styles from "./KeyFeatureFrame.module.css";
import navbarImg from "../../assets/guide/navbarImg.png";
export default function KeyFeaturesFrame() {
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
            <img alt="name" src={navbarImg} className={styles.navbarImg} />
            <figcaption className={styles.caption}>Navigation Img</figcaption>
          </figure>
        </div>
        <div id="log" className={styles.section}>
          <div className={styles.strength}>2. Log</div>
        </div>
        <div id="docker" className={styles.section}>
          <div className={styles.strength}>3. Docker file</div>
        </div>
        <div id="compose" className={styles.section}>
          <div className={styles.strength}>4. Docker compose file</div>
        </div>
        <div id="webhook" className={styles.section}>
          <div className={styles.strength}>5. Webhooks</div>
        </div>
      </div>
    </div>
  );
}
