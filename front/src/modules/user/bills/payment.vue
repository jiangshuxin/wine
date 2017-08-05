<script>
import { Cell } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
export default {
    computed: {
        ...mapGetters({
            payment: 'payment'
        }),
        list() {
            return this.payment.list;
        },
        selected() {
            return this.payment.selected;
        }
    },
    methods: {
        ...mapMutations({
            setSelected: 'SET_PAYMENT_SELECTED'
        }),
        checked(id) {
            this.setSelected(id);
        }
    },
    components: {
        mintCell: Cell
    }
};
</script>

<template>
    <div class="payment-wrapper">
        <div class="payment-title">支付方式</div>
        <v-touch
            tag="div"
            class="payment-item-container"
            v-for="item in list"
            @tap="checked(item.id)"
        >
            <mint-cell
                class="payment-item"
                :title="item.text"
            >
                <i slot="icon" :class="item.className"></i>
                <i class="radio" :class="{checked: item.id === selected}"></i>
            </mint-cell>
        </v-touch>
    </div>
</template>

<style lang="stylus">
.payment-wrapper
    padding-left 15px
    background #fff
    .payment-title
        line-height 44px
        font-size 15px
        color #181818
    .mint-cell
        background-image none
    .mint-cell-wrapper
        padding-left 0
        background-image none
    .mint-cell-text
        font-size 14px
    .payment-item
        border-top 1px solid #c0c0c0
    .wechat
        margin-right 6px
        vertical-align middle
        font-size 20px
        color #62b900
    .radio
        position relative
        display inline-block
        width 16px
        height 16px
        border 1px solid #c0c0c0
        border-radius 9px
    .checked
        background #181818
        border 1px solid #181818
        &:before
            position absolute
            top 4px
            left 3px
            display inline-block
            content ''
            width 7px
            height 4px
            border-left 2px solid #fff
            border-bottom 2px solid #fff
            transform rotate(-45deg)
</style>
