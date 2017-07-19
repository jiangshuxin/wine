import VueRouter from 'vue-router';
import Vue from 'vue';
import home from './home';
import mall from './mall';
import shopCart from './shopCart';
import user from './user';

Vue.use(VueRouter);

const notFound = {
    path: '*',
    redirect: '/'
};

const router = new VueRouter({
    routes: [home, mall, shopCart, user, notFound]
});

export default router;
