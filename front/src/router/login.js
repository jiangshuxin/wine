export default {
    name: 'login',
    path: '/login',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/login')));
    }
};
