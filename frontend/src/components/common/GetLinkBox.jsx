import styles from "./GetLinkBox.module.css";
import LinkIcon from "../../assets/link.png";

export default function GetBox({ keyName, valueName, linkValue }) {
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
        {valueName && (
          <div className={styles.iconBox}>
            <a href={linkValue} target="_blank" rel="noopener noreferrer">
              <img className={styles.icon} alt="link" src={LinkIcon} />
            </a>
          </div>
        )}
      </div>
    </div>
  );
}
