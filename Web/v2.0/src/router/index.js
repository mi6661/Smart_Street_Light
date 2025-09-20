import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LightDetails from '../components/LightDetails.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomeView
    },
    {
        path: '/details/:id',
        name: 'LightDetails',
        component: LightDetails,
        props: true
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;