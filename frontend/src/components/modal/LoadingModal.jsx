import { useState, useEffect, useRef } from "react";
import styles from "./LoadingModal.module.css";
import Animaion from "../common/Animaion";
import StopAnimaion from "../common/StopAnimaion";
import SaveAnimaion from "../common/SaveAnimaion";
import useModalStore from "../../stores/modalStore";

export default function Madal() {
  const modalBackground = useRef();

  const { loadingModal, setLoadingModal } = useModalStore();
  const { action, setAction } = useModalStore();

  return (
    <>
      <div
        className={styles.modalContainer}
        ref={modalBackground}
        // onClick={(e) => {
        //     if (e.target === modalBackground.current) {
        //         setLoadingModal(false);
        //     }
        // }}
      >
        {action === "run" && (
          <div className={styles.modalContent}>
            <Animaion />
            <div className={styles.runtext}>Running...</div>
            <div className={styles.modalhead}></div>
          </div>
        )}
        {action === "stop" && (
          <div className={styles.modalContent}>
            <StopAnimaion />
            <div className={styles.stoptext}>Stopping...</div>
            <div className={styles.modalhead}></div>
          </div>
        )}
        {action === "save" && (
          <div className={styles.modalContent}>
            <SaveAnimaion />
            <div className={styles.savetext}>Saving...</div>
            <div className={styles.modalhead}></div>
          </div>
        )}
        {action === "build" && (
          <div className={styles.modalContent}>
            <SaveAnimaion />
            <div className={styles.savetext}>Building...</div>
            <div className={styles.modalhead}></div>
          </div>
        )}
      </div>
    </>
  );
}
