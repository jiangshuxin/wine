export default {
    path: '/shop-cart',
    name: 'shopCart',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/shopCart')));
    }
};
