export function CHANGE_MALL_FILTER_SELECTED(state, info) {
    state.filterSelectedInfo = state.filterSelectedInfo.map(item => {
        if (item.id === info.id) {
            return Object.assign({}, item, {...info});
        }
        return item;
    });
}

export function SET_MALL_MDSE_LIST(state, list) {
    state.mdseList = state.mdseList.concat(list);
}

export function SET_MALL_MDSE_PAGE_INFO(state, pageNumber) {
    state.basicInfo.pageNumber = pageNumber;
}


export function SET_MALL_TOTAL_PAGE(state, totalPage) {
    state.basicInfo.totalPage = totalPage;
}

export function INIT_MALL_FILTER_INFO(state) {
    state.filterSelectedInfo = state.generatorFilterInfo();
}

export function INIT_MALL_PAGE_INFO(state) {
    state.basicInfo = state.generatorBasicInfo();
}

export function INIT_MALL_MDSE_LIST(state) {
    state.mdseList = [];
}

export function INIT_MALL_INFO(state) {
    INIT_MALL_PAGE_INFO(state);
    INIT_MALL_FILTER_INFO(state);
    INIT_MALL_MDSE_LIST(state);
}
