<script>
import { formatPrice } from 'common/util';
import { Popup } from 'mint-ui';
export default {
    data() {
        return {
            popupVisible: false,
            mdseInfo: null,
            count: 1
        };
    },
    methods: {
        setMdseInfo(info) {
            this.mdseInfo = info;
            this.popupVisible = true;
        },
        plus() {
            if (this.count < 99 ) {
                this.count++;
            }
        },
        minus() {
            if (this.count > 1) {
                this.count--;
            }
        },
        submit() {
            this.$emit('submit', {
                mdse: this.mdseInfo,
                count: this.count
            });
            this.popupVisible = false;
        },
        formatPrice
    },
    watch: {
        popupVisible() {
            if (!this.popupVisible) {
                this.mdseInfo = null;
                this.count = 1;
            }
        }
    },
    components: {
        mintPopup: Popup
    }
};
</script>

<template>
    <mint-Popup
        v-if="mdseInfo"
        v-model="popupVisible"
        position="bottom"
        :close-on-click-modal="true"
    >
        <div class="mdse-count-wrapper">
            <div class="mdse-info">
                <div class="mdse-info-img">
                    <img :src="mdseInfo.smallPic" alt="mdseInfo.name" width="100%" height="100%">
                </div>
                <div class="mdse-info-name">
                    <h4>{{mdseInfo.name}}</h4>
                    <p>￥{{formatPrice(mdseInfo.price / 100)}}</p>
                </div>
            </div>
            <div class="mdse-count">
                <span class="mdse-count-label">数量:</span>
                <div class="mdse-count-counter">
                    <v-touch tag="span" class="mdse-count-counter-btn" @tap="minus" :options="{threshold: 100}">-</v-touch>
                    <span class="mdse-count-counter-num">{{count}}</span>
                    <v-touch tag="span" class="mdse-count-counter-btn" @tap="plus" :options="{threshold: 100}">+</v-touch>
                </div>
            </div>
            <v-touch tag="div" class="submit" @tap="submit">确定</v-touch>
        </div>
    </mint-Popup>
</template>

<style lang="stylus" scoped>
h4, p
    margin 0
    padding 0
.mdse-count-wrapper
    width 100%
    background #fff
.mdse-info
    display flex
    margin-left 15px
    padding 10px 10px 10px 0
    border-bottom 1px solid #c0c0c0
    &-img
        width 50px
        height 50px
    &-name
        flex 1
        padding-left 10px
        h4
            line-height 25px
            font-size 14px
            color #181818
        p
            line-height 25px
            font-size 12px
            color #cf1f34
.mdse-count
    display flex
    margin-left 15px
    padding 14px 10px 14px 0
    &-label
        line-height 25px
        font-size 14px
        color #181818
    &-counter
        flex 1
        text-align right
        font-size 0
    &-counter-num
        display inline-block
        width 50px
        line-height 26px
        text-align center
        vertical-align top
        font-size 16px
    &-counter-btn
        display inline-block
        width 24px
        height 24px
        line-height 22px
        text-align center
        vertical-align top
        border 1px solid #dedede
        font-size 16px
        background #f0f0f0
        color #999
.submit
    height 45px
    line-height 45px
    text-align center
    font-size 16px
    background #cf1f34
    color #fff
</style>
