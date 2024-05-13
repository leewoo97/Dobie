import { create } from "zustand";
import { persist } from "zustand/middleware";

const useFileStore = create(
  persist(
    (set) => ({
      fileList: [],
      setFileList: (fileList) => {
        set({ fileList: fileList });
      },
      updatedFile: {},
      setUpdatedFile: (file) => {
        set({ updatedFile: file });
      },
      //   createProject: {
      //     projectName: "",
      //     backendMap: [],
      //   },
      //   addBackend: (backend) => {
      //     createProject.backendMap.add(backend);
      //   },
      createdFile : {},
      makeCreatedProject: () => {
        set({createdProject: {
            projectName : "",
            projectDomain : "",
            usingHttps : false,
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
                externalPort: "",
                internalPort: ""
              }
            },
            frontend: {
              serviceName: "",
              language: "",
              version: "",
              framework: "",
              path: "",
              branch: "",
              location: "",
              externalPort: "",
              internalPort: "",
              usingNginx: false
            },
            databaseMap: {
              1: {
                databaseType: "",
                databaseName: "",
                schemaPath: "",
                username: "",
                password: "",
                externalPort: "",
                internalPort: "",
              },
            },
          },
        });
      },
      setCreatedFile: (file) => {
        set({ createdFile: file });
      },
    }),
    {
      name: "file-storage", // 저장될 localStorage의 key 이름
      getStorage: () => localStorage, // 사용할 storage를 명시적으로 지정
    }
  )
);

export default useFileStore;
