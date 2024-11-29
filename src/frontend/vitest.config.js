import { defineConfig } from 'vitest/config';
import vue from '@vitejs/plugin-vue';
import * as dotenv from 'dotenv';

dotenv.config({ path: '.env.test' });

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': '/src', // Allows usage of '@' as an alias for the '/src' directory
    },
  },
  test: {
    globals: true,
    environment: 'jsdom', 
    setupFiles: './tests/setup.js', 
    css: true,
    coverage: {
      reporter: ['text', 'json', 'html'], // Enables coverage reporting in various formats
    },
  },
});