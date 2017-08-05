import {
    getUser,
    getAddress,
    modifyUserAddress,
    getOrders,
    getOrder,
    cancel
} from './api';

import { getList } from '../mall/api';

// 我的
export async function getUserInfo({state, commit}, id) {
    const userId = id || state.userInfo.userId;
    const result = await getUser(userId);
    commit('SET_USER_INFO', result);
}

// 我的地址
async function generatorAddressListParams(state) {
    const { pageNumber, pageSize } = state.addressList;
    return await getAddress({
        userId: state.userInfo.userId,
        pageNumber,
        pageSize
    });
}

export async function getAddressListInfo({state, commit}) {
    const result = await generatorAddressListParams(state);
    commit('SET_ADDRESS_LIST', result.data);
}

export async function getAddressDetailInfo({state, commit, dispatch}, id) {
    let address = state.addressList.list.filter(item => +item.addressId === +id)[0];
    if (!address) {
        await dispatch('getAddressListInfo');
    }
    commit('SET_ADDRESS_DETAIL_INFO', id);
    commit('CHECK_ADDRESS_DETAIL');
}

export async function deleteAddress({state}, id) {
    console.log(state);
    console.log(id);
}


export async function saveAddressDetail({state, commit}, isModify) {
    commit('CHECK_ADDRESS_DETAIL');
    const layout = state.addressDetailLayout;
    if (!layout.every(item => item.state ? item.state === 'success' : true)) {
        return;
    }
    const {addressId, isDefault, receiver, phone, province, address} = state.addressDetail;
    const userId = state.userInfo.userId;
    if (!isModify) {
        await modifyUserAddress({userId, isDefault, receiver, phone, province, address});
    } else {
        await modifyUserAddress({userId, addressId, isDefault, receiver, phone, province, address});
    }
}

// 结算
export async function getBillsMdseList({rootState, commit}, mdseIds) {
    const { merchantId } = rootState.env;
    const result = await getList({
        pageNumber: 1,
        pageSize: 100,
        mdseIds: mdseIds.join(','),
        merchantId
    });
    commit('SET_BILLS_MDSE_LIST', result.data);
}

// 订单
export async function getOrderList({state, commit}) {
    const userId = state.userInfo.userId;
    const { status, pageNumber, pageSize } = state.orderList;
    let result = await getOrders({
        userId,
        status,
        pageNumber,
        pageSize
    });
    result = {
        pageNumber: pageNumber,
        pageSize: 10,
        totalCount: 100,
        data: [{
            orderId: 123123,
            orderTime: '2017-8-1 15:42:28',
            status: 1,
            amount: 50000,
            mdseCount: 12,
            mdseInfos: [{
                name: '测试酒1',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2000,
                count: 2
            }, {
                name: '测试酒2',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 6000,
                count: 6
            }, {
                name: '测试酒3',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2500,
                count: 4
            }]
        }, {
            orderId: 123123,
            orderTime: '2017-8-1 15:42:28',
            status: 2,
            amount: 50000,
            mdseCount: 12,
            mdseInfos: [{
                name: '测试酒1',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2000,
                count: 2
            }, {
                name: '测试酒2',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 6000,
                count: 6
            }, {
                name: '测试酒3',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2500,
                count: 4
            }, {
                name: '测试酒1',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2000,
                count: 2
            }, {
                name: '测试酒2',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 6000,
                count: 6
            }, {
                name: '测试酒3',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2500,
                count: 4
            }, {
                name: '测试酒1',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2000,
                count: 2
            }, {
                name: '测试酒2',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 6000,
                count: 6
            }, {
                name: '测试酒3',
                pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
                price: 2500,
                count: 4
            }]
        }]
    };
    commit('SET_ORDER_LIST', result.data);
    commit('SET_ORDER_LIST_TOTAL_PAGE', Math.ceil(result.totalCount / pageSize));
}

export async function getOrderDetail({commit}, orderId) {
    let result = await getOrder(orderId);
    result.data = {
        orderId: 123123,
        orderTime: '2017-8-1 15:42:28',
        status: 1,
        amount: 50000,
        mdseCount: 12,
        mdseInfos: [{
            name: '测试酒1',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 2000,
            count: 2
        }, {
            name: '测试酒2',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 6000,
            count: 6
        }, {
            name: '测试酒3',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 2500,
            count: 4
        }, {
            name: '测试酒1',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 2000,
            count: 2
        }, {
            name: '测试酒2',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 6000,
            count: 6
        }, {
            name: '测试酒3',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 2500,
            count: 4
        }, {
            name: '测试酒1',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 2000,
            count: 2
        }, {
            name: '测试酒2',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 6000,
            count: 6
        }, {
            name: '测试酒3',
            pic: 'http://123.57.234.184/wineStatic/images/M0001/mdse/10000_small.png',
            price: 2500,
            count: 4
        }],
        receiver: '王子健',
        phone: 18810032068,
        province: '北京市海淀区',
        address: '丹棱街6号，中国电子大厦B座11层',
        comment: '',
        logisticsCompany: '顺丰',
        logisticsSeqs: 'M123098DE1238',
        invoiceInfo: '无'
    };
    commit('SET_ORDER_DETAIL', result.data);
}

/* eslint-disable */
export async function cancelOrder({state}, id) {
    await cancel(id);
}
/* eslint-enable */
