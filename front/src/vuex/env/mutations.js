export function CHANGE_ENV_SELECTED_TAB(state, id) {
    state.selectedTab = id;
}

export function SET_ENV_MERCHANT_ID(state, id) {
    state.merchantId = id;
}

export function SET_ENV_AGENT(state, bool) {
    state.isPC = bool;
}

export function CHANGE_ENV_HINT_INFO(state, msg) {
    state.hintMsg = msg;
}

