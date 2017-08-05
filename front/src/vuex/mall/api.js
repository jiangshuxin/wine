import request from 'common/request';
export async function getList(data) {
    return await request.post({
        url: '/wine/api/front/getMdses',
        data
    });
}
