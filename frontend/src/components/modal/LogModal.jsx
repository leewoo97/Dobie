import { useRef } from "react";

import toast from "react-hot-toast";
import styles from "./LogModal.module.css";
import close from "../../assets/close2.png";
import reload from "../../assets/reload.png";

import useModalStore from "../../stores/modalStore";
import { getLog } from "../../api/Docker";

export default function LogMadal({ serviceId }) {
  const modalBackground = useRef();
  const { setLogModalOpen, logContent, setLogContent } = useModalStore();

  const handleLogModal = async (serviceId) => {
    try {
      const response = await getLog(serviceId);
      if (response.status === 200) {
        setLogContent(response.data);
        console.log("로그재조회");
        console.log(serviceId);
        // console.log(response.data);
      } else {
        toast.error(`로그 조회 실패`, {
          position: "top-center",
        });
      }
    } catch (error) {
      console.log("로그 조회 실패: " + error);
    }
  };

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
              onClick={() => handleLogModal(serviceId)}
            >
              <img
                src={reload}
                alt=""
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
            <div
              className={styles.closeImg}
              onClick={() => setLogModalOpen(false)}
            >
              <img
                src={close}
                alt=""
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
