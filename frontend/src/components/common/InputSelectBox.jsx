import { useEffect, useState } from "react";
import styles from "./InputSelectBox.module.css";

export default function InputSelectBox({ keyName, list, value, onChange }) {
  
  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <div className={styles.list}>
          {list.map((framework) => (
            <div
              className={
                framework === value ? styles.selectedValue : styles.value
              }
              key={framework}
              onClick={() => onChange(framework)}
            >
              {framework}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
