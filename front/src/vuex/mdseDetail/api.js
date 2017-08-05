import request from 'common/request';

export async function getDetail(id) {
    return await request.get({
        url: `/wine/api/front/getMdseDetail/${id}`
    });
}
