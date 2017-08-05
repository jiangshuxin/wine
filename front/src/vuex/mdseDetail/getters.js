export function mdseDetailInfo(state) {
    return state.basicInfo;
}

export function mdseLayoutPolymer(state) {
    return state.propertyLayout.map(item => {
        return {
            ...item,
            value: state.basicInfo[item.id]
        };
    });
}
