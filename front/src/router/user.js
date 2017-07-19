export default {
    path: '/user',
    name: 'user',
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user')));
    }
};
