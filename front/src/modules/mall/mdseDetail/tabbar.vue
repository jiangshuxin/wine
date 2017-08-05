<script>
import { Badge, Indicator } from 'mint-ui';
import { mapGetters, mapActions } from 'vuex';
import popupMdseCount from 'components/popupMdseCount';
export default {
    props: {
        mdse: {
            type: Object
        }
    },
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            shopCartCount: 'shopCartTotalCount'
        })
    },
    methods: {
        ...mapActions({
            initShopCart: 'initShopCartInfo'
        }),
        async init() {
            Indicator.open();
            try {
                this.initShopCart();
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        showPopup(e) {
            e.srcEvent.stopPropagation();
            e.preventDefault();
            this.$emit('showPopup');
        },
        buy() {
            this.$refs.mdseBuyCount.setMdseInfo(this.mdse);
        },
        goBills(info) {
            this.$router.push({
                name: 'bills',
                query: {
                    merchantId: this.$route.query.merchantId,
                    mdseId: `${info.mdse.mdseId}-${info.count}`
                }
            });
        },
        goShopCart(e) {
            e.srcEvent.stopPropagation();
            e.preventDefault();
            this.$router.push({name: 'mdseShopCart', query: {merchantId: this.$route.query.merchantId}});
        }
    },
    components: {
        mintBadge: Badge,
        popupMdseCount
    }
};
</script>

<template>
    <div>
        <ul class="mdse-detail-tabbar-wrapper">
            <v-touch tag="li" class="shop-cart" @tap="goShopCart">
                <div class="shop-cart-container">
                    <div v-if="shopCartCount > 0" class="badge" size="small" type="error">{{shopCartCount}}</div>
                    <i class="iconfont icon-gouwuche1 gouwuche"></i>
                    <p>购物车</p>
                </div>
            </v-touch>
            <li class="buy">
                <v-touch tag="div" @tap="buy">立即购买</v-touch>
            </li>
            <li class="add-shop-cart">
                <v-touch tag="div" @tap="showPopup" :options="{domEvents: true}">加入购物车</v-touch>
            </li>
        </ul>
        <popup-mdse-count class="mdse-buy-count" ref="mdseBuyCount" @submit="goBills"></popup-mdse-count>
    </div>
</template>

<style lang="stylus" scoped>
ul, p
    margin 0
    padding 0
    list-style none
.mdse-detail-tabbar-wrapper
    display flex
    height 50px
    background #fff
    li
        flex 1
.shop-cart
    padding 8px 0
    text-align center
    border-top 1px solid #aaa
    border-right 1px solid #aaa
    color #ccc
    &-container
        position relative
        display inline-block
    .badge
        position absolute
        top -4px
        right -6px
        width 18px
        height 18px
        line-height 18px
        text-align center
        border-radius 9px
        font-size 12px
        background #cf1f34
        color #fff
    .gouwuche
        font-size 20px
    p
        line-height 1
        font-size 12px
.buy,
.add-shop-cart
    line-height 50px
    text-align center
    font-size 15px
    color #ccc
.buy
    border-top 1px solid #aaa
.add-shop-cart
    background #cf1f34
    color #fff
.mdse-buy-count
    padding 0
    width 100%
</style>
