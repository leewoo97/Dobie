import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import ProjectFrame from "../../components/create/ProjectFrame";

export default function CreateProjectPage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={2} />
      <ProjectFrame />
    </>
  );
}
