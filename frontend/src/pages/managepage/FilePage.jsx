import NavLeft from "../../components/common/NavLeft";
import NavTop from "../../components/common/NavTop";
import FileFrame from "../../components/manage/FileFrame";

export default function FilePage() {
  return (
    <>
      <NavTop />
      <NavLeft num={1} />
      <FileFrame />
    </>
  );
}
