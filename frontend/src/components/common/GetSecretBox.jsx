import { useState } from "react";
import styles from "./GetSecretBox.module.css";
import ShowIcon from "../../assets/show.png";
import HideIcon from "../../assets/hide.png";
import CopyIcon from "../../assets/copy.png";

export default function GetSecretBox({ keyName, valueName }) {
  const [showToken, setShowToken] = useState(false);
  const toggleTokenVisibility = () => {
    setShowToken(!showToken);
  };

  const copyToClipboard = () => {
    navigator.clipboard.writeText(valueName);
    alert("Copy Success");
  };

  const displayValue = () => {
    if (!valueName) {
      return "입력 정보가 없습니다.";
    } else if (showToken) {
      return valueName;
    } else {
      return "••••••••••";
    }
  };

  return (
    <div className={styles.page}>
      {/* <p>박스</p> */}
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <p className={styles.value}>{displayValue()}</p>
        <div className={styles.iconBox}>
          {valueName && (
            <>
              <img
                className={styles.icon}
                src={showToken ? HideIcon : ShowIcon}
                alt={showToken ? "Hide" : "Show"}
                onClick={toggleTokenVisibility}
              />
              <img
                className={styles.icon}
                src={CopyIcon}
                alt="Copy"
                onClick={copyToClipboard}
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
}
