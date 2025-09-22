import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LightDetails from '../components/LightDetails.vue';
import LightStatusView from "../views/LightStatus/LightStatusView.vue";
import SensorDataView from '../views/EnvironmentData/SensorDataView.vue';

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