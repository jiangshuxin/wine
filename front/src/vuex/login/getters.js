export function loginLayout(state) {
    const map = state.layoutMap;
    return state.layout[state.options.type].map(id => map[id]);
}

export function loginTabs(state) {
    return state.tabs;
}

export function loginOptions(state) {
    return state.options;
}

export function loginVerifyDelay(state) {
    return state.delay;
}
