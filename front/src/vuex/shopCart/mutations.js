import {
    isValidStorage,
    setStorageItem
} from 'common/util';

function setStorage(list) {
    if (!isValidStorage()) {
        return;
    }
    setStorageItem('shopCart', list.map(item => {
        return {
            id: item.mdse.mdseId,
            count: item.count,
            checked: item.checked
        };
    }));
}

export function SET_SHOP_CART_INFO(state, info) {
    const index = state.list.findIndex(item => item.mdse.mdseId === info.mdse.mdseId);
    if (index >= 0) {
        state.list = state.list.map(item => {
            if (item.mdse.mdseId === info.mdse.mdseId) {
                return Object.assign({}, item, {count: +item.count + +info.count});
            }
            return item;
        });
    } else {
        state.list.push(info);
    }
    setStorage(state.list);
}

export function CHANGE_SHOP_CART_INFO(state, info) {
    const id = info.mdse.mdseId;
    state.list = state.list.map(item => {
        if (item.mdse.mdseId === id) {
            return info;
        }
        return item;
    });
    setStorage(state.list);
}

export function DEL_SHOP_CART_INFO(state, info) {
    const id = info.mdse.mdseId;
    state.list = state.list.filter(item => item.mdse.mdseId !== id);
    setStorage(state.list);
}

export function CHANGE_SHOP_CART_CHECK_ALL(state, isCheck) {
    state.list.forEach(item => {
        item.checked = isCheck;
    });
}

export function INIT_SHOP_CART_INFO(state, info) {
    state.list = info;
    // 覆盖storage
    // 因为有可能之前storage中商品存在非法，或者过期，或不可用的id
    setStorage(info);
}

