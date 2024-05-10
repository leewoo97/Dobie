import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import BackendFrame from "../../components/update/BackendFrame";

export default function UpdateBackendPage() {
  return (
    <>
      <NavTop />
      <NavLeftUpdate num={3} />
      <BackendFrame />
    </>
  );
}
