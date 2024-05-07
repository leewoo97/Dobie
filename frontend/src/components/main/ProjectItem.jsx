import axios from "axios";
import { useState, useEffect } from "react";
import styles from "./ProjectItem.module.css";
import gitlab from "../../assets/gitlab.png";
import github from "../../assets/github.png";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";

export default function ProjectItem({ project }) {
  // const [projectList, setProjectList] = useState([{
  //     projectName: null,
  //     domainUrl: null,
  //     running: false,
  //     gitLink: null,
  // }]);

  return (
    <>
      <div className={styles.content}>
        <div key={project.projectName}>{project.projectName}</div>
        <div key={project.projectDomain}>{project.projectDomain}</div>
        <div className={styles.runButton}>
          <img src={project.running ? rerun : run} alt="" width="50px" />
          <img src={stop} alt="" width="50px"></img>
        </div>
        <div>
          <img
            src={project.git.gitType == 1 ? gitlab : github}
            alt=""
            width="50px"
          />
        </div>
      </div>
    </>
  );
}
