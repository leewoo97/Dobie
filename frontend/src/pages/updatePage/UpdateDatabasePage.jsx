import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import DatabaseFrame from "../../components/update/DatabaseFrame";
export default function UpdateDatabasePage() {
  return (
    <>
      <NavTop />
      <NavLeftUpdate num={5} />
      <DatabaseFrame />
    </>
  );
}
