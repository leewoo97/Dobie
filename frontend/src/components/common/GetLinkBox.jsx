import styles from "./GetLinkBox.module.css";
import LinkIcon from "../../assets/link.png";

export default function GetBox({ keyName, valueName, linkValue }) {
  return (
    <div className={styles.page}>
      {/* <p>박스</p> */}
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <p className={styles.value}>{valueName}</p>
        <div className={styles.iconBox}>
          <a href={linkValue} target="_blank" rel="noopener noreferrer">
            <img className={styles.icon} src={LinkIcon} />
          </a>
        </div>
      </div>
    </div>
  );
}
