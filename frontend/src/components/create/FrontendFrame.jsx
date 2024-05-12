import { useState } from "react";
import styles from "./FrontendFrame.module.css";
import InputBox from "../common/InputBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";

export default function FrontendFrame() {
  const frameworkList = ["React", "Vue"];

  const versionList = ["node 20.11.0"];

  const [framework, setFramework] = useState("");
  const [version, setVersion] = useState("");

  const frameworkSelect = (value) => {
    setFramework(value);
    console.log(value);
  };

  const versionSelect = (value) => {
    setVersion(value);
    console.log(value);
  };

  return (
    <div className={styles.page}>
      <InputSelectBox
        keyName={"프레임워크"}
        list={frameworkList}
        value={framework}
        onChange={frameworkSelect}
      />
      <DescBox desc={"Frontend 서비스의 프레임워크를 선택하세요"} />

      <InputSelectBox
        keyName={"언어버전"}
        list={versionList}
        value={version}
        onChange={versionSelect}
      />
      <DescBox desc={"Frontend 서비스의 언어 버전을 선택하세요"} />

      <InputBox keyName={"폴더 경로"} valueName={"/frontend"} />
      <DescBox
        desc={"프로젝트 루트 경로로부터 해당 프레임워크 폴더 경로를 작성하세요"}
      />

      <InputBox keyName={"브랜치"} valueName={"dev-fe"} />
      <DescBox desc={"해당 프레임워크를 빌드 시킬 브랜치명을 작성하세요"} />

      <InputBox keyName={"내부 포트 번호"} valueName={"3000"} />
      <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

      <InputBox keyName={"외부 포트 번호"} valueName={"3000"} />
      <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />
    </div>
  );
}
