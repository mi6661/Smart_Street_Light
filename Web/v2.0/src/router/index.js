import {createRouter, createWebHashHistory} from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LightStatusView from "../views/LightStatus/LightStatusView.vue";
import RealtimeDataView from "../views/EnvironmentData/RealtimeDataView.vue";
import StatisticsView from "../views/EnvironmentData/StatisticsDataView.vue";

/*路由规则*/
const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomeView,
        children: [
            {
                path: '/lights/:district',
                name: 'LightsStatus',
                component: LightStatusView,
                props: true
            },
            {
                path: '/sensor/realtime',
                name: 'RealTimeView',
                component: RealtimeDataView,
                props: true
            },
            {
                path: '/sensor/statistics',
                name: 'StatisticsView',
                component: StatisticsView,
                props: true
            }
        ]
    }
];

/*路由器*/
const router = createRouter({
    /*路由方式*/
    history: createWebHashHistory(),
    /*路由规则*/
    routes
});

export default router;