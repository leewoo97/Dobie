import { create } from 'zustand'
import { persist } from 'zustand/middleware'

const useUserStore = create(persist(    
    set => ({
        user: {
            username: "",
            password: "",
        },
        setUser: (user) => {
            set({user : user});
        },
    })
))

export default useUserStore;