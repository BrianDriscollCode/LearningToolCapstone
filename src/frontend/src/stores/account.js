import { defineStore } from 'pinia'

export const useAccountStore = defineStore('account', {
    state: () => {
        return { 
            status: false,
            name: '',
            // Either get item or empty
            uuid: localStorage.getItem('uuid') || '',  
            initialDataGenerated: false
        }
    },

    actions: {
        // Processes for login and logout to hydrate data for store.
        // Also uses localStorage for refresh persistence.
        // APP level will always use SupaBase, but platform level will use store
        logOut() {
            this.status = false;
            this.uuid = '';  
            localStorage.removeItem('uuid'); 
        },

        logIn(uuid) {
            this.status = true;
            this.uuid = uuid; 
            localStorage.setItem('uuid', uuid); 
        }
    }
});