import {
    catagory,
    year,
    price
} from './config';

function generatorFilterInfo() {
    return [{
        id: 'catagory',
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
}

function generatorBasicInfo() {
    return {
        totalPage: -1,
        pageNumber: 1,
        pageSize: 20
    };
}

export default {
    generatorBasicInfo,
    generatorFilterInfo,
    filterMap: {
        catagory,
        year,
        price
    },
    basicInfo: generatorBasicInfo(),
    filterSelectedInfo: generatorFilterInfo(),
    mdseList: []
};
