import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import DatabaseFrame from "../../components/create/DatabaseFrame";
export default function CreateDatabasePage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={5} />
      <DatabaseFrame />
    </>
  );
}
