<script>
import InfiniteLoading from 'vue-infinite-loading';
import { Indicator, Loadmore } from 'mint-ui';
import { mapGetters, mapActions, mapMutations } from 'vuex';
import { formatPrice } from 'common/util';
export default {
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            list: 'orderList',
            pageInfo: 'orderListPageInfo',
            tabs: 'orderTab',
            tabStatus: 'orderTabStatus'
        })
    },
    methods: {
        ...mapActions({
            getOrderList: 'getOrderList'
        }),
        ...mapMutations({
            initListInfo: 'INIT_ORDER_LIST_INFO',
            initList: 'INIT_ORDER_LIST',
            initPageInfo: 'INIT_ORDER_PAGE_INFO',
            changeOrderTab: 'CHANGE_ORDER_TAB',
            setPage: 'SET_ORDER_PAGE_INFO'
        }),
        init() {
            this.initListInfo();
            this.getList();
        },
        async getList() {
            Indicator.open();
            try {
                await this.getOrderList();
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        async loadTop() {
            this.initPageInfo();
            this.initList();
            await this.getList();
            this.$refs.infiniteLoading.$emit('$InfiniteLoading:reset');
            this.$refs.loadmore.onTopLoaded();
        },
        async loadMore() {
            const {pageNumber, totalPage} = this.pageInfo;
            if (+pageNumber < +totalPage) {
                this.setPage(pageNumber + 1);
                await this.getList();
                this.$refs.infiniteLoading.$emit('$InfiniteLoading:loaded');
                return;
            }
            this.$refs.infiniteLoading.$emit('$InfiniteLoading:complete');
        },
        changeTab(id) {
            this.changeOrderTab(id);
            this.initPageInfo();
            this.initList();
            this.getList();
        },
        goDetail(e, item) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            if (this.$refs.goPay && this.$refs.goPay.some(item => item.$el === e.target)) {
                return;
            }
            this.$router.push({
                name: 'orderDetail',
                query: {
                    merchantId: this.$route.query.merchantId,
                    orderId: item.orderId
                }
            });
        },
        goPay(e, item) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            this.$router.push({
                name: 'gopay',
                query: Object.assign({}, this.$route.query, {
                    orderId: item.orderId,
                    from: 'orderList'
                })
            });
        },
        formatPrice
    },
    components: {
        InfiniteLoading,
        mintLoadmore: Loadmore
    }
};
</script>

<template>
    <div class="order-list-wrapper">
        <ul class="tab-bar">
            <v-touch
                tag="li"
                :class="{'selected': tabStatus === tab.id}"
                v-for="tab in tabs.filter(tab => [1, 2].indexOf(tab.id) >= 0)"
                @tap="changeTab(tab.id)">
                {{tab.text}}
            </v-touch>
        </ul>
        <mint-loadmore
            ref="loadmore"
            :top-method="loadTop"
            v-if="list.length"
        >
            <ul class="list-wrapper">
                <v-touch tag="li" class="list-item" v-for="item in list" @tap="goDetail($event, item)" :options="{domEvents: true}">
                    <div class="head">
                        <p class="order-id">订单编号: {{item.orderId}}</p>
                        <p class="order-time">{{item.orderTime}}</p>
                    </div>
                    <div class="content">
                        <ul class="order-mdse">
                            <li class="mdse-img"
                                v-for="mdse in item.mdseInfos"
                                :style="{background: `url('${mdse.pic}') center/cover no-repeat`}"
                            ></li>
                        </ul>
                    </div>
                    <div class="footer">
                        <div class="order-price">
                            <span>共{{item.mdseCount}}件商品</span>
                            <span>{{[0, 1].indexOf(item.status) >= 0 ? '应' : '实' }}付款: ￥{{formatPrice(item.amount / 100)}}</span>
                        </div>
                        <div class="order-status">
                            <span v-if="item.status !== 1">
                                {{tabs.filter(tab => tab.id === item.status)[0].text}}
                            </span>
                            <v-touch v-else tag="div" ref="goPay" class="go-pay" @tap="goPay($event, item)" :options="{domEvents: true}">去支付</v-touch>
                        </div>
                    </div>
                </v-touch>
            </ul>
            <infinite-loading :on-infinite="loadMore" ref="infiniteLoading" spinner="waveDots" :distance="50">
                <span slot="no-more">
                    暂无更多数据
                </span>
                <span slot="no-results">
                    暂无更多数据
                </span>
            </infinite-loading>
        </mint-loadmore>
        <div class="no-more" v-else>
            暂无{{tabs.filter(tab => tabStatus === tab.id)[0].text}}订单
        </div>
    </div>
</template>

<style lang="stylus" scoped>
ul, li, p
    margin 0
    padding 0
    list-style none
.order-list-wrapper
    background #eee
.tab-bar
    margin-bottom 15px
    text-align center
    font-size 0
    background #fff
    li
        display inline-block
        width 55px
        line-height 38px
        font-size 15px
        color #999
        &:first-child
            margin-right 90px
    .selected
        color #181818
        border-bottom 2px solid #181818
.list-wrapper
    .list-item
        margin-bottom 15px
        background #fff
    .head
        padding 0 15px
        line-height 36px
        overflow hidden
    .order-id,
    .order-time
        font-size 14px
        color #999
    .order-id
        float left
    .order-time
        float right
    .order-mdse
        padding 15px 0
        font-size 0
        background #f8f8f8
        white-space nowrap
        overflow auto
    .mdse-img
        display inline-block
        margin-left 15px
        width 50px
        height 50px
        background #fff
        &:last-child
            margin-right 15px
    .footer
        display flex
        flex-flow row nowrap
        padding 0 15px
    .order-price
        flex 1
        line-height 44px
        text-align left
        font-size 14px
        color #181818
    .order-status
        font-size 14px
        span
            line-height 44px
            color #999
    .go-pay
        margin-top 9px
        width 60px
        line-height 26px
        text-align center
        border 0.5px solid #cf1f34
        border-radius 4px
        color #cf1f34
.no-more
    margin-top 100px
    text-align center
    font-size 14px
    color #999
</style>
