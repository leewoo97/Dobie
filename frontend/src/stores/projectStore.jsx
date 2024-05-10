import { create } from "zustand";
import { persist } from "zustand/middleware";

const useProjectStore = create(
  persist(
    (set) => ({
      projectMap: new Map(),
      setProjectMap: (projectMap) => {
        set({ projectMap: projectMap });
      },
      selectedProject: {},
      setSelectedProject: (selectedProject) => {
        set({ selectedProject: selectedProject });
      },
      updatedProject: {},
      setUpdatedProject: (project) => {
        set({ updatedProject: project });
      },
      //   createProject: {
      //     projectName: "",
      //     backendMap: [],
      //   },
      //   addBackend: (backend) => {
      //     createProject.backendMap.add(backend);
      //   },
      newProject: {},
      makeNewProject: () => {
        set({
          newProject: {
            projectName: "",
            projectDomain: "",
            usingHttps: false,
            git: {
              gitType: 0,
              gitUrl: "",
              accessToken: "",
              branch: "",
            },
            backendMap: {
              1: {
                serviceName: "",
                language: "",
                version: "",
                framework: "",
                path: "",
                branch: "",
                location: "",
                externalPort: 0,
                internalPort: 0,
              },
            },
            frontend: {
              serviceName: "",
              language: "",
              version: "",
              framework: "",
              path: "",
              branch: "",
              location: "",
              externalPort: 0,
              internalPort: 0,
              usingNginx: false,
            },
            databaseMap: {
              1: {
                databaseType: "",
                databaseName: "",
                schemaPath: "",
                username: "",
                password: "",
                externalPort: 0,
                internalPort: 0,
              },
            },
          },
        });
      },
      setNewProject: (project) => {
        set({ newProject: project });
      },
      checkProceed: {},
      setCheckProceed: (checkProceed) => {
        set({ checkProceed: checkProceed });
      },
    }),
    {
      name: "project-storage", // 저장될 localStorage의 key 이름
      getStorage: () => localStorage, // 사용할 storage를 명시적으로 지정
    }
  )
);

export default useProjectStore;
