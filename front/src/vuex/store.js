import Vuex from 'vuex';
import Vue from 'vue';

import user from './user';
import env from './env';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        user,
        env
    },
    strict: process.env.NODE_ENV === 'development'
});

