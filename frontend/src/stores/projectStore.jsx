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
        set({updatedProject : project});
      },
      //   createProject: {
      //     projectName: "",
      //     backendMap: [],
      //   },
      //   addBackend: (backend) => {
      //     createProject.backendMap.add(backend);
      //   },
    }),
    {
      name: "project-storage", // 저장될 localStorage의 key 이름
      getStorage: () => localStorage, // 사용할 storage를 명시적으로 지정
    }
  )
);

export default useProjectStore;
