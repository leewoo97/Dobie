import { useEffect } from "react";
import styles from "./ProjectList.module.css";
import { getProject } from "../../api/Project";
import useProjectStore from "../../stores/projectStore";

import ProjectItem from "./ProjectItem";

export default function ProjectList() {
  const { projectMap, setProjectMap } = useProjectStore();

  useEffect(() => {
    try {
      getProjectList(); //useEffect안에서 await처리 안돼서 함수로 빼서 실행
    } catch (error) {
      console.error("유저정보 조회 실패 에러: ", error);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getProjectList = async (e) => {
    try {
      const response = await getProject();
      setProjectMap(response.data.data);
    } catch (error) {
      console.error("프로젝트 조회 실패:", error);
    }
  };

  return (
    <div className={styles.table}>
      <div className={styles.colume}>
        <div>프로젝트명</div>
        <div>도메인주소</div>
        <div>빌드</div>
        <div>실행</div>
        <div>Git Link</div>
      </div>
      <div className={styles.projectlist}>
        {!projectMap || projectMap.size === 0 ? (
          <div className={styles.box}>
            <h3>등록된 프로젝트가 없습니다.</h3>
          </div>
        ) : (
          Object.values(projectMap).map((project) => (
            <div key={project.projectId} className={styles.project}>
              <ProjectItem project={project} />
            </div>
          ))
        )}
      </div>
    </div>
  );
}
