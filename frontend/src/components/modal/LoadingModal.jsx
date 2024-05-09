import { useState, useEffect, useRef } from "react";
import styles from "./LoadingModal.module.css";
import Animaion from "../common/Animaion";

export default function Madal({ setModalOpen }) {
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
                    <Animaion/>
                    <div className={styles.text}>Building...</div>
                    <div className={styles.modalhead}>
                    </div>
                </div>
            </div>
        </>
    );
}
