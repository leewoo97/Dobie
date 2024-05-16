import styles from "./ToggleBox.module.css";

export default function ToggleBox({ keyName, valueName, onChange, value, isToggle }) {
  const inputValue = value === 0 ? "" : value;
  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        {isToggle ? (
          <label className={styles.switch}>
            <input type="checkbox" checked={value} onChange={onChange} />
            <span className={styles.slider}></span>
          </label>
        ) : (
          <input
            className={styles.value}
            type="text"
            placeholder={valueName}
            onChange={onChange}
            value={inputValue}
          />
        )}
      </div>
    </div>
  );
}
