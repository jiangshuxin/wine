<script>
import { Indicator } from 'mint-ui';
import { mapGetters, mapActions, mapMutations } from 'vuex';
export default {
    data() {
        return {
            payType: 1
        };
    },
    created() {
        if (typeof WeixinJSBridge === 'undefined') {
            this.payType = 2;
        }
        this.init();
    },
    computed: {
        ...mapGetters({
            orderDetail: 'orderDetail',
            pic: 'payPic',
            isPC: 'envIsPC'
        })
    },
    methods: {
        ...mapActions({
            getOrder: 'getOrderDetail',
            pay: 'goPay'
        }),
        ...mapMutations({
            initPayInfo: 'INIT_PAY_INFO'
        }),
        async init() {
            this.initPayInfo();
            if (!this.orderDetail) {
                await this.getOrderDetail();
            }
            await this.goPay();
            if (this.payType === 2) {
                this._timer = setInterval(async () => {
                    try {
                        await this.getOrder(this.$route.query.orderId);
                    } catch (e) {
                        clearInterval(this._timer);
                        throw e;
                    }
                }, 2000);
            }
        },
        async getOrderDetail() {
            Indicator.open();
            try {
                await this.getOrder(this.$route.query.orderId);
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        async goPay() {
            Indicator.open();
            try {
                await this.pay({
                    orderId: this.$route.query.orderId,
                    payType: this.payType
                });
                /*
                alert('not wx ...');
                if (document.addEventListener) {
                    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                } else if ( document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                    document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                }
                */
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        }
    },
    watch: {
        orderDetail() {
            if (this.orderDetail.status === 2) {
                if (this.$route.query.from === 'bills') {
                    this.$router.replace({
                        name: 'orderDetail',
                        query: this.$route.query
                    });
                } else {
                    this.$router.go(-1);
                }
                clearInterval(this._timer);
            }
        }
    },
    beforeDestroy() {
        clearInterval(this._timer);
    }
};
</script>

<template>
    <div class="pay-wrapper">
        <div class="pay-pic" v-if="pic">
            <span>请扫面二维码支付</span>
            <div class="pay-pic-img">
                <img :src="pic" alt="支付二维码" width="100%">
            </div>
        </div>
    </div>
</template>

<style lang="stylus" scoped>
.pay-pic
    text-align center
    span
        display inline-block
        margin 50px 0
        font-size 16px
        color #181818
.pay-pic-img
    img
        max-width 200px
</style>
