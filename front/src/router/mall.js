export default {
    path: '/mall',
    name: 'mall',
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/mall')));
    }
};
