function num2Array(start, end, step) {
    return new Array((+end - +start) / step + 1)
        .fill(+start)
        .map((item, index) => index ? item + step * index : item);
}

function addAfterWord(val, word) {
    return `${val} ${word}`;
}

export const catagory = [{
    value: '',
    text: '全部'
}, {
    value: 1,
    text: '红葡萄酒'
}, {
    value: 2,
    text: '白葡萄酒'
}, {
    value: 3,
    text: '起泡酒'
}, {
    value: 4,
    text: '冰酒'
}, {
    value: 5,
    text: '桃红葡萄酒'
}];

export const year = [{
    value: '',
    text: '全部'
}, ...num2Array(2011, 2016, 1).map(item => {
    return {value: item, text: addAfterWord(item, '年')};
})];

export const price = [{
    value: '',
    text: '全部'
}, ...num2Array(100, 900, 100).map(item => {
    return {value: item, text: addAfterWord(item, '元')};
})];
