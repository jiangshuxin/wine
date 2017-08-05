export function loginLayout(state) {
    return state.layout[state.options.type];
}

export function loginTabs(state) {
    return state.tabs;
}

export function loginOptions(state) {
    return state.options;
}

export function loginVerifyDelay(state) {
    return state.options.delay;
}
