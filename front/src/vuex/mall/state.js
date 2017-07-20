const filterMap = {
    type: [{
        value: '',
        text: '全部'
    }, {
        value: '1',
        text: '红葡萄酒'
    }, {
        value: '2',
        text: '白葡萄酒'
    }],
    year: [{
        value: '',
        text: '全部'
    }, {
        value: '1',
        text: '10年'
    }, {
        value: '2',
        text: '20年'
    }],
    price: [{
        value: '',
        text: '全部'
    }, {
        value: '1',
        text: '100 - 200元'
    }, {
        value: '2',
        text: '200 - 300元'
    }]
};

const filterSelectedInfo = [{
    id: 'type',
    value: '',
    text: '类型'
}, {
    id: 'year',
    value: '',
    text: '年份'
}, {
    id: 'price',
    value: '',
    text: '价格'
}];

export default {
    filterMap,
    filterSelectedInfo
};
