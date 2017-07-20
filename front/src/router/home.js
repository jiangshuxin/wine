export default {
    path: '/',
    name: 'home',
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/home')));
    }
};
