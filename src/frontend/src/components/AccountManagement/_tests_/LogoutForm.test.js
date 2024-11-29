import { mount } from "@vue/test-utils";
import { describe, it, expect, vi } from 'vitest';
import LogoutForm from '../LogoutForm.vue';
import { supabase } from '@/clients/supabase';

describe('LogoutForm.vue', () => {
    it("Logs out successfully", async () => {
        
        vi.spyOn(supabase.auth, "signOut").mockResolvedValueOnce({});

        const wrapper = mount (LogoutForm);

        await wrapper.find("form").trigger("submit.prevent");

        expect(supabase.auth.signOut).toHaveBeenCalled();
        
    }); 
})