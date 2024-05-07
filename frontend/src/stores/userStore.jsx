import { create } from "zustand";
import { persist } from "zustand/middleware";

const useUserStore = create(
  persist(
    (set) => ({
      user: {
        username: "",
        password: "",
      },
      setUser: (user) => {
        set({ user: user });
      },
    }),
    {
      name: "user-storage", // 저장될 localStorage의 key 이름
      getStorage: () => localStorage, // 사용할 storage를 명시적으로 지정
    }
  )
);

export default useUserStore;
