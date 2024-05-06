import React, { useEffect, useState } from "react";
import Container from "../../components/common/Container";
import styles from "./LoginPage.module.css";
import mascot from "../../assets/mascot.png";

export default function LoginPage() {

    return (
        <Container>
                <div className={styles.content}>
                    <div className={styles.title}>
                        <img src={mascot} alt="" width="120px" decoding="async" />
                        <div className={styles.dobie}>Dobie</div>
                    </div>
                    <input type="text" placeholder="username"></input>
                    <input type="password" placeholder="password"></input>
                    <div className={styles.button}>
                        <div className={styles.login}>로그인</div>
                    </div>
                </div>
        </Container>
    );
}