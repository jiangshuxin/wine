import 'babel-polyfill';
import Vue from 'vue';
import app from './app';
// import store from 'vuex/store';

new Vue({
    el: '#app',
    render: h => h(app)
});
