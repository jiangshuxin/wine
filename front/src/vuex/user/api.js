import request from 'common/request';
export async function getUser(id) {
    return await request.get({
        url: `/wine/api/front/getUserInfo/${id}`
    });
}

export async function getAddress(data) {
    return await request.post({
        url: '/wine/api/front/getUserAddresses',
        data
    });
}

export async function modifyUserAddress(data) {
    return await request.post({
        url: '/wine/api/front/modifyUserAddress',
        data
    });
}

export async function getOrders(data) {
    return await request.post({
        url: '/wine/api/front/getOrders',
        data
    });
}

export async function getOrder(id) {
    return await request.get({
        url: `/wine/api/front/getOrderDetail/${id}`
    });
}

export async function cancel(id) {
    return await request.get({
        url: `/wine/api/front/cancelOrder/${id}`
    });
}
