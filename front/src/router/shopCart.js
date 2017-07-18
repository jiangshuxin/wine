export default {
    path: '/shop-cart',
    name: 'shopCart',
    component(resolve) {
        require.ensure([], () => resolve(require('../modules/shopCart')));
    }
};
