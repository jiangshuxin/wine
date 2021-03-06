export default {
    merchantId: '',
    isPC: false,
    // 初始选中tab
    selectedTab: 'home',
    // 全局提示
    hintMsg: '',
    // tab统一配置管理
    /*
    {
        id: 'home',
        text: '首页',
        iconClass: 'icon-home-copy'
    },
    */
    tabList: [{
        id: 'manor',
        text: '酒庄',
        iconClass: ['icon-wodezhuangyuan', 'icon-format']
    }, {
        id: 'mall',
        text: '商城',
        iconClass: 'icon-103'
    }, {
        id: 'shopCart',
        text: '购物车',
        iconClass: 'icon-gouwuche1'
    }, {
        id: 'user',
        text: '我的',
        iconClass: 'icon-wode-copy'
    }],
    // 路由name和tabId映射关系
    // 存在映射关系的将展示tab, 并选中tab
    // key 为路由名称
    selectedMap: {
        home: 'home',
        mall: 'mall',
        mdseList: 'mall',
        manor: 'manor',
        shopCart: 'shopCart',
        user: 'user',
        userList: 'user'
    },
    // head信息统一配置管理
    // key 为路由名称
    headMap: {
        /*
         *home: {
         *    text: '首页'
         *},
         */
        shopCart: {
            text: '购物车'
        },
        mdseShopCart: {
            text: '购物车',
            goBack: true
        },
        /*
         *manor: {
         *    text: '酒庄'
         *},
         */
        personalList: {
            text: '个人中心',
            goBack: true
        },
        addressList: {
            text: '我的地址',
            goBack: true
        },
        addressCreate: {
            text: '新增地址',
            goBack: true
        },
        addressModify: {
            text: '修改地址',
            goBack: true
        },
        bills: {
            text: '订单结算',
            goBack: true
        },
        orderList: {
            text: '我的订单',
            goBack: true
        },
        orderDetail: {
            text: '订单详情',
            goBack: true
        },
        login: {
            text: '登录',
            goBack: true
        },
        gopay: {
            text: '微信支付',
            goBack: true
        }
    }
};
