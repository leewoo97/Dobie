import { useRef } from "react";

import styles from "./NewModal.module.css";
import close from "../../assets/close2.png";
import LottieAnimation from "../common/LoadingAnimation";

import useModalStore from "../../stores/modalStore";

export default function NewMadal() {
  const modalBackground = useRef();
  const { setNewModal } = useModalStore();

  return (
    <>
      <div
        className={styles.modalContainer}
        ref={modalBackground}
        onClick={(e) => {
          if (e.target === modalBackground.current) {
            setNewModal(false);
          }
        }}
      >
        <div className={styles.modalContent}>
          <LottieAnimation />
        </div>
      </div>
    </>
  );
}
