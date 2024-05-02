import { build } from "../api/project/Project";

export default function Test() {
  const handleTest = async () => {
    const projectId = 1;
    const object = {
      projectName: "test",
      git: {
        gitType: 1,
        gitUrl: "https://lab.ssafy.com/o54711254/test.git",
        accessToken: "dQgja5Wxxrc6xvQfTHGr",
      },
      backendMap: {
        1: {
          serviceName: "backend",
          language: "java",
          version: "17",
          framework: "Spring",
          path: "/backend",
          branch: "master",
          externalPort: 8089,
          internalPort: 8080,
        },
      },
      frontend: {
        serviceName: "frontend",
        language: "node",
        version: "20.11",
        framework: "React",
        path: "/frontend",
        branch: "/main",
        externalPort: 3001,
        internalPort: 3000,
        usingNginx: false,
      },
      database: {},
    };

    console.log("보내는거", projectId, object);
    const res = await build(projectId, object);
    console.log(res);
  };
  return (
    <>
      <div>실험용 페이지</div>
      <div>
        <button
          onClick={() => {
            handleTest();
          }}
        >
          실험용 버튼
        </button>
      </div>
    </>
  );
}
