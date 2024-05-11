import NavTop from "../../components/common/NavTop";
import NavLeft from "../../components/common/NavLeft";
import ProjectFrame from "../../components/manage/ProjectFrame";

export default function ProjectPage() {
  return (
    <>
      <NavTop />
      <NavLeft num={2} />
      <ProjectFrame />
    </>
  );
}
