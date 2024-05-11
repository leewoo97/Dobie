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
      action: "",
      setAction: (action) => {
        set({ action: action });
      },
      fileType: "",
      setFileType: (fileType) => {
        set({ fileType: fileType });
      },
    }),
    {
      name: "modal-storage", // 저장될 localStorage의 key 이름
      getStorage: () => localStorage, // 사용할 storage를 명시적으로 지정
    }
  )
);

export default useModalStore;
