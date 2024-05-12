import styles from "./GetCopyBox.module.css";
import CopyIcon from "../../assets/copy.png";

export default function GetSecretBox({ keyName, valueName }) {
  const copyToClipboard = () => {
    navigator.clipboard.writeText(valueName);
    alert("Copy Success");
  };

  const displayValue = () => {
    if (!valueName) {
      return "입력 정보가 없습니다.";
    } else {
      return valueName;
    }
  };

  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <p className={styles.value}>{displayValue()}</p>
        <div className={styles.iconBox}>
          {valueName && (
            <img
              className={styles.icon}
              src={CopyIcon}
              alt="Copy"
              onClick={copyToClipboard}
            />
          )}
        </div>
      </div>
    </div>
  );
}
