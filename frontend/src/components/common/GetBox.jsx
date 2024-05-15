import styles from "./GetBox.module.css";

export default function GetBox({ keyName, valueName }) {
  const displayValue = () => {
    if (!valueName || valueName === " ") {
      return "입력정보가 없습니다.";
    } else {
      return valueName;
    }
  };

  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <p className={styles.value}>{displayValue()}</p>
      </div>
    </div>
  );
}
