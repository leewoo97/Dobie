import NavTop from "../../components/common/NavTop";
import NavLeftCreate from "../../components/common/NavLeftCreate";
import BackendFrame from "../../components/create/BackendFrame";
import useProjectStore from "../../stores/projectStore";

export default function CreateBackendPage() {
  const {newProject} = useProjectStore();

  console.log(newProject.backendMap);

  return (
    <>
      <NavTop />
      <NavLeftCreate num={3} />
      <div>
        {Object.values(newProject.backendMap).map((backend, index) => {
          return <div key={index}>test</div>

        })}
      </div>
      <BackendFrame />
    </>
  );
}
