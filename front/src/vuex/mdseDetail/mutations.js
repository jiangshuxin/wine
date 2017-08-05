export function SET_MDSE_DETAIL_ID(state, id) {
    state.mdseId = id;
}

export function SET_MDSE_DETAIL_BASIC_INFO(state, info) {
    state.basicInfo = info;
}

export function INIT_MDSE_DETAIL_INFO(state) {
    state.basicInfo = state.generatorBasicInfo();
}
