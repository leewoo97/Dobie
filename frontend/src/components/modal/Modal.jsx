import { useRef } from "react";

import styles from "./Modal.module.css";
import close from "../../assets/close.png";

import useModalStore from "../../stores/modalStore";

export default function Madal({ content }) {
  const modalBackground = useRef();
  const { fileType, setFileType } = useModalStore();
  const { modalOpen, setModalOpen } = useModalStore();

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
            {fileType == "nginx" && <h2>Nginx Config File</h2>}
            {fileType == "dockerCompose" && <h2>docker-compose File</h2>}
            {fileType == "dockerFile" && <h2>docker File</h2>}

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
            <p className={styles.content}>{content}</p>
          </div>
        </div>
      </div>
    </>
  );
}
