import 'babel-polyfill';
import 'mint-ui/lib/style.css';
import 'common/global';
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
