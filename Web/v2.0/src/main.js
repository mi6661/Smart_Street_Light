import { createApp } from 'vue';
import App from './App.vue';
import Antd from 'ant-design-vue';
import router from './router';
import {MotionPlugin} from "@vueuse/motion";
import 'ant-design-vue/dist/reset.css'


/*腾讯地图配置*/
import Tmap from '@map-component/vue-tmap';

createApp(App)
    .use(Antd)
    .use(router)
    .use(MotionPlugin)
    .use(Tmap)
    .mount('#app');