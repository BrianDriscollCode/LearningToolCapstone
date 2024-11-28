import { mount } from '@vue/test-utils';
import { createPinia } from "pinia";
import App from "../src/App.vue"

jest.mock('vue-router', () => ({
    useRoute: () => ({
      name: 'userHome',
    }),
  }));

describe('App.vue', () => {
    it('renders a message', () => {
        const pinia = createPinia();
        const wrapper = mount(App, {
            global: {
                plugins: [pinia],
            },
        });
        expect(wrapper.text()).toContain("Hello");
    })
})