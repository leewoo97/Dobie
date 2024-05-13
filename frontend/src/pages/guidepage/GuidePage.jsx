import styles from "./GuidePage.module.css";
import NavTop from "../../components/common/NavTop";
import OverviewFrame from "../../components/guide/OverviewFrame";
import NavLeftGuide from "../../components/common/NavLeftGuide";
import SupportFrame from "../../components/guide/SupportFrame";

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
        <OverviewFrame />
        <SupportFrame />
      </div>
      <NavLeftGuide scrollToId={scrollToId} />
    </>
  );
}
