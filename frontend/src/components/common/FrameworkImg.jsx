import springIcon from "../../assets/springIcon.png";
import reactIcon from "../../assets/reactIcon.png";
import vueIcon from "../../assets/vueIcon.png";
import djangoIcon from "../../assets/djangoIcon.png";
import mysqlIcon from "../../assets/mysqlIcon.png";
import redisIcon from "../../assets/redisIcon.png";
import mongodbIcon from "../../assets/mongodbIcon.png";
import styles from "./FrameworkImg.module.css";

export default function FrameworkImg({ framework, databaseType }) {
  return (
    <>
      <div className={styles.iconimage}>
        {(framework == "SpringBoot(gradle)" ||
          framework == "SpringBoot(maven)") && (
          <img src={springIcon} ></img>
        )}
        {framework == "Django" && <img src={djangoIcon} ></img>}
        {framework == "React" && <img src={reactIcon}></img>}
        {framework == "Vue" && <img src={vueIcon}></img>}
        {databaseType == "Mysql" && <img src={mysqlIcon}></img>}
        {databaseType == "Redis" && <img src={redisIcon}></img>}
        {databaseType == "Mongodb" && (
          <img src={mongodbIcon} ></img>
        )}
      </div>
    </>
  );
}
