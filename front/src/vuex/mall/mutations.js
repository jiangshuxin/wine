export function CHANGE_MALL_FILTER_SELECTED(state, info) {
    state.filterSelectedInfo = state.filterSelectedInfo.map(item => {
        if (item.id === info.id) {
            return Object.assign({}, item, {...info});
        }
        return item;
    });
}
