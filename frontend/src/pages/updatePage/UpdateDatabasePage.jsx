import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import DatabaseFrame from "../../components/update/DatabaseFrame";
import { useState, useEffect } from "react";
import useProjectStore from "../../stores/projectStore";

export default function UpdateDatabasePage() {

  const {updatedProject} = useProjectStore();
  const [selectedDatabaseId, setSelectedDatabseId] = useState( null );

  useEffect(() => {
    const firstDatabaseId = Object.keys(updatedProject.databaseMap)[0];
    setSelectedDatabseId(firstDatabaseId);
  }, [updatedProject.databaseMap]);

  const handleSelectDatabaseId = (databaseId) => {
    setSelectedDatabseId(databaseId);
  }
  console.log(selectedDatabaseId);

  return (
    <>
      {/* <NavTop /> */}
      <NavLeftUpdate num={5} />
      <div>
        {Object.entries(updatedProject.databaseMap).map((database, index)=> (
          <button key={database.at(0)} onClick={()=> handleSelectDatabaseId(database.at(0))}>
            {index+1}
          </button>
        ))}
        {selectedDatabaseId && (
          <DatabaseFrame databaseId={selectedDatabaseId}/>
        )}
      </div>
    </>
  );
}
