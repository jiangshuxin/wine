export function mallFilterMap(state) {
    return state.filterMap;
}

export function mallFilterSelectedInfo(state) {
    return state.filterSelectedInfo;
}

export function mallMdseList(state) {
    return state.mdseList;
}

export function mallMdsePageInfo(state) {
    const {pageNumber, totalPage} = state.basicInfo;
    return {
        pageNumber,
        totalPage
    };
}
