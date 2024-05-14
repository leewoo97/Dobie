import { useRef } from "react";

import styles from "./LogModal.module.css";
import close from "../../assets/close2.png";

import useModalStore from "../../stores/modalStore";

export default function LogMadal({ content }) {
  const modalBackground = useRef();
  const { logModalOpen, setLogModalOpen, logContent } = useModalStore();

  return (
    <>
      <div
        className={styles.modalContainer}
        ref={modalBackground}
        onClick={(e) => {
          if (e.target === modalBackground.current) {
            setLogModalOpen(false);
          }
        }}
      >
        <div className={styles.modalContent}>
          <div className={styles.modalhead}>
            <div
              className={styles.closeImg}
              onClick={() => setLogModalOpen(false)}
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
            <p className={styles.content}>{logContent}</p>
          </div>
        </div>
      </div>
    </>
  );
}
