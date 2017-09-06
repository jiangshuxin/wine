<script>
export default {
    props: {
        label: {
            type: String
        },
        value: {
            type: String
        },
        isLink: {
            type: Boolean
        }
    },
    data() {
        return {
            onPress: false
        };
    },
    methods: {
        click(e) {
            this.onPress = true;
            setTimeout(() => {
                this.onPress = false;
            }, 100);
            e.preventDefault();
            e.srcEvent.stopPropagation();
            this.$emit('click-cell');
        }
    }
};
</script>
<template>
    <div class="cell-wrapper">
        <v-touch
            @tap="click"
            :options="{domEvent: true, threshold: 100}"
        >
            <div class="cell">
                <div class="mask" :class="{'press': onPress}" ref="mask"></div>
                <div class="label">
                    <slot name="label">
                        {{label}}
                    </slot>
                </div>
                <i class="iconfont icon-left arrow" v-if="isLink"></i>
            </div>
        </v-touch>
        <div class="cell-right">
            <span class="button">
                <slot name="button"></slot>
            </span>
            <span class="value">
                <slot name="value">
                    {{value}}
                </slot>
            </span>
        </div>
    </div>
</template>
<style lang="stylus" scoped>
.cell-wrapper
    position relative
    background-color #fff
/*
.cell-wrapper:last-child
    background-image linear-gradient(0deg, #d9d9d9, #d9d9d9 50%, transparent 50%)
    background-size 100% 1px
    background-repeat no-repeat
    background-position bottom
    background-origin content-box
*/
.cell-wrapper .cell
    padding 0 15px
    line-height 44px
    font-size 14px
    background-image linear-gradient(180deg, #d9d9d9, #d9d9d9 50%, transparent 50%)
    background-size 120% 1px
    background-repeat no-repeat
    background-position top left
    background-origin content-box
    overflow hidden
.cell-wrapper:first-child .cell
        background-image none
        /* background-origin border-box */
.cell-right
    position absolute
    right 15px
    top 0px
    padding 9px 0
.label
    float left
.value
    float right
    margin-right 6px
    line-height 26px
    color #999
.button
    float right
.arrow
    display block
    float right
    font-size 12px
    transform rotate(180deg)
    color #999
.mask
    &:after
        transition opacity 0.1s ease
        background-color #000
        content " "
        opacity 0
        top 0
        right 0
        left 0
        bottom 0
        position absolute
.press
    &:after
        opacity 0.1
</style>
