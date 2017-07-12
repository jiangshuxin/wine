import Vuex from 'vuex';
import Vue from 'vue';

import user from './user';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        user
    },
    strict: process.env.NODE_ENV === 'development'
});

