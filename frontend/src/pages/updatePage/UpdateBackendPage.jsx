import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import BackendFrame from "../../components/update/BackendFrame";
import useProjectStore from "../../stores/projectStore";
import { useState, useEffect } from "react";

export default function UpdateBackendPage() {

  const {updatedProject} = useProjectStore();
  const [selectedServiceId, setSelectedServiceId] = useState(null);

  useEffect(() => {
    const firstServiceId = Object.keys(updatedProject.backendMap)[0];
    setSelectedServiceId(firstServiceId);
  }, [updatedProject.backendMap]);

  const handleSelectedServiceId = (serviceId) => {
    setSelectedServiceId(serviceId);
  }
  console.log(selectedServiceId);

  return (
    <>
      {/* <NavTop /> */}
      <NavLeftUpdate num={3} />
      <div>
        {Object.entries(updatedProject.backendMap).map((backend, index) => (
          <button key={backend.at(0)} onClick={() => handleSelectedServiceId(backend.at(0))}>
            {index+1}
          </button>
        ))}
        {selectedServiceId && (
          <BackendFrame serviceId={selectedServiceId}/>
        )}
      </div>
    </>
  );
}
