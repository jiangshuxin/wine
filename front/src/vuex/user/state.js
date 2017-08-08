import {
    layout,
    addressDetailLayout,
    orderTab
} from './config';

// 用户信息
const userInfo = {
    userId: -1,
    userName: '',
    // 1销售 2普通用户
    type: '',
    balance: '',
    realName: '',
    gender: '',
    birthday: '',
    nowAddress: null
};

// TODO 为了省事和user共用一个model
// 收获地址列表
function generatorAddressList() {
    return {
        list: [],
        selected: -1,
        pageNumber: 1,
        pageSize: 100
    };
}

// 收获地址详情 新建 修改
function generatorAddressDetail() {
    return {
        addressId: -1,
        isDefault: 0,
        receiver: '',
        phone: '',
        province: '',
        address: ''
    };
}

const payment = {
    selected: 1,
    list: [{
        id: 1,
        text: '微信支付',
        className: ['iconfont', 'icon-wechat', 'wechat']
    }]
};

const distribution = {
    selected: 1,
    list: [{
        id: 1,
        text: '顺丰'
    }]
};

const invoice = {
    selected: 1,
    list: [{
        id: 1,
        text: '无'
    }, {
        id: 2,
        text: '个人发票'
    }, {
        id: 3,
        text: '公司发票',
        input: ''
    }]
};

function generatorOrderList() {
    return {
        list: [],
        // 1未支付, 2已支付
        status: 1,
        pageNumber: 1,
        pageSize: 10,
        totalPage: -1
    };
}

function generatorOrderDetail() {
    return {
        orderId: -1,
        orderTime: '',
        // 0:已取消，1:未支付，2:已支付，3:已完成
        status: '',
        amount: -1,
        mdseCount: '',
        mdseInfos: '',
        receiver: '',
        phone: -1,
        province: '',
        address: '',
        comment: '',
        logisticsCompany: '',
        logisticsSeqs: '',
        invoiceInfo: ''
    };
}

function generatorPayInfo() {
    return {
        appId: '',
        timeStamp: '',
        nonceStr: '',
        package: '',
        signType: '',
        paySign: '',
        payPic: ''
    };
}

export default {
    isLogin: false,
    userInfo,
    layout,
    addressDetailLayout,
    addressList: generatorAddressList(),
    addressDetail: generatorAddressDetail(),
    billsMdseList: [],
    payment,
    distribution,
    invoice,
    orderList: generatorOrderList(),
    orderDetail: generatorOrderDetail(),
    orderTab,
    payInfo: generatorPayInfo(),
    generatorAddressDetail,
    generatorAddressList,
    generatorOrderList,
    generatorOrderDetail,
    generatorPayInfo
};
