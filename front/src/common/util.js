// 监测是否是pc端
export function checkAgent() {
    const userAgent = navigator.userAgent;
    const devices = ['Android', 'iPhone', 'SymbianOS', 'Windows Phone', 'iPad', 'iPod'];
    return devices.every(item => userAgent.indexOf(item) < 0);
}
