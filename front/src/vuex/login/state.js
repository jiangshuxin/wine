const layoutMap = {
    phone: {
        id: 'phone',
        type: 'number',
        placeholder: '手机号码',
        state: '',
        rule: [{
            value: /^1[0-9]{10}$/
        }]
    },
    verifyCode: {
        id: 'verifyCode',
        type: 'number',
        placeholder: '验证码',
        state: '',
        rule: [{
            value: /^[0-9]{4}$/
        }],
        dep: 'realVerifyCode'
    },
    password: {
        id: 'password',
        type: 'text',
        placeholder: '密码',
        state: '',
        rule: [{
            value: /^\S+$/
        }]
    },
    parentId: {
        id: 'parentId',
        type: 'text',
        placeholder: '推荐码(选填)'
    }
};

const tabs = [{
    id: 2,
    text: '验证码登录'
}, {
    id: 1,
    text: '密码登录'
}];

const layout = {
    2: [
        layoutMap.phone,
        layoutMap.verifyCode,
        layoutMap.parentId
    ],
    1: [
        layoutMap.phone,
        layoutMap.password,
        layoutMap.parentId
    ]
};

function generatorOptions() {
    return {
        phone: '',
        verifyCode: '',
        realVerifyCode: '',
        password: '',
        parentId: '',
        type: 2,
        delay: 0
    };
}

export default {
    layout,
    tabs,
    options: generatorOptions(),
    generatorOptions
};
