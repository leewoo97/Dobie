import axios from "axios";
import { useState, useEffect } from "react";
import Container from "../../components/common/Container";
import NavTop from "../../components/common/NavTop"
import styles from "./MainPage.module.css";
import newpjtIcon from "../../assets/newpjtIcon.png";
import guideIcon from "../../assets/guideIcon.png";
import gitlab from "../../assets/gitlab.png";
import github from "../../assets/github.png";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";
import stop from "../../assets/stop.png";

export default function MainPage() {

    // const [projectList, setProjectList] = useState([{
    //     projectName: null,
    //     domainUrl: null,
    //     running: false,
    //     gitLink: null,
    // }]);

    const projectList = [
        {
            projectName: "Mybrary",
            domainUrl: "https://mybrary.com",
            running: false,
            gitLink: "https://lab.ssafy.com/s10-webmobile2-sub2/S10P12B207.git",
            gitType: "gitlab"
        },
        {
            projectName: "NOAH",
            domainUrl: "https://noah.com",
            running: true,
            gitLink: "https://lab.ssafy.com/s10-fintech-finance-sub2/S10P22B106.git",
            gitType: "gitlab"
        },
        {
            projectName: "Cosmos",
            domainUrl: "https://cosmos.com",
            running: false,
            gitLink: "https://github.com/hyeseon97/Cosmos.git",
            gitType: "github"
        }
    ];

    return (
        <>
            <NavTop />
            <div className={styles.topButton}>
                <div className={styles.newpjtBtn}>
                    새 프로젝트 등록
                    <img src={newpjtIcon} alt="" width="30px" decoding="async" className={styles.btnIcon} /></div>
                <div className={styles.guideBtn}>
                    도비 가이드
                    <img src={guideIcon} alt="" width="30px" decoding="async" className={styles.btnIcon} /></div>
            </div>

            <div className={styles.table}>
                <div className={styles.colume}>
                    <div>프로젝트명</div>
                    <div>도메인주소</div>
                    <div>실행</div>
                    <div>Git Link</div>
                </div>
                <div className={styles.projectlist}>
                    {projectList.map((project) => (
                        <div className={styles.content}>
                            <div key={project.projectName}>{project.projectName}</div>
                            <div key={project.domainUrl}>{project.domainUrl}</div>
                            <div className={styles.runButton}>
                                <img src={project.running ? rerun : run} alt="" width="50px" />
                                <img src={stop} alt="" width="50px" ></img>
                            </div>
                            <div><img src={project.gitType == "gitlab" ? gitlab : github} alt="" width="50px" /></div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
}
