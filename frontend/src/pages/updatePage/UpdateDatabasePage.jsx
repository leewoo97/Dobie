import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import DatabaseFrame from "../../components/update/DatabaseFrame";
import { useState, useEffect } from "react";
import useProjectStore from "../../stores/projectStore";

export default function UpdateDatabasePage() {

  return (
    <>
      <NavTop />
      <NavLeftUpdate num={5} />
      <DatabaseFrame/>
    </>
  );
}
