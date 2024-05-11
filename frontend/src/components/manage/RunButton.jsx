import run from "../../assets/run.png";
import restart from "../../assets/restart.png";
import styles from "./RunProjectItem.module.css";

export default function RunButton({
  type,
  container,
  isRunning,
  handleStartService,
}) {
  const icon = isRunning === "Running :)" ? restart : run;
  const serviceId =
    type === "Database" ? container.databaseId : container.serviceId;
  return (
    <img
      src={icon}
      alt=""
      className={styles.runButtonIcon}
      onClick={() => handleStartService(serviceId)}
    />
  );
}
