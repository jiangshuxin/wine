export default {
    path: '/manor',
    name: 'manor',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/manor')));
    }
};
