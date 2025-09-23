import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LightDetails from '../components/LightDetails.vue';
import LightStatusView from "../views/LightStatus/LightStatusView.vue";
import SensorDataView from '../views/EnvironmentData/SensorDataView.vue';
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
                path: '/sensors/:choice',
                name: 'SensorDataView',
                component: SensorDataView,
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
    },
    {
        path: '/details/:id',
        name: 'LightDetails',
        component: LightDetails,
        props: true
    },
];

/*路由器*/
const router = createRouter({
    /*路由方式*/
    history: createWebHistory(),
    /*路由规则*/
    routes
});

export default router;