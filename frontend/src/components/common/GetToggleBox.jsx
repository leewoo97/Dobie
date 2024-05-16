import styles from "./GetToggleBox.module.css";

export default function GetToggleBox({ keyName, valueName, isToggle }) {
  const displayValue = () => {
    if (valueName === null || valueName === undefined || valueName === " ") {
      return "입력정보가 없습니다.";
    } else {
      return valueName;
    }
  };

  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        {isToggle ? (
          <label className={styles.switch}>
            <input type="checkbox" checked={valueName} readOnly />
            <span className={styles.slider}></span>
          </label>
        ) : (
          <p className={styles.value}>{displayValue()}</p>
        )}
      </div>
    </div>
  );
}
