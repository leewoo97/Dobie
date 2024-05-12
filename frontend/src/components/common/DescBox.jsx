import styles from "./DescBox.module.css";

export default function DescBox({ desc }) {
  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <div className={styles.key}></div>
        <div className={styles.desc}>{desc}</div>
      </div>
    </div>
  );
}
