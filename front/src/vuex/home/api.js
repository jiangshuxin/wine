import request from 'common/request';

export async function getMerchant(id) {
    return await request.get({
        url: `/wine/api/front/getMerchantInfo/${id}`
    });
}
