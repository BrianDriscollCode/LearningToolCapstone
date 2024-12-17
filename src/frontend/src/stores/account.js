import { defineStore } from 'pinia'

export const useAccountStore = defineStore('account', {
    state: () => 
    {
        return { 
            status: false,
            name: '',
            uuid: '',
            onboardingFinished: false,
            initialDataGenerated: false
        }
    },

    actions: 
    {
        logOut()
        {
            this.status = false;
        },

        logIn()
        {
            this.status = true;
        }
    
    }
})