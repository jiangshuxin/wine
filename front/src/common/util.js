// 监测是否是pc端
export function checkAgent() {
    const userAgent = navigator.userAgent;
    const devices = ['Android', 'iPhone', 'SymbianOS', 'Windows Phone', 'iPad', 'iPod'];
    return devices.every(item => userAgent.indexOf(item) < 0);
}

export function isValidStorage() {
    return !!window.localStorage;
}

export function setStorageItem(key, value) {
    return window.localStorage.setItem(key, JSON.stringify(value));
}

export function getStorageItem(key) {
    return JSON.parse(window.localStorage.getItem(key));
}

export function removeStorageItem(key) {
    return window.localStorage.removeItem(key);
}

// query request
class URLSearchParams {
    constructor() {
        this.params = [];
    }
    append(key, value) {
        this.params.push(`${key}=${value}`);
    }
    toString() {
        return this.params.join('&');
    }
}

export function obj2Params(obj) {
    if (typeof obj !== 'object') {
        return null;
    }
    Object.keys(obj).forEach(key => {
        if (
            typeof obj[key] === 'string' && !obj[key]
            || typeof obj[key] === 'undefined'
            || obj[key] === null
        ) {
            delete obj[key];
        }
    });
    const searchParams = new URLSearchParams();
    Object.keys(obj).forEach(key => {
        if (typeof obj[key] === 'object') {
            searchParams.append(key, JSON.stringify(obj[key]));
        } else {
            searchParams.append(key, obj[key]);
        }
    });
    return searchParams;
}

// @params price 单位为元
// @return price 单位为元
export function formatPrice(price) {
    const num = '' + price;
    const index = num.indexOf('.');
    if (index < 0) {
        return num + '.00';
    }
    const before = num.slice(0, index);
    const after = num.slice(index + 1);
    return before + '.' + (after.length >= 2 ? after.slice(0, 2) : after + '0');
}


export function phoneNumberShelter(num) {
    const strNum = '' + num;
    const shelter = strNum.substr(3, 4);
    return strNum.replace(shelter, '****');
}

