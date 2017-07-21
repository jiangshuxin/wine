const filterMap = {
    type: [{
        value: '',
        text: '全部'
    }, {
        value: '1',
        text: '红葡萄酒'
    }, {
        value: '2',
        text: '白葡萄酒白葡萄酒'
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
        text: '100元'
    }, {
        value: '2',
        text: '200元'
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
    filterSelectedInfo,
    commodityList: [{
        id: '123',
        url: 'http://img5.imgtn.bdimg.com/it/u=1568156851,1614184660&fm=26&gp=0.jpg',
        name: '维德尊贵干红',
        year: 2004,
        description: 'Valdelosfrailes Prestigio 750ml',
        price: '580'
    }, {
        id: '123',
        url: 'http://img5.imgtn.bdimg.com/it/u=1568156851,1614184660&fm=26&gp=0.jpg',
        name: '维德尊贵干红',
        year: 2004,
        description: 'Valdelosfrailes Prestigio 750ml',
        price: '580'
    }, {
        id: '123',
        url: 'http://img5.imgtn.bdimg.com/it/u=1568156851,1614184660&fm=26&gp=0.jpg',
        name: '维德尊贵干红',
        year: 2004,
        description: 'Valdelosfrailes Prestigio 750ml',
        price: '580'
    }, {
        id: '123',
        url: 'http://img5.imgtn.bdimg.com/it/u=1568156851,1614184660&fm=26&gp=0.jpg',
        name: '维德尊贵干红',
        year: 2014,
        description: 'Valdelosfrailes Prestigio 750ml',
        price: '580'
    }, {
        id: '123',
        url: 'http://img5.imgtn.bdimg.com/it/u=1568156851,1614184660&fm=26&gp=0.jpg',
        name: '维德尊贵干红',
        year: 2014,
        description: 'Valdelosfrailes Prestigio 750ml',
        price: '580'
    }]
};
