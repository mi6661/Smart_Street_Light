import { createApp } from 'vue';
import App from './App.vue';
import Antd from 'ant-design-vue';
import router from './router';
import {MotionPlugin} from "@vueuse/motion";
import 'ant-design-vue/dist/reset.css'

createApp(App)
    .use(Antd)
    .use(router)
    .use(MotionPlugin)
    .mount('#app');