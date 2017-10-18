export default {
    path: '/',
    redirect: {name: 'manor'},
    name: 'home',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/home')));
    }
};
