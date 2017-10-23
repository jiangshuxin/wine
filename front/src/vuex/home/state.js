const merchantInfo = {
    merchantId: '',
    // 名称
    name: '',
    // 英文名称
    nameEn: '',
    // 庄主
    master: '',
    // 注册时间
    createYear: '',
    // 种植面积
    acreage: '',
    // 葡萄种植种类
    grapeType: '',
    // 平均树龄
    treeAge: '',
    // 酿酒师及介绍, 长篇文字
    wineMaker: '',
    // 年产量
    output: '',
    // 酒庄级别和级别介绍, 长篇文字
    level: '',
    // 酒庄文字介绍, 长篇文字
    description: '',
    // 实时土壤和气候信息
    soilAndClimate: '',
    // 酒庄照片, 最多4张
    chateauPics: [],
    // 种植地照片, 4
    landPics: [],
    // 部分证照, 4
    certPics: [],
    // 得奖照片, 4
    prizePics: [],
    // 视频
    videoLink: '',
    tourismLink: ''
};

const nodeMap = {
    name: {
        id: 'name',
        label: '酒庄',
        type: 'text'
    },
    nameEn: {
        id: 'nameEn',
        label: '英文名',
        type: 'text'
    },
    master: {
        id: 'master',
        label: '庄主',
        type: 'text'
    },
    createYear: {
        id: 'createYear',
        label: '注册时间',
        type: 'text'
    },
    acreage: {
        id: 'acreage',
        label: '种植面积',
        type: 'text'
    },
    grapeType: {
        id: 'grapeType',
        label: '葡萄种植种类',
        type: 'text'
    },
    treeAge: {
        id: 'treeAge',
        label: '平均树龄',
        type: 'text'
    },
    output: {
        id: 'output',
        label: '年产量',
        type: 'text'
    },
    soilAndClimate: {
        id: 'soilAndClimate',
        label: '实时土壤和气候',
        type: 'text'
    },
    wineMaker: {
        id: 'wineMaker',
        label: '酿酒师介绍',
        type: 'btn-group-text'
    },
    level: {
        id: 'level',
        label: '酒庄级别介绍',
        type: 'btn-group-text'
    },
    description: {
        id: 'description',
        label: '酒庄介绍',
        type: 'btn-group-text'
    },
    chateauPics: {
        id: 'chateauPics',
        label: '酒庄照片',
        type: 'btn-group-img'
    },
    landPics: {
        id: 'landPics',
        label: '种植地照片',
        type: 'btn-group-img'
    },
    certPics: {
        id: 'certPics',
        label: '证书照片',
        type: 'btn-group-img'
    },
    prizePics: {
        id: 'prizePics',
        label: '获奖照片',
        type: 'btn-group-img'
    }
};

const layout = {
    title: ['name', 'nameEn'].map(id => nodeMap[id]),
    info: {
        title: '酒庄信息',
        node: [
            'master',
            'createYear',
            'acreage',
            'grapeType',
            'treeAge',
            'output',
            'soilAndClimate'
        ].map(id => nodeMap[id])
    },
    introduce: {
        title: '酒庄介绍',
        node: [
            'wineMaker',
            'level',
            'description',
            'chateauPics',
            'landPics'
        ].map(id => nodeMap[id])
    },
    certificate: {
        title: '荣誉证书',
        node: [
            'certPics',
            'prizePics'
        ].map(id => nodeMap[id])
    }
};

export default {
    merchantInfo,
    layout,
    nodeMap
};
