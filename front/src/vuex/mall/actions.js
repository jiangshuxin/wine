import {
    getList
} from './api';

function generatorParams(state) {
    const { pageNumber, pageSize } = state.basicInfo;
    return {
        pageNumber,
        pageSize,
        ...state.filterSelectedInfo.reduce((obj, item) => {
            obj[item.id] = item.value;
            return obj;
        }, {})
    };
}

export async function getMallMdseList({state, commit}, route) {
    const params = Object.assign({}, generatorParams(state), {merchantId: route.query.merchantId});
    const result = await getList(params);
    commit('SET_MALL_MDSE_LIST', result.data);
    commit('SET_MALL_TOTAL_PAGE', Math.ceil(result.totalCount / state.basicInfo.pageSize));
}

export async function getMallMdseListInit({state, commit}, route) {
    const params = Object.assign({}, generatorParams(state), {merchantId: route.query.merchantId});
    const result = await getList(params);
    commit('INIT_MALL_MDSE_LIST');
    commit('SET_MALL_MDSE_LIST', result.data);
    commit('SET_MALL_TOTAL_PAGE', Math.ceil(result.totalCount / state.basicInfo.pageSize));
}
