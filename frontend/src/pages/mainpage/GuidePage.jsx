import axios from "axios";
import { useState, useEffect } from "react";
import styles from "./GuidePage.module.css";

import NavTop from "../../components/common/NavTop";

export default function GuidePage() {
  return (
    <>
      <NavTop />
      <div className={styles.page}>
        <h1>가이드 페이지</h1>
      </div>
    </>
  );
}
