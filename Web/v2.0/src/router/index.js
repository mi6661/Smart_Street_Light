import {createRouter, createWebHashHistory} from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LightStatusView from "../views/data/LightStatus/LightStatusView.vue";
import RealtimeDataView from "../views/data/EnvironmentData/RealtimeDataView.vue";
import SensorAnalysis from "../views/data/EnvironmentData/SensorAnalysis.vue";
import SensorDataView from "../views/data/SensorDataView.vue";
import LightMapView from "../views/map/LightMapView.vue";

/*路由规则*/
const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomeView,
        children: [
            {
                path: '/data',
                name: 'DataView',
                component: SensorDataView,
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
                        name: 'SensorAnalysis',
                        component: SensorAnalysis,
                        props: true
                    }
                ]
            },
            {
                path: '/map',
                name: 'MapView',
                component: LightMapView,
                children: [

                ]
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