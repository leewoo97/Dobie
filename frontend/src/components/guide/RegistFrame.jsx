import styles from "./RegistFrame.module.css";
export default function RegistFrame() {
  return (
    <div id="regist" className={styles.guideFrame}>
      <div className={styles.header}>
        <div className={styles.title}>Regist Project</div>
      </div>
      <div className={styles.normal}>
        <div id="registPJ" className={styles.strength}>
          Project
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
