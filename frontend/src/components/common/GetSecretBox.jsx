import { useState } from "react";
import styles from "./GetSecretBox.module.css";

export default function GetSecretBox({ keyName, valueName }) {
  const [showToken, setShowToken] = useState(false);
  const toggleTokenVisibility = () => {
    setShowToken(!showToken);
  };

  const copyToClipboard = () => {
    navigator.clipboard.writeText(valueName);
    alert("Copy Success");
  };

  return (
    <div className={styles.page}>
      {/* <p>박스</p> */}
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <p className={styles.value}>{showToken ? valueName : "••••••••••"}</p>
        <button onClick={toggleTokenVisibility}>
          {showToken ? "Hide" : "Show"}
        </button>
        <button onClick={copyToClipboard}>Copy</button>
      </div>
    </div>
  );
}
