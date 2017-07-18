export default {
    path: '/mall',
    name: 'mall',
    component(resolve) {
        require.ensure([], () => resolve(require('../modules/mall')));
    }
};
