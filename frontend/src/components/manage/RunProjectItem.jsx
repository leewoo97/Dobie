import { useState, useEffect } from "react";

import styles from "./RunProjectList.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";
import springIcon from "../../assets/springIcon.png";
import reactIcon from "../../assets/reactIcon.png";
import vueIcon from "../../assets/vueIcon.png";
import djangoIcon from "../../assets/djangoIcon.png";
import mysqlIcon from "../../assets/mysqlIcon.png";
import redisIcon from "../../assets/redisIcon.png";
import mongodbIcon from "../../assets/mongodbIcon.png";
import setting from "../../assets/settingIcon.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";
import FrameworkImg from "../common/FrameworkImg";

export default function RunProjectItem({ container, type }) {
  return (
    <>
      <div className={styles.container}>
        <div className={styles.containerButton}>
          <div className={styles.runButton}>
            <img
              src={container.running == "Running :)" ? rerun : run}
              alt=""
              width="30px"
            />
            <img src={stop} width="30px"></img>
          </div>
          {(type == "Backend" || type == "Frontend") && (
            <div className={styles.fileButton}>
              Dockerfile 파일 조회
              <img
                src={document}
                alt=""
                width="25px"
                decoding="async"
                className={styles.btnIcon}
              />
            </div>
          )}
        </div>
        <div className={styles.box}>
          <div className={styles.boxTop}>
            <table>
              <tbody>
                <tr>
                  <td key={type} className={styles.serviceName}>
                    {type}
                  </td>
                  <td key={container.externalPort}>
                    외부포트 {container.externalPort}
                  </td>
                </tr>
                <tr>
                  {type == "Backend" || type == "Frontend" ? (
                    <td key={container.framework}>{container.framework}</td>
                  ) : (
                    <td key={container.databaseType}>
                      {container.databaseType}
                    </td>
                  )}

                  <td key={container.internalPort}>
                    내부포트 {container.internalPort}
                  </td>
                </tr>
              </tbody>
            </table>
            <FrameworkImg
              framework={container.framework}
              databaseType={container.databaseType}
            />
          </div>
          <div className={styles.line}></div>
          <div className={styles.boxBottom}>
            <div
              className={
                container.running == "Running :)"
                  ? styles.running
                  : styles.stopped
              }
              key={container.running}
            >
              {container.running}
              stoped :(
            </div>
            <div className={styles.log}>
              <img
                src={log}
                alt=""
                width="25px"
                decoding="async"
                className={styles.btnIcon}
              />
              로그 보기
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
