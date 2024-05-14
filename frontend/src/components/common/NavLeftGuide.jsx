import { useNavigate } from "react-router-dom";
import styles from "./NavLeftGuide.module.css";
import home2 from "../../assets/homeIcon2.png";
import { useState } from "react";

export default function NavLeftGuide({ scrollToId }) {
  const navigate = useNavigate();
  const [showDetail, setShowDetail] = useState("");

  const toggleDetail = (current) => {
    setShowDetail(current);
  };

  const handleClick = (sectionId) => {
    toggleDetail(sectionId);
    scrollToId(sectionId);
    if (sectionId === showDetail) {
      setShowDetail("");
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.list}>
        <p className={styles.text} onClick={() => handleClick("dobie")}>
          Dobie
        </p>
        {showDetail === "dobie" && (
          <>
            <p className={styles.text2} onClick={() => scrollToId("overview")}>
              Overview
            </p>
            <p className={styles.text2} onClick={() => scrollToId("structure")}>
              Structure
            </p>
          </>
        )}
        <p className={styles.text} onClick={() => handleClick("support")}>
          Support
        </p>
        <p className={styles.text} onClick={() => handleClick("regist")}>
          Regist Project
        </p>
        {showDetail === "regist" && (
          <>
            <p className={styles.text2} onClick={() => scrollToId("registPJ")}>
              Project
            </p>
            <p className={styles.text2} onClick={() => scrollToId("registBE")}>
              Backend
            </p>
            <p className={styles.text2} onClick={() => scrollToId("registFE")}>
              Frontend
            </p>
            <p className={styles.text2} onClick={() => scrollToId("registDB")}>
              DB
            </p>
            <p className={styles.text2} onClick={() => scrollToId("registAWS")}>
              AWS
            </p>
          </>
        )}
        <p className={styles.text} onClick={() => handleClick("run")}>
          Run & Stop
        </p>
        {showDetail === "run" && (
          <>
            <p className={styles.text2} onClick={() => scrollToId("all")}>
              All Services
            </p>
            <p className={styles.text2} onClick={() => scrollToId("each")}>
              Each Service
            </p>
          </>
        )}
        <p className={styles.text} onClick={() => handleClick("feature")}>
          Key Features
        </p>
        {showDetail === "feature" && (
          <>
            <p className={styles.text2} onClick={() => scrollToId("info")}>
              Project Info
            </p>
            <p className={styles.text2} onClick={() => scrollToId("log")}>
              Log
            </p>
            <p className={styles.text2} onClick={() => scrollToId("docker")}>
              Docker
            </p>
            <p className={styles.text2} onClick={() => scrollToId("compose")}>
              Docker Compose
            </p>
            <p className={styles.text2} onClick={() => scrollToId("webhook")}>
              Webhooks(CD)
            </p>
          </>
        )}
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
