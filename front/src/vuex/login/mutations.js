import {
    setStorageItem,
    getStorageItem
} from 'common/util';

export function SET_LOGIN_TYPE(state, type) {
    state.options = state.generatorOptions();
    state.options.type = type;
    state.layoutMap = state.generatorLayoutMap();
}

export function SET_LOGIN_FORM(state, {id, value}) {
    state.options[id] = value;
}

export function SET_LOGIN_DELAY(state, delay) {
    state.delay = delay;
    setStorageItem('wineVerifyDelay', delay);
}

export function CHANGE_LOGIN_FORM_STATE(state, {id, status}) {
    state.layoutMap[id].state = status;
}

export function INIT_LOGIN(state) {
    state.layoutMap = state.generatorLayoutMap();
    state.options = state.generatorOptions();
    const delay = +getStorageItem('wineVerifyDelay');
    state.delay = delay > 0 ? delay : 0;
}

export function CHECK_LOGIN_FORM(state, id) {
    const type = state.options.type;
    const options = state.options;
    const nowLayout = state.layout[type].map(id => state.layoutMap[id]);
    nowLayout.forEach(item => {
        if (id && item.id !== id) {
            return;
        }
        if (!item.rule || !item.isShow) {
            return;
        }
        if (item.rule && item.rule.every(ru => ru.value.test(options[item.id]))) {
            if (item.dep && options[item.dep] !== options[item.id]) {
                item.state = 'error';
                return;
            }
            item.state = 'success';
            return;
        }
        item.state = 'error';
        return;
    });
}

export function HIDE_LOGIN_LAYOUT(state, id) {
    state.tabs.forEach(tab => {
        state.layout[tab.id]
            .map(id => state.layoutMap[id])
            .forEach(item => {
                if (item.id === id) {
                    item.isShow = false;
                }
            });
    });
}

export function SHOW_LOGIN_LAYOUT(state, id) {
    state.tabs.forEach(tab => {
        state.layout[tab.id]
            .map(id => state.layoutMap[id])
            .forEach(item => {
                if (item.id === id) {
                    item.isShow = true;
                }
            });
    });
}
