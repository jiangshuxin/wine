const list = {
    name: 'userList',
    path: '/',
    meta: {
        requiresQuery: ['merchantId'],
        requiresAuth: ['userId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user/list')));
    } };

const address = {
    name: 'address',
    path: '/address',
    redirect: {name: 'addressList'},
    meta: {
        requiresQuery: ['merchantId'],
        requiresAuth: ['userId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user/address')));
    },
    children: [{
        name: 'addressList',
        path: '/',
        meta: {
            requiresQuery: ['merchantId'],
            requiresAuth: ['userId']
        },
        component(resolve) {
            require.ensure([], require => resolve(require('../modules/user/address/list')));
        }
    }, {
        name: 'addressCreate',
        path: '/address/create',
        meta: {
            requiresQuery: ['merchantId'],
            requiresAuth: ['userId']
        },
        component(resolve) {
            require.ensure([], require => resolve(require('../modules/user/address/detail')));
        }
    }, {
        name: 'addressModify',
        path: '/address/modify',
        meta: {
            requiresQuery: ['merchantId', 'addressId'],
            requiresAuth: ['userId']
        },
        component(resolve) {
            require.ensure([], require => resolve(require('../modules/user/address/detail')));
        }
    }]
};

const order = {
    name: 'order',
    path: '/order',
    redirect: {name: 'orderList'},
    meta: {
        requiresQuery: ['merchantId'],
        requiresAuth: ['userId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user/order')));
    },
    children: [{
        name: 'orderList',
        path: '/',
        meta: {
            requiresQuery: ['merchantId'],
            requiresAuth: ['userId']
        },
        component(resolve) {
            require.ensure([], require => resolve(require('../modules/user/order/list')));
        }
    }, {
        name: 'orderDetail',
        path: '/order/detail',
        meta: {
            requiresQuery: ['merchantId', 'orderId'],
            requiresAuth: ['userId']
        },
        component(resolve) {
            require.ensure([], require => resolve(require('../modules/user/order/detail')));
        }
    }]
};

const bills = {
    name: 'bills',
    path: '/bills',
    meta: {
        requiresQuery: ['merchantId', 'mdseId'],
        requiresAuth: ['userId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user/bills')));
    }
};

const gopay = {
    name: 'gopay',
    path: '/gopay',
    meta: {
        requiresQuery: ['merchantId', 'orderId'],
        requiresAuth: ['userId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user/pay')));
    }
};

const personal = {
    name: 'personal',
    path: '/personal',
    redirect: {name: 'personalList'},
    meta: {
        requiresQuery: ['merchantId'],
        requiresAuth: ['userId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user/personal')));
    },
    children: [{
        name: 'personalList',
        path: '/',
        meta: {
            requiresQuery: ['merchantId'],
            requiresAuth: ['userId']
        },
        component(resolve) {
            require.ensure([], require => resolve(require('../modules/user/personal/list')));
        }
    }]
};

export default {
    name: 'user',
    path: '/user',
    redirect: {name: 'userList'},
    meta: {
        requiresQuery: ['merchantId'],
        requiresAuth: ['userId']
    },
    component(resolve) {
        require.ensure([], require => resolve(require('../modules/user')));
    },
    children: [list, address, order, bills, gopay, personal]
};
