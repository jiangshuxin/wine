export default {
    merchantId: '',
    selectedTab: 'home',
    // tab统一配置管理
    tabList: [{
        id: 'home',
        text: '首页',
        iconClass: 'icon-home-copy'
    }, {
        id: 'mall',
        text: '商城',
        iconClass: 'icon-gouwu'
    }, {
        id: 'shopCart',
        text: '购物车',
        iconClass: 'icon-gouwuche'
    }, {
        id: 'user',
        text: '我的',
        iconClass: 'icon-wode'
    }],
    // head信息统一配置管理
    headMap: {
        home: {
            text: '首页'
        },
        shopCart: {
            text: '购物车'
        },
        user: {
            text: '我的'
        }
    }
};
