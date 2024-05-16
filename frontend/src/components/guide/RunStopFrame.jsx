import styles from "./RunStopFrame.module.css";
import Run from "../../assets/run.png";
import Build from "../../assets/settings.png";
import Restart from "../../assets/restart.png";
import RunAllServices1 from "../../assets/guide/RunAllServices1.png";
import RunAllServices3 from "../../assets/guide/RunAllServices3.png";
import Building1 from "../../assets/guide/Building1.png";
import RunLoading from "../../assets/guide/RunLoading.png";
import RunStatus from "../../assets/guide/RunStatus.png";
export default function RunStopFrame() {
  return (
    <div id="run" className={styles.guideFrame}>
      <div className={styles.header}>
        <div className={styles.title}>Run & Stop</div>
      </div>
      <div className={styles.normal}>
        <div id="all" className={styles.section}>
          <div className={styles.strength}>1. All Services</div>
          <div>모든 서비스를 한번에 실행시키는 방법은 다음과 같습니다.</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={RunAllServices1} className={styles.runImg} />
            <figcaption className={styles.caption}>
              Run All Services 1
            </figcaption>
          </figure>
          <div>
            프로젝트가 정상적으로 등록되면 메인화면에 위와 같이 표시됩니다.
          </div>
          <div className={styles.textWithImg}>
            <img src={Build} alt="run" className={styles.runIcon} />
            아이콘을 클릭하여 전체 서비스를 빌드시킵니다.
          </div>
          <div>
            <b>
              Dobie에서의 빌드는 빌드에 사용되는 Dockerfile, Docker-compose.yml
              파일 등을 적절한 위치에 생성하는 과정입니다.
            </b>
          </div>
          <div>
            빌드는 실행될때마다 Git Pull, 빌드파일 생성의 과정을 진행합니다.
          </div>
          <div>
            즉 변경사항이 있을때 Webhook 설정을 하지 않았다면
            <b>
              {" "}
              빌드 버튼을 누르고 재실행 하는 것으로 프로젝트를 최신화 할 수
              있습니다.
            </b>
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={Building1} className={styles.buildingImg1} />
            <figcaption className={styles.caption}>Loading</figcaption>
          </figure>
          <div>빌드를 완료한 후 전체 서비스를 실행합니다.</div>
          <div className={styles.textWithImg}>
            <img src={Run} alt="run" className={styles.runIcon} />
            아이콘을 클릭하여 전체 서비스를 실행시킵니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={RunLoading} className={styles.loadingImg} />
            <figcaption className={styles.caption}>Loading</figcaption>
          </figure>
          <div>정상적으로 실행시켰다면 Running 이미지가 표시됩니다.</div>

          <div className={styles.textWithImg}>
            <img src={Restart} alt="run" className={styles.runIcon} />로 실행
            버튼이 바뀐다면 정상적으로 실행이 완료된 것 입니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={RunAllServices3} className={styles.runImg} />
            <figcaption className={styles.caption}>
              Run All Services2
            </figcaption>
          </figure>
          <div>
            프로젝트 관리 페이지에서도 같은 방법으로 서비스를 전체 실행 & 정지
            시킬 수 있습니다.
          </div>
        </div>
        <div id="each" className={styles.section}>
          <div className={styles.strength}>2. Each Service</div>
          <div>
            프로젝트를 개별적으로 실행&정지 시키기 위해서는 <b>전체 실행</b>이
            선행되어야 합니다.
          </div>
          <div>프로젝트를 전체 실행 시켰을때 관리페이지는 다음과 같습니다.</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={RunStatus} className={styles.runStatusImg} />
            <figcaption className={styles.caption}>Run Status</figcaption>
          </figure>
          <div>
            활성화 된 각 서비스의 버튼을 통해 서비스를 개별적으로 실행&정지 시킬
            수 있습니다.
          </div>
        </div>
      </div>
    </div>
  );
}
