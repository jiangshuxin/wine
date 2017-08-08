// 我的
export function userLayout(state) {
    return state.layout;
}

export function userInfo(state) {
    return state.userInfo;
}

export function userIsLogin(state) {
    return state.isLogin;
}

// 我的地址
export function addressList(state) {
    return state.addressList.list;
}

export function addressPageInfo(state) {
    const { pageSize, pageNumber, totalPage } = state.addressList;
    return {
        pageSize,
        pageNumber,
        totalPage
    };
}

export function addressDetailLayout(state) {
    return state.addressDetailLayout;
}

export function addressDetailInfo(state) {
    return state.addressDetail;
}

export function addressNowSelected(state) {
    const id = state.userInfo.nowAddress;
    const list = state.addressList.list;
    return list.length && list.filter(item => item.addressId === id)[0];
}

// 结算
export function billsMdseList(state) {
    return state.billsMdseList;
}

export function payment(state) {
    return state.payment;
}

export function distribution(state) {
    return state.distribution;
}

export function invoice(state) {
    return state.invoice;
}

// 订单
export function orderList(state) {
    return state.orderList.list;
}

export function orderTabStatus(state) {
    return state.orderList.status;
}

export function orderDetail(state) {
    return state.orderDetail;
}

export function orderListPageInfo(state) {
    const {pageNumber, totalPage} = state.orderList;
    return {pageNumber, totalPage};
}

export function orderTab(state) {
    return state.orderTab;
}

export function payPic(state) {
    return state.payInfo.payPic;
}
