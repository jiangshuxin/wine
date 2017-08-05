import VueRouter from 'vue-router';
import Vue from 'vue';
import home from './home';
import mall from './mall';
import shopCart from './shopCart';
import user from './user';
import login from './login';
import { getUserAuth } from 'modules/common/auth';

Vue.use(VueRouter);

const notFound = {
    path: '*',
    redirect: '/'
};

const router = new VueRouter({
    routes: [home, mall, shopCart, user, login, notFound]
});

router.beforeEach(async (to, from, next) => {
    if (to.meta.requiresQuery && to.meta.requiresQuery.some(item => !to.query[item])) {
        console.warn('[router] query unmet: ', from.path, to.path, to.query);
        next(false);
        return;
    }
    if (to.matched.some(match => match.meta.requiresAuth)) {
        const hasAuth = await getUserAuth(to.meta.requiresAuth);
        if (!hasAuth) {
            next({name: 'login', query: to.query});
            return;
        }
    }
    next();
});

export default router;