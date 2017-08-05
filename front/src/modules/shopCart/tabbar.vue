<script>
import { formatPrice } from 'common/util';
import { mapGetters, mapMutations } from 'vuex';
export default {
    computed: {
        ...mapGetters({
            isCheckAll: 'shopCartIsCheckAll',
            totalPrice: 'shopCartTotalPrice',
            list: 'shopCartList'
        })
    },
    methods: {
        ...mapMutations({
            changeCheckAll: 'CHANGE_SHOP_CART_CHECK_ALL'
        }),
        checkAll() {
            this.changeCheckAll(!this.isCheckAll);
        },
        bills() {
            this.$router.push({
                name: 'bills',
                query: {
                    merchantId: this.$route.query.merchantId,
                    mdseId: this.list.filter(item => item.checked).map(item => `${item.mdse.mdseId}-${item.count}`)
                }
            });
        },
        formatPrice
    }
};
</script>

<template>
    <div class="shop-cart-tabbar-wrapper" v-if="list.length">
        <v-touch tag="div" class="check-all" @tap="checkAll">
            <i class="check-all-icon" :class="{checked: isCheckAll}"></i>
            <span class="check-all-text">全选</span>
        </v-touch>
        <div class="price">
            <span>合计</span>
            ￥{{totalPrice ? formatPrice(totalPrice / 100) : 0}}
        </div>
        <v-touch tag="div" class="bills" @tap="bills">结算</v-touch>
    </div>
</template>

<style lang="stylus" scoped>
.shop-cart-tabbar-wrapper
    display flex
    flex row nowrap
    height 50px
    background #fff
.check-all
    padding-left 18px
    line-height 50px
    text-align center
    border-top 1px solid #9a9a9a
    font-size 14px
    &-text
        color #181818
    &-icon
        position relative
        display inline-block
        margin-right 8px
        margin-top 16px
        width 16px
        height 16px
        vertical-align top
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
.price
    flex 1
    padding-left 30px
    line-height 50px
    border-top 1px solid #9a9a9a
    font-size 14px
    color #cf1f34
    span
        color #181818
.bills
    flex-basis 100px
    line-height 50px
    text-align center
    font-size 14px
    background #cf1f34
    color #fff
</style>
