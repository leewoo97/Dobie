import React, { useState } from "react";
import styles from "./WebhookFrame.module.css";
import useProjectStore from "../../stores/projectStore";
import GetBox from "../common/GetBox";
import GetSecretBox from "../common/GetSecretBox";

export default function WebhookFrame() {
  const { selectedProject } = useProjectStore();

  return (
    <div className={styles.page}>
      <div>{selectedProject.projectName}</div>
      <GetBox
        keyName={"git"}
        valueName={selectedProject.git.gitType == 1 ? "gitLab" : "gitHub"}
      />
      <GetBox keyName={"git url"} valueName={selectedProject.git.gitUrl} />
      <GetSecretBox
        keyName={"AccessToken"}
        valueName={selectedProject.git.accessToken}
      />
    </div>
  );
}
