function generatorLayoutMap() {
    return {
        phone: {
            id: 'phone',
            type: 'number',
            placeholder: '手机号码',
            state: '',
            rule: [{
                value: /^1[0-9]{10}$/
            }],
            isShow: true
        },
        verifyCode: {
            id: 'verifyCode',
            type: 'number',
            placeholder: '验证码',
            state: '',
            rule: [{
                value: /^[0-9]{4}$/
            }],
            dep: 'realVerifyCode',
            isShow: true
        },
        password: {
            id: 'password',
            type: 'password',
            placeholder: '密码',
            state: '',
            rule: [{
                value: /^\S+$/
            }],
            isShow: true
        },
        parentId: {
            id: 'parentId',
            type: 'text',
            placeholder: '推荐码(选填)',
            isShow: true
        }
    };
}

const layoutMap = generatorLayoutMap();

const tabs = [{
    id: 2,
    text: '验证码登录'
}, {
    id: 1,
    text: '密码登录'
}];

const layout = {
    2: ['phone', 'verifyCode', 'parentId'],
    1: ['phone', 'password']
};

function generatorOptions() {
    return {
        phone: '',
        verifyCode: '',
        realVerifyCode: '',
        password: '',
        parentId: '',
        type: 2
    };
}

export default {
    layout,
    layoutMap,
    tabs,
    options: generatorOptions(),
    generatorOptions,
    generatorLayoutMap,
    delay: 0
};
