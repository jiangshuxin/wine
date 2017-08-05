import {
    setStorageItem,
    getStorageItem
} from 'common/util';

export function SET_LOGIN_TYPE(state, type) {
    state.options.type = type;
}

export function SET_LOGIN_FORM(state, {id, value}) {
    state.options[id] = value;
}

export function SET_LOGIN_DELAY(state, delay) {
    state.options.delay = delay;
    setStorageItem('wineVerifyDelay', delay);
}

export function INIT_LOGIN(state) {
    state.options = state.generatorOptions();
    state.options.delay = +getStorageItem('wineVerifyDelay') || 0;
}

export function CHECK_LOGIN_FORM(state, id) {
    const type = state.options.type;
    const options = state.options;
    state.layout[type] = state.layout[type].map(item => {
        if (id && item.id !== id) {
            return item;
        }
        if (!item.rule) {
            return item;
        }
        if (item.rule && item.rule.every(ru => ru.value.test(options[item.id]))) {
            if (item.dep && options[item.dep] !== options[item.id]) {
                return Object.assign({}, item, {state: 'error'});
            }
            return Object.assign({}, item, {state: 'success'});
        }
        return Object.assign({}, item, {state: 'error'});
    });
}
