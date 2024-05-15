import styles from "./InputBox.module.css";

export default function InputBox({ keyName, valueName, onChange, value }) {
  const inputValue = value === 0 ? "" : value;
  return (
    <div className={styles.page}>

      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        <input
          className={styles.value}
          type="text"
          placeholder={valueName}
          onChange={onChange}
          value={inputValue}
        />
      </div>
    </div>
  );
}
