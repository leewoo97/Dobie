import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import FrontendFrame from "../../components/update/FrontendFrame";

export default function UpdateFrontendPage() {
  return (
    <>
      <NavTop />
      <NavLeftUpdate num={4} />
      <FrontendFrame />
    </>
  );
}
