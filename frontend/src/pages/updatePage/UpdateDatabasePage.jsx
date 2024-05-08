import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import DatabaseFrame from "../../components/update/DatabaseFrame";
export default function UpdateDatabasePage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={5} />
      <DatabaseFrame />
    </>
  );
}
