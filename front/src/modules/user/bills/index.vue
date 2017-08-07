<script>
import addressBar from './address';
import list from './list';
import payment from './payment';
import otherInfo from './otherInfo';
import { Indicator } from 'mint-ui';
import { mapGetters, mapActions, mapMutations } from 'vuex';
import { formatPrice } from 'common/util';
export default {
    created() {
    },
    computed: {
        ...mapGetters({
            mdseList: 'billsMdseList',
            nowAddress: 'addressNowSelected',
            orderDetail: 'orderDetail'
        }),
        formatMdseInfo() {
            let mdseInfo = this.$route.query.mdseId;
            if (typeof mdseInfo === 'string') {
                mdseInfo = [mdseInfo];
            }
            return mdseInfo.reduce((obj, item) => {
                const info = item.split('-');
                if (info.length !== 2) {
                    throw new Error('arguments error!!');
                }
                obj[info[0]] = info[1];
                return obj;
            }, {});
        },
        totalPrice() {
            return this.mdseList.reduce((price, item) => {
                price += +item.price * +this.formatMdseInfo[item.mdseId];
                return price;
            }, 0);
        }
    },
    methods: {
        ...mapActions({
            createSingleOrder: 'createSingleOrder'
        }),
        ...mapMutations({
            changeHint: 'CHANGE_ENV_HINT_INFO',
            initShopCart: 'INIT_SHOP_CART_INFO'
        }),
        async createOrder() {
            Indicator.open();
            try {
                await this.createSingleOrder(this.formatMdseInfo);
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        async pay() {
            if (!this.nowAddress) {
                this.changeHint('请选择您的收货地址');
                return;
            }
            await this.createOrder();
            this.initShopCart([]);
            this.$router.replace({
                name: 'gopay',
                query: {
                    merchantId: this.$route.query.merchantId,
                    orderId: this.orderDetail.orderId,
                    from: 'bills'
                }
            });
        },
        formatPrice
    },
    components: {
        addressBar,
        list,
        payment,
        otherInfo
    }
};
</script>

<template>
    <div class="bills-wrapper">
        <address-bar class="address"></address-bar>
        <list class="list"></list>
        <payment class="payment"></payment>
        <other-info></other-info>
        <div class="bills-footer">
            <div class="price">
                <span>合计</span>
                ￥{{totalPrice ? formatPrice(totalPrice / 100) : 0}}
            </div>
            <v-touch tag="div" class="go-pay" @tap="pay">支付</v-touch>
        </div>
    </div>
</template>

<style lang="stylus" scoped>
.bills-wrapper
    padding-bottom 65px
.address,
.list,
.payment
    margin-bottom 15px
.bills-footer
    position fixed
    left 0
    right 0
    bottom 0
    display flex
    flex row nowrap
    height 50px
    background #fff
.price
    flex 1
    padding-left 30px
    line-height 50px
    border-top 1px solid #9a9a9a
    font-size 14px
    color #cf1f34
    span
        color #181818
.go-pay
    flex-basis 100px
    line-height 50px
    text-align center
    font-size 14px
    background #cf1f34
    color #fff
</style>
