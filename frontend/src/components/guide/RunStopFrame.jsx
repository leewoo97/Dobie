import styles from "./RunStopFrame.module.css";
import Run from "../../assets/run.png";
import Restart from "../../assets/restart.png";
import RunAllServices1 from "../../assets/guide/RunAllServices1.png";
import RunAllServices2 from "../../assets/guide/RunAllServices2.png";
import RunAllServices3 from "../../assets/guide/RunAllServices3.png";
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
            프로젝트가 정상적으로 등록되면 메인화면에 다음과 같이 표시됩니다.
          </div>
          <div className={styles.textWithImg}>
            <img src={Run} alt="run" className={styles.runIcon} />
            아이콘을 클릭하여 전체 서비스를 실행시킵니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={RunLoading} className={styles.loadingImg} />
            <figcaption className={styles.caption}>Loading</figcaption>
          </figure>
          <div>정상적으로 실행시켰다면 Building 이미지가 표시됩니다.</div>
          <figure className={styles.imgBox}>
            <img alt="name" src={RunAllServices2} className={styles.runImg} />
            <figcaption className={styles.caption}>
              Run All Services 2
            </figcaption>
          </figure>
          <div className={styles.textWithImg}>
            <img src={Restart} alt="run" className={styles.runIcon} />로 실행
            버튼이 바뀐다면 정상적으로 실행이 완료된 것 입니다.
          </div>
          <figure className={styles.imgBox}>
            <img alt="name" src={RunAllServices3} className={styles.runImg} />
            <figcaption className={styles.caption}>
              Run All Services3
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
