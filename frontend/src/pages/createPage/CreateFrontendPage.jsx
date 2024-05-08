import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import FrontendFrame from "../../components/create/FrontendFrame";

export default function CreateFrontendPage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={4} />
      <FrontendFrame />
    </>
  );
}
