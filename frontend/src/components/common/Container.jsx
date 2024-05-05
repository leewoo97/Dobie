import s from "classnames";
import styles from "./Container.module.css";

function Container({ children }) {
  return <div className={s(styles.container)}>{children}</div>;
}

export default Container;
