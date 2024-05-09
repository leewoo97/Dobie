import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import NavTop from "../../components/common/NavTop";
import NavLeft from "../../components/common/NavLeft";

import toast from "react-hot-toast";
import styles from "./Modal.module.css";
import close from "../../assets/close.png";

// import { deleteProject } from "../../api/Project";
// import { getNginxConf } from "../../api/ngixn";
// import { getDockerCompose } from "../../api/Docker";
import useProjectStore from "../../stores/projectStore";

export default function Madal({ content, type, setModalOpen }) {
  const modalBackground = useRef();

  return (
    <>
      <div
        className={styles.modalContainer}
        ref={modalBackground}
        onClick={(e) => {
          if (e.target === modalBackground.current) {
            setModalOpen(false);
          }
        }}
      >
        <div className={styles.modalContent}>
          <div className={styles.modalhead}>
            {type == "nginx" && <h2>Nginx Config File</h2>}
            {type == "dockerCompose" && <h2>docker-compose File</h2>}

            <div
              className={styles.closeImg}
              onClick={() => setModalOpen(false)}
            >
              <img
                src={close}
                alt=""
                height="20px"
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
          </div>

          <div className={styles.modalBody}>
            <p className={styles.nginxConf}>{content}</p>
          </div>
        </div>
      </div>
    </>
  );
}
