import {
    getDetail
} from './api';
export async function getMdseDetailInfo({state, commit}) {
    const result = await getDetail(state.mdseId);
    const bigPic = new Array(10).fill(0)
        .map((item, index) => {
            return result.data[`bigPic${index + 1}`];
        })
        .filter(item => item);
    result.data.bigPic = bigPic;
    commit('SET_MDSE_DETAIL_BASIC_INFO', result.data);
}
