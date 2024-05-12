import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import BackendFrame from "../../components/create/BackendFrame";

export default function CreateBackendPage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={3} />
      <BackendFrame />
    </>
  );
}
