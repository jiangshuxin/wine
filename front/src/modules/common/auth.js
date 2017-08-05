import store from 'vuex/store';
import { Indicator } from 'mint-ui';
import {
    getStorageItem
} from 'common/util';


export async function getUserAuth() {
    if (store.getters.userIsLogin) {
        return true;
    }
    const userInfo = store.getters.userInfo;
    const userId = userInfo.userId === -1 ? getStorageItem('wineUserId') : userInfo.userId;
    if (!userId) {
        return false;
    }
    Indicator.open();
    try {
        await store.dispatch('getUserInfo', userId);
    } catch (e) {
        console.warn(e);
    } finally {
        Indicator.close();
    }
    if (!store.getters.userIsLogin) {
        return false;
    }
    return true;
}
