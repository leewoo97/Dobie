import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import BackendFrame from "../../components/update/BackendFrame";

export default function UpdateBackendPage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={3} />
      <BackendFrame />
    </>
  );
}
