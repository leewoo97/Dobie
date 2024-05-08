import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import ProjectFrame from "../../components/update/ProjectFrame";

export default function UpdateProjectPage() {
  return (
    <>
      <NavTop />
      <NavLeftCreate num={2} />
      <ProjectFrame />
    </>
  );
}
