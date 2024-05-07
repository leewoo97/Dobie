import { useState, useEffect } from "react";
import NavTop from "../../components/common/NavTop";
import NavLeft from "../../components/common/NavLeft";
import InnerContainer from "../../components/common/InnerContainer";
import Container from "../../components/common/Container";
import styles from "./RunPage.module.css";
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
import edit from "../../assets/editIcon.png";
import remove from "../../assets/deleteIcon.png";
import setting from "../../assets/settingIcon.png";
import document from "../../assets/documentIcon.png";
import log from "../../assets/logIcon.png";



export default function RunPage() {

    const containerList = [
        {
            servieId: "149n9203fnmo3j",
            servieName: "backend",
            framework: "SpringBoot",
            externalPort: 8080,
            internalPort: 8080,
            running: "Running :)",
        },
        {
            servieId: "fkj9fn549nkjdf",
            servieName: "frontend",
            framework: "React",
            externalPort: 3000,
            internalPort: 3000,
            running: "Stopped :(",
        },
        {
            servieId: "54lkj598jtiu34hf",
            servieName: "database",
            framework: "Mysql",
            externalPort: 3306,
            internalPort: 3306,
            running: "Stopped :(",
        },
        {
            servieId: "23io8fjk,jdsfkj",
            servieName: "database",
            framework: "Redis",
            externalPort: 5678,
            internalPort: 5678,
            running: "Running :)",
        },
    ]

    return (
        <>
            <NavTop />
            <NavLeft num={1}/>
            {/* <InnerContainer /> */}
            <div className={styles.page}>
                <div className={styles.top}>
                    <div>
                        <div className={styles.text}>프로젝트</div>
                        <div className={styles.projectName}>NOAH</div>
                    </div>
                    <div className={styles.buttons}>
                        <div className={styles.webhook}>Webhook 설정 <img src={setting} alt="" height="20px" decoding="async" className={styles.btnIcon} /></div>
                        <div className={styles.edit}>수정 <img src={edit} alt="" height="20px" decoding="async" className={styles.btnIcon} /></div>
                        <div className={styles.remove}>삭제 <img src={remove} alt="" width="23px" decoding="async" className={styles.btnIcon} /></div>
                    </div>
                </div>

                <div className={styles.mid}>
                    <div>
                        <div className={styles.text}>프로젝트 전체 실행</div>
                        <div className={styles.runButton}>
                            <img src={run} width="40px"></img>
                            <img src={rerun} width="40px"></img>
                        </div>
                    </div>
                    <div className={styles.buttons}>
                        <div className={styles.fileButton}>ningx.conf 파일 조회 <img src={document} alt="" width="30px" decoding="async" className={styles.btnIcon} /></div>
                        <div className={styles.fileButton}>docker-compose.yml 파일 조회 <img src={document} alt="" width="30px" decoding="async" className={styles.btnIcon} /></div>
                    </div>
                </div>

                {containerList.length > 0 && (
                    <div className={styles.bottom}>
                        {containerList.map((container) => (
                            <div className={styles.container}>
                                <div className={styles.containerButton}>
                                    <div className={styles.runButton}>
                                        <img src={container.running=="Running :)" ? rerun : run} alt="" width="30px" />
                                        <img src={stop} width="30px"></img>
                                    </div>
                                    {(container.servieName == "backend" || container.servieName == "frontend") && (<div className={styles.fileButton}>
                                        Dockerfile 파일 조회<img src={document} alt="" width="25px" decoding="async" className={styles.btnIcon} />
                                    </div>)}

                                </div>
                                <div className={styles.box}>

                                    <div className={styles.boxTop}>
                                        <table>
                                            <tr>
                                                <td key={container.servieName} className={styles.serviceName}>{container.servieName}</td>
                                                <td key={container.externalPort}>외부포트 {container.externalPort}</td>
                                            </tr>
                                            <tr>
                                                <td key={container.framework}>{container.framework}</td>
                                                <td key={container.internalPort}>내부포트 {container.internalPort}</td>
                                            </tr>
                                        </table>
                                        <div className={styles.icon}>
                                            <img src={springIcon} width="100%"></img>
                                        </div>
                                    </div>
                                    <div className={styles.line}></div>
                                    <div className={styles.boxBottom}>
                                        <div className={container.running == "Running :)" ? styles.running : styles.stopped}
                                            key={container.running}>{container.running}
                                        </div>
                                        <div className={styles.log}><img src={log} alt="" width="25px" decoding="async" className={styles.btnIcon} />로그 보기</div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>

        </>
    );
}