export const layout = [{
    id: 'personal',
    text: '个人中心',
    routeName: 'personal'
}, {
    id: 'order',
    text: '我的订单',
    routeName: 'order'
}, {
    id: 'address',
    text: '我的地址',
    routeName: 'address'
}, {
    id: 'referrer',
    text: '推荐人'
}];

export const addressDetailLayout = [{
    id: 'receiver',
    label: '姓名',
    type: 'text',
    placeholder: '请输入收货人姓名',
    state: '',
    rule: [{
        value: /^.*(\S+).*$/
    }]
}, {
    id: 'phone',
    label: '手机号',
    type: 'number',
    placeholder: '请输入收货人手机号',
    state: '',
    rule: [{
        value: /^.*(\S+).*$/
    }]
}, {
    id: 'province',
    label: '省市区',
    type: 'text',
    placeholder: '请输入收货所属省市区',
    state: '',
    rule: [{
        value: /^.*(\S+).*$/
    }]
}, {
    id: 'address',
    label: '详细地址',
    type: 'textarea',
    placeholder: '请输入收货详细地址',
    rows: '4',
    state: '',
    rule: [{
        value: /^.*(\S+).*$/
    }]
}, {
    id: 'isDefault',
    label: '设为默认地址'
}];

export const orderTab = [{
    id: 0,
    text: '已取消'
}, {
    id: 1,
    text: '未支付'
}, {
    id: 2,
    text: '已支付'
}, {
    id: 3,
    text: '已完成'
}];
