import stop from "../../assets/stop.png";
import styles from "./RunProjectItem.module.css";

export default function StopButton({ type, container, handleStopService }) {
  const serviceId =
    type === "Database" ? container.databaseId : container.serviceId;
  return (
    <img
      src={stop}
      alt=""
      className={styles.runButtonIcon}
      onClick={() => handleStopService(serviceId)}
    />
  );
}
