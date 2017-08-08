import {
    setStorageItem,
    removeStorageItem
} from 'common/util';

export function SET_USER_INFO(state, info) {
    const data = info.data;
    const userInfo = state.userInfo;
    setStorageItem('wineUserId', data.userId);
    userInfo.balance = data.balance;
    userInfo.birthday = data.birthday;
    userInfo.gender = data.gender;
    userInfo.userId = data.userId;
    userInfo.userName = data.userName;
    userInfo.realName = data.realName;
    userInfo.type = data.type;
    state.isLogin = true;
}

export function USER_LOGOUT(state) {
    const userInfo = state.userInfo;
    userInfo.balance = '';
    userInfo.birthday = '';
    userInfo.gender = '';
    userInfo.userId = -1;
    userInfo.userName = '';
    userInfo.realName = '';
    userInfo.type = '';
    state.isLogin = false;
    removeStorageItem('wineUserId');
}

export function SET_ADDRESS_LIST(state, list) {
    state.addressList.list = list;
    const address = list.filter(item => item.isDefault)[0];
    state.userInfo.nowAddress = address ? address.addressId : null;
}

export function SET_ADDRESS_FORM_INFO(state, {id, value}) {
    state.addressDetail[id] = id !== 'isDefault' ? value : Number(value);
}

export function SET_ADDRESS_DETAIL_INFO(state, id) {
    state.addressDetail = state.addressList.list.filter(item => +item.addressId === +id)[0];
}

export function INIT_ADDRESS_DETAIL_INFO(state) {
    state.addressDetail = state.generatorAddressDetail();
}

export function INIT_ADDRESS_INFO(state) {
    state.addressList = state.generatorAddressList();
}

export function CHECK_ADDRESS_DETAIL(state, id) {
    const layout = state.addressDetailLayout;
    const data = state.addressDetail;
    state.addressDetailLayout = layout.map(item => {
        if (id && item.id !== id) {
            return item;
        }
        if (item.rule && item.rule.length) {
            const isValid = item.rule.every(ru => ru.value.test(data[item.id]));
            if (isValid) {
                return Object.assign({}, item, {state: 'success'});
            }
            return Object.assign({}, item, {state: 'error'});
        }
        return Object.assign({}, item, {state: 'success'});
    });
}

export function SET_BILLS_MDSE_LIST(state, list) {
    state.billsMdseList = list;
}

export function SET_NOW_ADDRESS(state, address) {
    state.userInfo.nowAddress = address.addressId;
}

export function SET_PAYMENT_SELECTED(state, id) {
    state.payment.selected = id;
}

export function SET_DISTRIBUTION_SELECTED(state, id) {
    state.distribution.selected = id;
}

export function SET_INVOICE_SELECTED(state, selected) {
    const { id, input } = selected;
    state.invoice.selected = id;
    if (id === 3) {
        state.invoice.list = state.invoice.list.map(item => {
            if (item.id === id) {
                item.input = input;
            }
            return item;
        });
    }
}

// 订单列表
export function SET_ORDER_LIST(state, list) {
    state.orderList.list = state.orderList.list.concat(list);
}

export function SET_ORDER_LIST_TOTAL_PAGE(state, total) {
    state.orderList.totalPage = total;
}

export function CHANGE_ORDER_TAB(state, id) {
    state.orderList.status = id;
}

export function SET_ORDER_PAGE_INFO(state, number) {
    state.orderList.pageNumber = number;
}

export function INIT_ORDER_PAGE_INFO(state) {
    state.orderList.pageNumber = 1;
    state.orderList.totalPage = -1;
}

export function INIT_ORDER_LIST_INFO(state) {
    state.orderList = state.generatorOrderList();
}

export function INIT_ORDER_LIST(state) {
    state.orderList.list = [];
}

// 订单详情
export function SET_ORDER_DETAIL(state, data) {
    state.orderDetail = data;
}

export function INIT_ORDER_DETAIL(state) {
    state.orderDetail = state.generatorOrderDetail();
}

// 支付信息
export function SET_PAY_INFO(state, data) {
    state.payInfo = data;
}

export function INIT_PAY_INFO(state) {
    state.payInfo = state.generatorPayInfo();
}
