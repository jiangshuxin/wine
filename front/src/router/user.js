export default {
    path: '/user',
    name: 'user',
    component(resolve) {
        require.ensure([], () => resolve(require('../modules/user')));
    }
};
