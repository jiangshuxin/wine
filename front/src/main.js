import 'babel-polyfill';
import 'mint-ui/lib/style.css';
import 'common/global';
import Vue from 'vue';
import app from './app';
import router from './router';
import store from 'vuex/store';
import VueTouch from 'vue-touch';


Vue.use(VueTouch, {
    name: 'v-touch'
});

new Vue({
    el: '#app',
    store,
    router,
    render: h => h(app)
});
