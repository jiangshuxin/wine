import 'babel-polyfill';
import 'muse-components/styles/base.less';
import Vue from 'vue';
import app from './app';
import router from './router';
import store from 'vuex/store';

new Vue({
    el: '#app',
    store,
    router,
    render: h => h(app)
});
