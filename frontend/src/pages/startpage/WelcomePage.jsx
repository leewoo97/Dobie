import React, { useEffect, useState } from "react";
import Container from "../../components/common/Container";
import mascot from "../../assets/mascot.png";
import styles from "./WelcomePage.module.css";

export default function WelcomePage() {

    return (
        <Container>
            <div className={styles.content}>
                <div className={styles.mascot}>
                    <img src={mascot} alt="" width="160px" />
                </div>
                <div>
                    <div className={styles.dobie}>
                        Dobie
                    </div>
                    <div className={styles.eng}>
                        <div className={styles.line1}>Docker newbie</div>
                        <div className={styles.line2}>Docker is free</div>
                    </div>
                    <div className={styles.kor}>
                        <div className={styles.line3}>인프라로부터</div>
                        <div className={styles.line4}>자유로워지자</div>
                    </div>
                </div>
            </div>
        </Container>
    );
}