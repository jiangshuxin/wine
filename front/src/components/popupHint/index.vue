<script>
import { Popup } from 'mint-ui';
export default {
    props: {
        delay: {
            type: Number,
            default: 800
        },
        close: {
            type: Function
        }
    },
    data() {
        return {
            isShow: false,
            msg: ''
        };
    },
    methods: {
        show(msg) {
            return new Promise((resolve) => {
                this.msg = msg;
                this.isShow = true;
                setTimeout(() => {
                    this.isShow = false;
                    this.msg = '';
                    // close
                    resolve();
                }, this.delay);
            });
        }
    },
    components: {
        mintPopup: Popup
    }
};
</script>

<template>
    <mint-popup v-model="isShow" class="hint" position="top" pop-transition="pop-fade" :close-on-click-modal="false">
        {{msg}}
    </mint-popup>
</template>

<style lang="stylus" scoped>
.hint
    width 100%
    line-height 40px
    text-align center
    font-size 16px
    background #000
    color #fff
    opacity .7
</style>
