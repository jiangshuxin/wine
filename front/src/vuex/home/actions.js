import {
    getMerchant
} from './api';

export async function getMerchantInfo({commit}, merchantId) {
    const result = await getMerchant(merchantId);
    commit('SET_MERCHANT_INFO', result.data);
}
