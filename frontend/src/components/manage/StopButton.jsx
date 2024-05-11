import stop from "../../assets/stop.png";
import styles from "./StopButton.module.css";

export default function StopButton({ type, container, handleStopService }) {
  const serviceId =
    type === "Database" ? container.databaseId : container.serviceId;
  return (
    <img
      src={stop}
      alt=""
      className={styles.stopButtonIcon}
      onClick={() => handleStopService(serviceId)}
    />
  );
}
