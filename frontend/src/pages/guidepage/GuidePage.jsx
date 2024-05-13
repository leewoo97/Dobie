import styles from "./GuidePage.module.css";
import NavTop from "../../components/common/NavTop";
import OverviewFrame from "../../components/guide/OverviewFrame";
import NavLeftGuide from "../../components/common/NavLeftGuide";
import { useRef } from "react";
import SupportFrame from "../../components/guide/SupportFrame";

export default function GuidePage() {
  const overviewRef = useRef(null);
  const supportRef = useRef(null);
  const registRef = useRef(null);

  const scrollToSection = (sectionRef) => {
    if (sectionRef && sectionRef.current) {
      window.scrollTo({
        top: sectionRef.current.offsetTop, // 오타 수정: offssetTop -> offsetTop
        behavior: "smooth",
      });
    }
  };

  return (
    <>
      <NavTop />
      <div className={styles.page}>
        <OverviewFrame ref={overviewRef} />
        <SupportFrame ref={supportRef} />
      </div>
      <NavLeftGuide
        scrollToSection={scrollToSection}
        overviewRef={overviewRef}
        supportRef={supportRef}
        registRef={registRef}
      />
    </>
  );
}
