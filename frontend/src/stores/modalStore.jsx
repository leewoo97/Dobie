import { create } from "zustand";
import { persist } from "zustand/middleware";

const useModalStore = create(
  persist(
    (set) => ({
      modalOpen: false,
      setModalOpen: (modalOpen) => {
        set({ modalOpen: modalOpen });
      },
      loadingModal: false,
      setLoadingModal: (loadingModal) => {
        set({ loadingModal: loadingModal });
      },
      newModal: false,
      setNewModal: (newModal) => {
        set({ newModal: newModal });
      },
      action: "",
      setAction: (action) => {
        set({ action: action });
      },
      fileType: "",
      setFileType: (fileType) => {
        set({ fileType: fileType });
      },

      logModalOpen: false,
      setLogModalOpen: (logModalOpen) => {
        set({ logModalOpen: logModalOpen });
      },

      logContent: "",
      setLogContent: (logContent) => {
        set({ logContent: logContent });
      },
      logServiceId: "",
      setLogServiceId: (logServiceId) => {
        set({ logServiceId: logServiceId });
      },
    }),
    {
      name: "modal-storage", // 저장될 localStorage의 key 이름
      getStorage: () => localStorage, // 사용할 storage를 명시적으로 지정
    }
  )
);

export default useModalStore;
