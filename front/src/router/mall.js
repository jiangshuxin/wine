const mdseList = {
    name: 'mdseList',
    path: '/',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/mall/mdseList')));
    }
};

const mdseDetail = {
    name: 'mdseDetail',
    path: 'mdse-detail',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/mall/mdseDetail')));
    }
};

const shopCart = {
    name: 'mdseShopCart',
    path: 'mdse-shopcart',
    meta: {
        requiresQuery: ['merchantId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/shopCart')));
    }
};


export default {
    path: '/mall',
    name: 'mall',
    redirect: {name: 'mdseList'},
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/mall')));
    },
    children: [mdseList, mdseDetail, shopCart]
};
