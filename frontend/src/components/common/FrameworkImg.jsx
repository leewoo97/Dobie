import springIcon from "../../assets/springIcon.png";
import reactIcon from "../../assets/reactIcon.png";
import vueIcon from "../../assets/vueIcon.png";
import djangoIcon from "../../assets/djangoIcon.png";
import fastapiIcon from "../../assets/fastapiIcon.png";
import mysqlIcon from "../../assets/mysqlIcon.png";
import redisIcon from "../../assets/redisIcon.png";
import mongodbIcon from "../../assets/mongodbIcon.png";
import styles from "./FrameworkImg.module.css";

export default function FrameworkImg({ framework, databaseType }) {
  return (
    <>
      <div className={styles.iconimage}>
        {(framework === "SpringBoot(gradle)" ||
          framework === "SpringBoot(maven)") && (
          <img alt="spring" src={springIcon}></img>
        )}
        {framework === "Django" && <img alt="django" src={djangoIcon}></img>}
        {framework === "Fastapi" && <img alt="fastapiIcon" src={fastapiIcon}></img>}
        {framework === "React" && <img alt="react" src={reactIcon}></img>}
        {framework === "Vue" && <img alt="vue" src={vueIcon}></img>}
        {databaseType === "Mysql" && <img alt="mysql" src={mysqlIcon}></img>}
        {databaseType === "Redis" && <img alt="redis" src={redisIcon}></img>}
        {databaseType === "Mongodb" && (
          <img alt="mongodb" src={mongodbIcon}></img>
        )}
      </div>
    </>
  );
}
