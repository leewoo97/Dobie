import React, { useState } from "react";
import styles from "./WebhookFrame.module.css";
import useProjectStore from "../../stores/projectStore";

export default function WebhookFrame() {
  const { selectedProject } = useProjectStore();
  const [showToken, setShowToken] = useState(false);

  const toggleTokenVisibility = () => {
    setShowToken(!showToken);
  };

  const copyToClipboard = () => {
    navigator.clipboard.writeText(selectedProject.git.accessToken);
    alert("Access Token copied to clipboard!");
  };

  return (
    <div className={styles.page}>
      <div>{selectedProject.projectName}</div>
      <div>git : {selectedProject.git.gitType == 1 ? "gitLab" : "gitHub"} </div>
      <div>git url : {selectedProject.git.gitUrl}</div>
      <div>
        Access Token:{" "}
        {showToken ? selectedProject.git.accessToken : "••••••••••"}
        <button onClick={toggleTokenVisibility}>
          {showToken ? "Hide" : "Show"}
        </button>
        <button onClick={copyToClipboard}>Copy</button>
      </div>
    </div>
  );
}
