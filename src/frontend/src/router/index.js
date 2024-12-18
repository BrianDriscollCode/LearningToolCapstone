import { createRouter, createWebHistory } from 'vue-router'

// General
import HomeView from '@/views/HomeView.vue'
import DataView from "@/views/DataView.vue"
import UnauthorizedView from '@/views/UnauthorizedView.vue'
import AccountManagerConsole from '@/components/AccountManagement/AccountManagerConsole.vue'
import LoginView from '@/views/LoginView.vue'
import SignUpView from '@/views/SignUpView.vue'

import { supabase } from '@/clients/supabase'

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
        component: LoginView
    },
    {
        path: '/signup',
        name: 'signUpPage',
        component: SignUpView
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
    },
    {
        path: '/platform/onboarding',
        name: 'onboarding',
        component: () => import('../views/Platform/OnboardingView.vue'),
            meta: { requiresAuth: true }
    },
    {
        path: '/platform/dashboard',
        name: 'dashboard',
        component: () => import('../views/Platform/DashboardView.vue'),
            meta: { requiresAuth: true}
    },
    {
        path: '/platform/schedule',
        name: 'schedule',
        component: () => import('../views/Platform/ScheduleView.vue'),
            meta: { requiresAuth: true}
    },
    {
        path: '/platform/learningMap',
        name: 'learningMap',
        component: () => import('../views/Platform/LearningMapView.vue'),
            meta: { requiresAuth: true}
    },
    {
        path: '/platform/study',
        name: 'studyView',
        component: () => import('../views/Platform/StudyView.vue'),
            meta: { requiresAuth: true}
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