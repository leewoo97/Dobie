import { useState } from "react";
import styles from "./InputSelectBox.module.css";

export default function InputSelectBox({ keyName, list, value, onChange }) {
  const [selectedValue, setSelectedValue] = useState(value);

  const handleSelect = (value) => {
    if (selectedValue !== value) {
      setSelectedValue(value);
      onChange(value); // 부모 컴포넌트로 선택된 값을 전달
    }
  };

  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <div className={styles.list}>
          {list.map((value) => (
            <div
              className={
                value === selectedValue ? styles.selectedValue : styles.value
              }
              key={value}
              onClick={() => handleSelect(value)}
            >
              {value}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
