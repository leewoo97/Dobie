import styles from "./GetCopyBox.module.css";
import CopyIcon from "../../assets/copy.png";

export default function GetSecretBox({ keyName, valueName }) {
  const copyToClipboard = () => {
    navigator.clipboard.writeText(valueName);
    alert("Copy Success");
  };

  return (
    <div className={styles.page}>
      {/* <p>박스</p> */}
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <p className={styles.value}>{valueName}</p>
        <div className={styles.iconBox}>
          <img
            className={styles.icon}
            src={CopyIcon}
            alt="Copy"
            onClick={copyToClipboard}
          />
        </div>
      </div>
    </div>
  );
}
