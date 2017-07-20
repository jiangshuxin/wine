import Vuex from 'vuex';
import Vue from 'vue';

import user from './user';
import env from './env';
import mall from './mall';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        user,
        env,
        mall
    },
    strict: process.env.NODE_ENV === 'development'
});

