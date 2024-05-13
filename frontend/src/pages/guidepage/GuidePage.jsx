import styles from "./GuidePage.module.css";
import NavTop from "../../components/common/NavTop";
import DobieFrame from "../../components/guide/DobieFrame";
import NavLeftGuide from "../../components/common/NavLeftGuide";
import SupportFrame from "../../components/guide/SupportFrame";
import RegistFrame from "../../components/guide/RegistFrame";
import RunStopFrame from "../../components/guide/RunStopFrame";
import KeyFeaturesFrame from "../../components/guide/KeyFeaturesFrame";

export default function GuidePage() {
  const scrollToId = (id) => {
    const section = document.getElementById(id);
    if (section) {
      window.scrollTo({
        top: section.offsetTop,
        behavior: "smooth",
      });
    }
  };

  return (
    <>
      <NavTop />
      <div className={styles.page}>
        <DobieFrame />
        <SupportFrame />
        <RegistFrame />
        <RunStopFrame />
        <KeyFeaturesFrame />
      </div>
      <NavLeftGuide scrollToId={scrollToId} />
    </>
  );
}
