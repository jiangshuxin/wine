export default {
    path: '/shop-cart',
    name: 'shopCart',
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/shopCart')));
    }
};
