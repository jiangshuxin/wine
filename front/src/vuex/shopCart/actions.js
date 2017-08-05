import {
    isValidStorage,
    getStorageItem
} from 'common/util';

import { getList } from '../mall/api';

export async function initShopCartInfo({rootState, state, commit}) {
    if (state.list.length) {
        return;
    }
    if (!isValidStorage()) {
        return;
    }
    // 获取storage中的购物车信息
    const shopCartInfo = getStorageItem('shopCart');
    if (shopCartInfo && shopCartInfo.length) {
        const { merchantId } = rootState.env;
        // 根据id获取商品详情
        const result = await getList({
            pageNumber: 1,
            pageSize: 100,
            mdseIds: shopCartInfo.map(item => item.id).join(','),
            merchantId
        });
        const map = {};
        commit('INIT_SHOP_CART_INFO', result.data.length ? shopCartInfo
            // 过滤实际可以获取到详情的商品
            // 并id: mdse 存在map中
            .filter(item => {
                const mdse = result.data.filter(mdse => mdse.mdseId === item.id)[0];
                if (mdse) {
                    map[mdse.mdseId] = mdse;
                    return true;
                }
                return false;
            })
            // 聚合商品详情mdse  和 count checked 状态
            .map(item => {
                return {
                    mdse: map[item.id],
                    count: item.count,
                    checked: item.checked
                };
            }) : []
        );
    }
}

export function setShopCartInfo({state, commit}, info) {
    const index = state.list.findIndex(item => item.mdse.mdseId === info.mdse.mdseId);
    if (index >= 0) {
        return false;
    }
    commit('SET_SHOP_CART_INFO', Object.assign({}, info, {checked: true}));
    return true;
}
