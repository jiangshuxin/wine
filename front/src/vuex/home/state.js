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
    agroType: '',
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

const merchantLayout = [{
    id: 'name',
    label: '酒庄',
    type: 'text'
}, {
    id: 'nameEn',
    label: '英文名',
    type: 'text'
}, {
    id: 'master',
    label: '庄主',
    type: 'text'
}, {
    id: 'createYear',
    label: '注册时间',
    type: 'text'
}, {
    id: 'acreage',
    label: '种植面积',
    type: 'text'
}, {
    id: 'agroType',
    label: '葡萄种植种类',
    type: 'text'
}, {
    id: 'treeAge',
    label: '平均树龄',
    type: 'text'
}, {
    id: 'output',
    label: '年产量',
    type: 'text'
}, {
    id: 'soilAndClimate',
    label: '实时土壤和气候',
    type: 'text'
}, {
    id: 'wineMaker',
    label: '酿酒师介绍',
    type: 'btn-group-text'
}, {
    id: 'level',
    label: '酒庄级别介绍',
    type: 'btn-group-text'
}, {
    id: 'description',
    label: '酒庄介绍',
    type: 'btn-group-text'
}, {
    id: 'chateauPics',
    label: '酒庄照片',
    type: 'btn-group-img'
}, {
    id: 'landPics',
    label: '种植地照片',
    type: 'btn-group-img'
}, {
    id: 'certPics',
    label: '证书照片',
    type: 'btn-group-img'
}, {
    id: 'prizePics',
    label: '获奖照片',
    type: 'btn-group-img'
}];

export default {
    merchantInfo,
    merchantLayout
};
