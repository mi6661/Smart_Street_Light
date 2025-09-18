import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/light': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
      '/sensor': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      }
    }
  }
});