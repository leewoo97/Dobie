import { useNavigate } from "react-router-dom";
import styles from "./NavLeftGuide.module.css";
import s from "classnames";
import home2 from "../../assets/homeIcon2.png";

export default function NavLeftGuide({ scrollToId }) {
  const navigate = useNavigate();

  return (
    <div className={s(styles.container)}>
      <div className={styles.list}>
        <p className={styles.text2} onClick={() => scrollToId("overview")}>
          Dobie Overview
        </p>
        <p className={styles.text2} onClick={() => scrollToId("overview1")}>
          Dobie Overview1
        </p>
        <p className={styles.text2} onClick={() => scrollToId("support")}>
          Support
        </p>
      </div>
      <div className={styles.buttons}>
        <div className={styles.home} onClick={() => navigate("/main")}>
          메인페이지{" "}
          <img
            src={home2}
            alt=""
            decoding="async"
            className={styles.homeIcon}
          />
        </div>
      </div>
    </div>
  );
}
