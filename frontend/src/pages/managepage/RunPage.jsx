import { useState, useEffect } from "react";
import NavTop from "../../components/common/NavTop";
import NavLeft from "../../components/common/NavLeft";
import InnerContainer from "../../components/common/InnerContainer";
import Container from "../../components/common/Container";
import styles from "./RunPage.module.css";
import run from "../../assets/run.png";
import rerun from "../../assets/rerun.png";


export default function RunPage() {

    const containerList = [
        {
            servieId: "149n9203fnmo3j",
            servieName: "backend",
            framework: "SpringBoot",
            externalPort: 8080,
            internalPort: 8080,
            running: true,
        },
        {
            servieId: "fkj9fn549nkjdf",
            servieName: "frontend",
            framework: "React",
            externalPort: 3000,
            internalPort: 3000,
            running: false,
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
                        <div>프로젝트</div>
                        <div>NOAH</div>
                    </div>
                    <div>
                        <div>수정</div>
                        <div>삭제</div>
                    </div>
                </div>

                <div className={styles.mid}>
                    <div>
                        <div>프로젝트 전체 실행</div>
                        <div>
                            <img src={run} width="10px"></img>
                            <img src={rerun} width="10px"></img>
                        </div>
                    </div>
                    <div>
                        <div>ningx.conf 파일 조회</div>
                        <div>docker-compose.yml 파일 조회</div>
                    </div>
                </div>

                {containerList.length > 0 && (
                    <div className={styles.bottom}>
                        {/* 여기에 원하는 콘텐츠를 추가하세요 */}
                        {containerList.map((container) => (
                            <div className={styles.container}>
                                <div className={styles.containerButton}>

                                </div>
                                <div className={styles.box}>
                                    <div key={container.framework}>{container.framework}</div>
                                    <div key={container.framework}>{container.framework}</div>
                                </div>

                                
                            </div>
                        ))}
                    </div>
                )}
            </div>

        </>
    );
}