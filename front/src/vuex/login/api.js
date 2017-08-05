import request from 'common/request';

export async function getVerifyCode({phone, type}) {
    return await request.get({
        url: `/wine/api/front/sendVerifyCode/${phone}/${type}`
    });
}

export async function login(data) {
    return await request.post({
        url: '/wine/api/front/login',
        data
    });
}
