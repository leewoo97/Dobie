import styles from "./RunProjectList.module.css";
import RunProjectItem from "./RunProjectItem";
import useProjectStore from "../../stores/projectStore";

export default function RunProjectList({ setContent }) {
  const { selectedProject } = useProjectStore();

  return (
    <>
      {Object.values(selectedProject.backendMap).length > 0 &&
        Object.values(selectedProject.databaseMap).length > 0 && (
          <div className={styles.bottom}>
            {Object.values(selectedProject.backendMap).map((container) => (
              <RunProjectItem
                key={container.serviceId}
                container={container}
                type="Backend"
                setContent={setContent}
              />
            ))}
            <RunProjectItem
              container={selectedProject.frontend}
              type="Frontend"
              setContent={setContent}
            />
            {Object.values(selectedProject.databaseMap).map((container) => (
              <RunProjectItem
                key={container.databaseId}
                container={container}
                type="Database"
                setContent={setContent}
              />
            ))}
          </div>
        )}
    </>
  );
}
