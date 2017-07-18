export default {
    path: '/',
    name: 'home',
    component(resolve) {
        require.ensure([], () => resolve(require('../modules/home')));
    }
};
