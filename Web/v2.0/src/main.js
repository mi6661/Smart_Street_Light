import { createApp } from 'vue';
import App from './App.vue';
import Antd from 'ant-design-vue';
import router from './router';
import {MotionPlugin} from "@vueuse/motion";
import 'ant-design-vue/dist/reset.css'


/*高德地图配置*/
import VueAMap, {initAMapApiLoader} from '@vuemap/vue-amap';
import '@vuemap/vue-amap/dist/style.css'
initAMapApiLoader({
    key: '0850aa0c1da5de0da520f17c9e323056',
    securityJsCode: 'fa75915c731f39f2d4d7d0089427329d', // 新版key需要配合安全密钥使用
})

createApp(App)
    .use(Antd)
    .use(router)
    .use(MotionPlugin)
    .use(VueAMap)
    .mount('#app');