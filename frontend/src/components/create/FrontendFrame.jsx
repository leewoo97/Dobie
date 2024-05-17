import { useEffect, useState } from "react";
import styles from "./FrontendFrame.module.css";
import InputBox from "../common/InputBox";
import ToggleBox from "../common/ToggleBox";
import InputSelectBox from "../common/InputSelectBox";
import DescBox from "../common/DescBox";
import useProjectStore from "../../stores/projectStore";

export default function FrontendFrame() {

  const { createdProject, setCreatedProject } = useProjectStore();
  const [tempProject, setTempProject] = useState({ ...createdProject });

  const frameworkList = ["React", "Vue"];
  const versionList = ["node 20.11.0"];

  const changeFrameworkHandler = (value) => {
    setTempProject((prev) => ({
      ...prev,
      frontend: {
        ...prev.frontend,
        framework: value,
      },
    }));
  };

  const changeVersionHandler = (value) => {
    const splitValue = value.split(' ');
    setTempProject((prev) => ({
      ...prev,
      frontend: {
        ...prev.frontend,
        language: splitValue[0],
        version: splitValue[1],
      }
    }))
  }

  const changePathHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      frontend: {
        ...prev.frontend,
        path: e.target.value,
      },
    }));
  };

  const changeLocationHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      frontend: {
        ...prev.frontend,
        location: e.target.value,
      },
    }));
  };

  const changeInternalPortHandler = (e) => {
    setTempProject((prev) => ({
      ...prev,
      frontend: {
        ...prev.frontend,
        internalPort: e.target.value,
      },
    }));
  };

  const changeUsingNPortHandler = () => {
    setTempProject(prev => ({
      ...prev,
      frontend: {
        ...prev.frontend,
        usingNginx: !prev.frontend.usingNginx,
      },
    }));
  };

  useEffect(() => {
    setCreatedProject(tempProject);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [tempProject]);

  return (
    <div className={styles.page}>
      <InputSelectBox
        keyName={"프레임워크"}
        list={frameworkList}
        value={tempProject.frontend.framework}
        onChange={changeFrameworkHandler}
      />
      <DescBox desc={"Frontend 서비스의 프레임워크를 선택하세요"} />

      <InputSelectBox
        keyName={"언어버전"}
        list={versionList}
        value={tempProject.frontend.language + " " + tempProject.frontend.version}
        onChange={changeVersionHandler}
      />
      <DescBox desc={"Frontend 서비스의 언어 버전을 선택하세요"} />

      <InputBox keyName={"폴더 경로"} valueName={"/frontend"} value={tempProject.frontend.path} onChange={changePathHandler} />
      <DescBox
        desc={"프로젝트 루트 경로로부터 해당 프레임워크 폴더 경로를 작성하세요"}
      />

      <InputBox keyName={"Nginx location"} valueName={"/api"} value={tempProject.frontend.location} onChange={changeLocationHandler} />
      <DescBox
        desc={"Nginx location을 작성하세요"}
      />

      <InputBox keyName={"내부 포트 번호"} valueName={"3000"} value={tempProject.frontend.internalPort} onChange={changeInternalPortHandler} />
      <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

      <ToggleBox keyName={"Nginx 사용"} valueName={"true / false"} value={tempProject.frontend.usingNginx} onChange={changeUsingNPortHandler} isToggle/>
      <DescBox desc={"해당 프레임워크가 사용할 포트 번호를 지정해주세요"} />

    </div>
  );
}
