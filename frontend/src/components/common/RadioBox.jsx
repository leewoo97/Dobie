import styles from "./RadioBox.module.css";
import githubImage from "../../assets/github.png";
import gitlabImage from "../../assets/gitlab.png";
import liveImage from "../../assets/live.png";
import disableImage from "../../assets/disable.png";

export default function RadioBox({ keyName, valueName, onChange, value, isRadio }) {
  return (
    <div className={styles.page}>
      <div className={styles.boxFrame}>
        <p className={styles.key}>{keyName}</p>
        {isRadio ? (
          <div className={styles.radioGroup}>
            <label className={styles.radioLabel}>
              <input
                type="radio"
                name="gitType"
                value="1"
                checked={value === 1}
                onChange={() => onChange(1)}
              />
              <img
                src={gitlabImage}
                alt="GitLab"
                className={styles.icon}
              />
              <img
                src={value === 1 ? liveImage : disableImage}
                alt={value === 1 ? "Selected" : "Not Selected"}
                className={styles.radioIcon}
              />
            </label>
            <label className={styles.radioLabel}>
              <input
                type="radio"
                name="gitType"
                value="2"
                checked={value === 2}
                onChange={() => onChange(2)}
              />
              <img
                src={githubImage}
                alt="GitHub"
                className={styles.icon}
              />
              <img
                src={value === 2 ? liveImage : disableImage}
                alt={value === 2 ? "Selected" : "Not Selected"}
                className={styles.radioIcon}
              />
            </label>
          </div>
        ) : (
          <input
            className={styles.value}
            type="text"
            placeholder={valueName}
            onChange={onChange}
            value={value || ""}
          />
        )}
      </div>
    </div>
  );
}
