import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import FrontendFrame from "../../components/update/FrontendFrame";

export default function UpdateFrontendPage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={4} />
      <FrontendFrame />
    </>
  );
}
