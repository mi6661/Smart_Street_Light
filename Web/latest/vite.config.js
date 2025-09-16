import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/light': {
        target: 'http://localhost:8080', // 目标后端地址
        changeOrigin: true, // 改变源，让后端认为请求来自它的域名
        rewrite: (path) => path.replace(/^\/light/, '/light') // 可选，如果API路径不同
      },
      '/sensor': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/sensor/, '/sensor')
      }
    }
  }
});