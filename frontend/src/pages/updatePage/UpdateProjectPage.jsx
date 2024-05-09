import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import ProjectFrame from "../../components/update/ProjectFrame";

export default function UpdateProjectPage() {
  return (
    <>
      <NavTop />
      <NavLeftUpdate num={2} />
      <ProjectFrame />
    </>
  );
}
