import 'isomorphic-fetch';
import {obj2Params} from 'common/util';
import errorEventBus from 'common/error';

const reqHeaders = {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'x-requested-with': 'fetch'
};

function request(method, {url, headers, data = {}}) {
    if (method === 'get' && data) {
        url += `?t=${+new Date()}&${obj2Params(data)}`;
    }
    return new Promise((resolve, reject) => {
        const params = {
            method: method,
            headers: new Headers(Object.assign({}, reqHeaders, headers)),
            credentials: 'include'
        };
        if (method !== 'get') {
            params.body = JSON.stringify(data);
        }
        // TODO处理一下空数据
        fetch(url, params).then(response => {
            if (response.status !== 200) {
                errorEventBus.$emit('requestError', response);
                reject(response);
            }
            return response.json();
        }).then(data => {
            /*
             *if (!data.success) {
             *    errorEventBus.$emit('requestError', data);
             *    reject(data);
             *    return;
             *}
             */
            resolve(data);
        }).catch(error => {
            errorEventBus.$emit('requestError', error);
            reject(error);
        });
    });
}

export default ['get', 'post', 'put', 'delete'].reduce((api, method) => {
    api[method] = request.bind(null, method);
    return api;
}, {});
