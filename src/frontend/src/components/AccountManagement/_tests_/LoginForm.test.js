import { mount } from '@vue/test-utils';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import LoginForm from '../LoginForm.vue';
import { createPinia } from 'pinia';
import { createRouter, createWebHistory } from 'vue-router';
import { supabase } from '@/clients/supabase';
import { useAccountStore } from '@/stores/account';

describe('LoginForm.vue', () => {
  let pinia;
  let router;
  let mockRouterPush;

  beforeEach(() => {
    pinia = createPinia(); 
    router = createRouter({
      history: createWebHistory(),
      routes: [{ path: '/', component: { template: '<div></div>' } }],
    });

    mockRouterPush = vi.fn();
    router.push = mockRouterPush;
  });

  it('logs in successfully and navigates to dashboard', async () => {
    vi.spyOn(supabase.auth, 'signInWithPassword').mockResolvedValueOnce({
        data: { user: { id: 'fb984c31-4f57-4552-8739-e75dd4c5bd7d'} },
        error: null,
    });

    vi.spyOn(global, 'fetch').mockResolvedValueOnce({
        json: () => Promise.resolve({ name: 'John Doe'}),
    });

    const wrapper = mount (LoginForm, {
        global: {
            plugins: [pinia, router],
        },
    });

    await wrapper.vm.login();

    const accountStore = useAccountStore();
    // Testing that reactive variable gets populated with login info
    expect(accountStore.status).toBe(true);
    expect(accountStore.name).toBe('John Doe');
    expect(accountStore.uuid).toBe('fb984c31-4f57-4552-8739-e75dd4c5bd7d');
    // Testing that the page reroutes correctly
    expect(mockRouterPush).toHaveBeenCalledWith('/platform/dashboard');
  });

  it('renders "Sign up" text correctly', () => {
    const wrapper = mount(LoginForm, {
      global: {
        plugins: [pinia, router],
      },
    });
  });
});

