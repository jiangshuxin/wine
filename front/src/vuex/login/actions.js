import {
    getVerifyCode,
    login,
    checkUserName
} from './api';

export async function getLoginVerifyCode({state, commit}) {
    const result = await getVerifyCode({
        phone: state.options.phone,
        // 1登录 2修改密码
        type: 1
    });
    commit('SET_LOGIN_FORM', {
        id: 'realVerifyCode',
        value: result.data.verifyCode
    });
}

export async function loginWine({state, commit}) {
    const { phone, password, type, verifyCode, parentId} = state.options;
    const result = await login({
        userName: phone,
        password: type === 2 ? verifyCode : password,
        type,
        parentId
    });
    if (!result.success) {
        commit('CHANGE_LOGIN_FORM_STATE', {id: 'password', status: 'error'});
        throw new Error(result.errorMsg);
    }
    commit('SET_USER_INFO', result);
}

export async function checkLoginPhone({commit}, phone) {
    const result = await checkUserName(phone);
    if (result.data.isExist) {
        commit('HIDE_LOGIN_LAYOUT', 'parentId');
    } else {
        commit('SHOW_LOGIN_LAYOUT', 'parentId');
    }
}
