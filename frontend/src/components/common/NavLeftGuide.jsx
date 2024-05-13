import { useNavigate } from "react-router-dom";
import styles from "./NavLeftGuide.module.css";
import s from "classnames";
import home2 from "../../assets/homeIcon2.png";
import useProjectStore from "../../stores/projectStore";
import { useParams } from "react-router-dom";
import { useState } from "react";

export default function NavLeftGuide({
  scrollToSection,
  overviewRef,
  supportRef,
  registRef,
}) {
  const navigate = useNavigate();
  const { selectedProject } = useProjectStore();

  const params = useParams();
  const serviceId = params.serviceId;
  const databaseId = params.databaseId;

  const [spreadBackend, setSpreadBackend] = useState(serviceId ? true : false);
  const [spreadDatabase, setSpreadDatabase] = useState(
    databaseId ? true : false
  );

  const changeStateBE = () => {
    if (!spreadBackend) {
      setSpreadBackend(true);
      navigate(
        `/manage/backend/${
          Object.values(selectedProject.backendMap).at(0).serviceId
        }`
      );
    }
  };

  const changeStateDB = () => {
    if (!spreadDatabase) {
      setSpreadBackend(true);
      navigate(
        `/manage/database/${
          Object.values(selectedProject.databaseMap).at(0).databaseId
        }`
      );
    }
  };

  return (
    <div className={s(styles.container)}>
      <div className={styles.list}>
        <p
          className={styles.text2}
          onClick={() => scrollToSection(overviewRef)}
        >
          Dobie Overview
        </p>
        <p className={styles.text2} onClick={() => scrollToSection(supportRef)}>
          Support
        </p>
        <div>
          <p className={styles.text2}>Regist Project</p>
          {Object.values(selectedProject.backendMap).map((backendProject) => (
            <div
              className={[
                spreadBackend ? "" : styles.hide,
                backendProject.serviceId === serviceId ? styles.text3 : "",
              ].join(" ")}
              key={backendProject.serviceId}
              onClick={() =>
                navigate(`/manage/backend/${backendProject.serviceId}`)
              }
            ></div>
          ))}
        </div>
        <p className={styles.text2}>Run Project</p>
        <div>
          <p className={styles.text2}>Key Features</p>
          {/* 여기는 DatabaseMap */}
          {Object.values(selectedProject.databaseMap).map((databaseProject) => (
            <div
              className={[
                spreadDatabase ? "" : styles.hide,
                databaseProject.databaseId === databaseId ? styles.text3 : "",
              ].join(" ")}
              key={databaseProject.databaseId}
              onClick={() =>
                navigate(`/manage/database/${databaseProject.databaseId}`)
              }
            ></div>
          ))}
        </div>
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
