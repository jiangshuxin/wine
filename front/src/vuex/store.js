import Vuex from 'vuex';
import Vue from 'vue';
import home from './home';
import user from './user';
import env from './env';
import mall from './mall';
import shopCart from './shopCart';
import mdseDetail from './mdseDetail';
import login from './login';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        home,
        user,
        env,
        mall,
        shopCart,
        mdseDetail,
        login
    },
    strict: process.env.NODE_ENV === 'development'
});

