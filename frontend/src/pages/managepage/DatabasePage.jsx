import NavTop from "../../components/common/NavTop";
import NavLeft from "../../components/common/NavLeft";
import DatabaseFrame from "../../components/manage/DatabaseFrame";

export default function DatabasePage() {
  return (
    <>
      <NavTop />
      <NavLeft num={5} />
      <DatabaseFrame />
    </>
  );
}
