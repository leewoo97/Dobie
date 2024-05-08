import styles from "../manage/RunProjectList.module.css";
import springIcon from "../../assets/springIcon.png";
import reactIcon from "../../assets/reactIcon.png";
import vueIcon from "../../assets/vueIcon.png";
import djangoIcon from "../../assets/djangoIcon.png";
import mysqlIcon from "../../assets/mysqlIcon.png";
import redisIcon from "../../assets/redisIcon.png";
import mongodbIcon from "../../assets/mongodbIcon.png";

export default function FrameworkImg({ framework, databaseType }) {
  return (
    <>
      <div>
        {(framework == "SpringBoot(gradle)" ||
          framework == "SpringBoot(maven)") && (
          <img src={springIcon} width="100%"></img>
        )}
        {framework == "Django" && <img src={djangoIcon} width="100%"></img>}
        {framework == "React" && <img src={reactIcon} width="100%"></img>}
        {framework == "Vue" && <img src={vueIcon} width="100%"></img>}
        {databaseType == "Mysql" && <img src={mysqlIcon} width="100%"></img>}
        {databaseType == "Redis" && <img src={redisIcon} width="100%"></img>}
        {databaseType == "Mongodb" && (
          <img src={mongodbIcon} width="100%"></img>
        )}
      </div>
    </>
  );
}
