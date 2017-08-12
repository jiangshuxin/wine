import {
    getUser,
    getAddress,
    modifyUserAddress,
    deleteUserAddress,
    getOrders,
    getOrder,
    cancel,
    createOrder,
    pay
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

export async function deleteAddress({}, id) {
    await deleteUserAddress(id);
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

export async function createSingleOrder({rootState, state, commit}, countInfo) {
    const { userId, nowAddress } = state.userInfo;
    const { merchantId } = rootState.env;
    const mdseInfo = state.billsMdseList
        .reduce((arr, obj) => {
            arr.push(`${obj.mdseId}:${countInfo[obj.mdseId]}`);
            return arr;
        }, [])
        .join(',');
    const nowInvoiceId = state.invoice.selected;
    const invoice = state.invoice.list.filter(item => item.id === nowInvoiceId)[0];
    const invoiceInfo = nowInvoiceId === 3 ? invoice.text + ',' + invoice.input : invoice.text;
    const result = await createOrder({
        userId,
        merchantId,
        mdseInfo,
        invoiceInfo,
        addressId: nowAddress
    });
    commit('SET_ORDER_DETAIL', result.data);
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
    commit('SET_ORDER_LIST', result.data);
    commit('SET_ORDER_LIST_TOTAL_PAGE', Math.ceil(result.totalCount / pageSize));
}

export async function getOrderDetail({commit}, orderId) {
    let result = await getOrder(orderId);
    commit('SET_ORDER_DETAIL', result.data);
}

/* eslint-disable */
export async function cancelOrder({state}, id) {
    await cancel(id);
}
/* eslint-enable */

export async function goPay({commit}, {orderId, payType}) {
    const result = await pay(orderId, payType);
    commit('SET_PAY_INFO', result.data);
    /* eslint-disable */
/*
 *    function onBridgeReady() {
 *        WeixinJSBridge.invoke('getBrandWCPayRequest',
 *            {
 *
 *                "appId": "wx79c1c7fa2255f655",
 *                "timeStamp": "1502093354466",
 *                "nonceStr": "1609135115",
 *                "package": "prepay_id=wx20170807160913539a22112d0766233032",
 *                "signType": "MD5",
 *                "paySign": "7738009E306331CAF2C70DDAF86B47BD"
 *            },
 *            function(res){
 *                console.log(res);
 *                if (res.err_msg === 'get_brand_wcpay_request：ok') {
 *                    alert('success');
 *                }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
 *                else{
 *                    alert(res.err_msg + '' + res.err_desc);
 *                }
 *            }
 *        );
 *    }
 *    function pay() {
 *        if (typeof WeixinJSBridge == 'undefined') {
 *            alert('not wx ...');
 *            if (document.addEventListener) {
 *                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
 *            } else if ( document.attachEvent) {
 *                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
 *                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
 *            }
 *        } else {
 *            alert('start pay....');
 *            console.log('start pay...');
 *            onBridgeReady();
 *        }
 *    }
 *    pay();
 */
    /* eslint-enable */
}
