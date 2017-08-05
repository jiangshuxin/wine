export default {
    path: '/',
    name: 'home',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/home')));
    }
};
