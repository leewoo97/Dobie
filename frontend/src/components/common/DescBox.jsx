import styles from "./DescBox.module.css";

export default function DescBox({ desc }) {
  const formattedDesc = desc.split("\n").map((line, index) => (
    <span key={index}>
      {line}
      <br />
    </span>
  ));

  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <div className={styles.key}></div>
        <div className={styles.desc}>{formattedDesc}</div>
      </div>
    </div>
  );
}
