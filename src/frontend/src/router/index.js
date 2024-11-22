import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import DataView from "@/views/DataView.vue"
import UnauthorizedView from '@/views/UnauthorizedView.vue'
import AccountManagerConsole from '@/components/AccountManagement/AccountManagerConsole.vue'

import { supabase } from '@/clients/supabase'
import LoginView from '@/views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: [
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/login',
        name: 'loginPage',
        componet: LoginView
    },
    {
        path: '/secrets',
        name: 'secretPage',
        component: () => import('../views/SecretView.vue'),
            meta: { requiresAuth: true }
    },
    {
        path: '/unauthorized',
        names: 'unauthorizedPage',
        component: UnauthorizedView
    },
    
    {
        path: '/data',
        name: 'dataPage',
        component: DataView
    },
    {
        path: '/accountmanagerconsole',
        name: 'accountManagerConsole',
        component: AccountManagerConsole
    }
]
})

let localUser;

async function getUser(next)
{
    localUser = await supabase.auth.getSession();

    if (localUser.data.session == null)
    {
        next('./unauthorized');
    }
    else
    {
        next();
    }
}

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth)
    {
        getUser(next);
    }
    else
    {
        next();
    }
})

export default router