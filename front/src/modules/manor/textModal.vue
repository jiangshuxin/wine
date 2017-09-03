<script>
import { Popup } from 'mint-ui';
export default {
    data() {
        return {
            show: false,
            text: '',
            info: {}
        };
    },
    methods: {
        set(info, text) {
            if (text) {
                this.info = info;
                this.text = text;
                this.show = true;
            }
        },
        close() {
            this.show = false;
        }
    },
    components: {
        mintPopup: Popup
    },
    watch: {
        show() {
            if (!this.show) {
                setTimeout(() => {
                    this.text = '';
                    this.info = {};
                }, 200);
            }
        }
    }
};
</script>
<template>
    <mint-popup
        class="text-modal"
        v-model="show"
        position="right"
        close-on-click-modal="false"
    >
        <h4>
            {{info.label}}
            <v-touch tag="i" class="close" @tap="close"></v-touch>
        </h4>
        <p>
            {{text}}
        </p>
    </mint-popup>
</template>
<style lang="stylus" scoped>
h4, p
    margin 0
    padding 0
.text-modal
    left 0
    right 0
    margin 0 20px
    padding 15px
    max-height 70%
    border-radius 6px
    overflow-y scroll
h4
    margin-bottom 15px
    padding-bottom 15px
    line-height 24px
    border-bottom 1px solid #eee
p
    font-size 14px
    color #333
.close
    position relative
    float right
    width 24px
    height 24px
    border-radius 12px
    &:after
        position absolute
        top 12px
        left 3px
        display inline-block
        content " "
        width 18px
        height 1px
        background #333
        transform rotateZ(-45deg)
    &:before
        position absolute
        top 12px
        left 3px
        display inline-block
        content " "
        width 18px
        height 1px
        background #333
        transform rotateZ(45deg)
</style>
