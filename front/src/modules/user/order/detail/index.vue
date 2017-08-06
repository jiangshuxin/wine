<script>
import { Indicator, Cell } from 'mint-ui';
import { mapGetters, mapActions, mapMutations } from 'vuex';
import { formatPrice } from 'common/util';
export default {
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            detail: 'orderDetail',
            orderTabs: 'orderTab'
        }),
        orderId() {
            return this.$route.query.orderId;
        }
    },
    methods: {
        ...mapActions({
            getDetailInfo: 'getOrderDetail',
            cancelOrder: 'cancelOrder'
        }),
        ...mapMutations({
            initDetail: 'INIT_ORDER_DETAIL',
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        init() {
            this.initDetail();
            this.getDetail();
        },
        async getDetail() {
            Indicator.open();
            try {
                await this.getDetailInfo(this.orderId);
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        goDetail(item) {
            this.$router.push({
                name: 'mdseDetail',
                query: {
                    merchantId: this.$route.query.merchantId,
                    mdseId: item.mdseId
                }
            });
        },
        cancel(id) {
            Indicator.open();
            try {
                this.cancelOrder(id);
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
            this.changeHint('已成功取消订单');
            this.$router.go(-1);
        },
        pay(id) {
            console.log(id);
        },
        formatPrice
    },
    components: {
        mintCell: Cell
    }
};
</script>

<template>
    <div
        class="order-detail-wrapper"
        v-if="detail.orderId !== -1"
        :style="{paddingBottom: detail.status === 1 ? '55px': '0'}"
    >
        <mint-cell class="address">
            <div slot="title" class="address-cell">
                <i class="iconfont icon-position icon-address"></i>
                <div class="title">
                    <div class="name">
                        <h4>{{detail.receiver}}</h4>
                        <p>{{detail.phone}}</p>
                    </div>
                    <div class="address">{{detail.province}}{{detail.address}}</div>
                </div>
            </div>
        </mint-cell>
        <ul class="list">
            <v-touch tag="li"
                class="list-item-wrapper"
                v-for="item in detail.mdseInfos"
                @tap="goDetail(item)"
            >
                <div class="img">
                    <img :src="item.pic" alt="item.name">
                </div>
                <div class="content">
                    <div class="title">
                        <p class="name">{{item.name}}</p>
                    </div>
                    <div class="content-footer">
                        <p class="price">￥{{formatPrice(item.price / 100)}}</p>
                        <p class="count">x {{item.count}}</p>
                    </div>
                </div>
            </v-touch>
        </ul>
        <div class="order-info">
            <ul class="time">
                <li>
                    <span>订单状态:</span>
                    <span>{{orderTabs.filter(tab => tab.id === detail.status)[0].text}}</span>
                </li>
                <li>
                    <span>订单编号:</span>
                    <span>{{detail.orderId}}</span>
                </li>
                <li>
                    <span>下单时间:</span>
                    <span>{{detail.orderTime}}</span>
                </li>
            </ul>
            <ul class="order-distribution">
                <li>
                    <span>配送方式:</span>
                    <span>{{detail.logisticsCompany}}</span>
                </li>
                <li>
                    <span>快递单号:</span>
                    <span>{{detail.logisticsSeqs}}</span>
                </li>
            </ul>
            <ul class="order-invoice">
                <li>
                    <span>发票信息:</span>
                    <span>{{detail.invoiceInfo}}</span>
                </li>
            </ul>
            <ul class="order-comment" v-if="detail.comment">
                <li>
                    <span>备注:</span>
                    <p>{{detail.comment}}你好啊</p>
                </li>
            </ul>
        </div>
        <div
            class="order-price"
        >
            {{[0, 1].indexOf(detail.status) >= 0 ? '应' : '实'}}付款:
            <span>
                ￥{{formatPrice(detail.amount / 100)}}
            </span>
        </div>
        <div class="footer" v-if="detail.status === 1">
            <div class="cancel-container">
                <v-touch tag="div" class="cancel" @tap="cancel(detail.orderId)">取消订单</v-touch>
            </div>
            <v-touch tag="div" class="pay" @tap="pay(detail.orderId)">支付</v-touch>
        </div>
    </div>
</template>

<style lang="stylus" scoped>
@import '../../../../common/mixin.styl';
ul, li
    list-style none
h4, p, ul, li
    margin 0
    padding 0
    font-weight normal
.address
    margin-bottom 15px
    background-image none
    .address-cell
        display flex
        flex-flow row nowrap
        .icon-address
            align-self center
            margin-right 15px
            font-size 20px
            color #c0c0c0
        .name
            height 34px
            line-height 34px
            font-size 0
            color #181818
            h4, p
                display inline-block
                font-size 16px
            h4
                margin-right 10px
        .address
            margin-bottom 6px
            line-height 1.5
            font-size 12px
            color #999
.list
    padding-left 15px
    margin-bottom 15px
    background #fff
    .list-item-wrapper
        display flex
        padding 15px 0
        border-bottom 0.5px solid #eee
        &:last-child
            border-bottom 0
    .img
        width 60px
        height 60px
        flex-basis 60px
        img
            width 100%
            height 100%
    .content
        flex 1
        padding 6px 15px 0px
        overflow hidden
    .name
        line-height 1
        white-space nowrap
        font-size 16px
        color #181818
        ellipsis()
    .content-footer
        display flex
        flex row nowrap
        margin-top 12px
        overflow hidden
    .price
        flex 1
        line-height 25px
        font-size 15px
        color #cf1f34
        ellipsis()
    .count
        height 25px
        line-height 25px
        font-size 15px
        color #999
.order-info
    padding-left 15px
    font-size 14px
    background #fff
    color #666
    ul
        padding 10px 15px 10px 0
        border-bottom 0.5px solid #eee
    li
        line-height 30px
        span:first-child
            margin-right 10px
    p
        font-size 12px
.order-price
    background #fff
    padding-right 15px
    line-height 40px
    text-align right
    color #181818
    span
        color #cf1f34
.footer
    display flex
    position fixed
    left 0
    right 0
    bottom 0
    height 40px
    line-height 40px
    text-align right
    font-size 0px
    background #fff
    .cancel-container
        flex 1
        border-top 0.5px solid #9a9a9a
    .cancel,
    .pay
        display inline-block
        font-size 14px
    .cancel
        margin-right 15px
        font-size 12px
        color #999
    .pay
        width 100px
        text-align center
        background #cf1f34
        color #fff
</style>
