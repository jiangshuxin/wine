export function shopCartList(state) {
    return state.list;
}

export function shopCartTotalCount(state) {
    return state.list.reduce((num, item) => {
        num += item.count;
        return num;
    }, 0);
}

export function shopCartIsCheckAll(state) {
    return state.list.every(item => item.checked);
}

export function shopCartTotalPrice(state) {
    return state.list
        .filter(item => item.checked)
        .reduce((num, item) => {
            num += item.mdse.price * item.count;
            return num;
        }, 0);
}
