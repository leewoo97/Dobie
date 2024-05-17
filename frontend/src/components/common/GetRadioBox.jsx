import styles from "./GetRadioBox.module.css";
import githubImage from "../../assets/github.png";
import gitlabImage from "../../assets/gitlab.png";
import liveImage from "../../assets/live.png";
import disableImage from "../../assets/disable.png";

export default function GetRadioBox({ keyName, valueName, isRadio }) {
  const displayValue = () => {
    if (!valueName || valueName === " ") {
      return "입력정보가 없습니다.";
    } else {
      return valueName;
    }
  };

  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        {isRadio ? (
          <div className={styles.radioGroup}>
            <div className={styles.radioItem}>
              <img src={gitlabImage} alt="GitLab" className={styles.icon} />
              <img
                src={valueName === 1 ? liveImage : disableImage}
                alt={valueName === 1 ? "Selected" : "Not Selected"}
                className={styles.radioIcon}
              />
            </div>
            <div className={styles.radioItem}>
              <img src={githubImage} alt="GitHub" className={styles.icon} />
              <img
                src={valueName === 2 ? liveImage : disableImage}
                alt={valueName === 2 ? "Selected" : "Not Selected"}
                className={styles.radioIcon}
              />
            </div>
          </div>
        ) : (
          <p className={styles.value}>{displayValue()}</p>
        )}
      </div>
    </div>
  );
}
