import NavTop from "../../components/common/NavTop";
import NavLeftUpdate from "../../components/common/NavLeftUpdate";
import BackendFrame from "../../components/update/BackendFrame";
import useProjectStore from "../../stores/projectStore";
import { useState, useEffect } from "react";

export default function UpdateBackendPage() {

  return (
    <>
      <NavTop />
      <NavLeftUpdate num={3} />
      <BackendFrame />
    </>
  );
}
