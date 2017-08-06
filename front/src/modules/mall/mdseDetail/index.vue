<script>
import { formatPrice } from 'common/util';
import { Swipe, SwipeItem, Indicator } from 'mint-ui';
import { mapGetters, mapActions, mapMutations } from 'vuex';
import popupMdseCount from 'components/popupMdseCount';
import tabbar from './tabbar';
export default {
    created() {
        this.init();
    },
    data() {
        return {
            isShow: false,
            hint: {
                isShow: false,
                msg: ''
            }
        };
    },
    computed: {
        ...mapGetters({
            mdse: 'mdseDetailInfo',
            layoutPolymer: 'mdseLayoutPolymer',
            shopCartList: 'shopCartList'
        })
    },
    methods: {
        ...mapActions({
            getDetail: 'getMdseDetailInfo',
            setShopCart: 'setShopCartInfo'
        }),
        ...mapMutations({
            setId: 'SET_MDSE_DETAIL_ID',
            initInfo: 'INIT_MDSE_DETAIL_INFO',
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        async init() {
            this.initInfo();
            this.setId(this.$route.query.mdseId);
            this.getDetailInfo();
        },
        async getDetailInfo() {
            Indicator.open();
            try {
                await this.getDetail();
            } catch (e) {
                Indicator.close();
                throw e;
            } finally {
                this.isShow = true;
            }
            Indicator.close();
        },
        goBack() {
            this.$router.go(-1);
        },
        showPopup() {
            this.$refs.mdseCount.setMdseInfo(this.mdse);
        },
        async addShopCart(info) {
            const result = await this.setShopCart(info);
            if (!result) {
                this.changeHint('购物车中已存在该商品');
            } else {
                this.changeHint('商品已添加至购物车');
            }
        },
        formatPrice
    },
    components: {
        mintSwipe: Swipe,
        mintSwipeItem: SwipeItem,
        popupMdseCount,
        tabbar
    }
};
</script>

<template>
    <div class="mdse-detail-wrapper" v-show="isShow">
        <v-touch class="go-back" @tap="goBack">
            <i class="iconfont icon-left back-icon"></i>
        </v-touch>
        <div class="mdse-swipe-wrapper">
            <mint-swipe :auto="4000">
                <mint-swipe-item
                    class="swipe-item"
                    v-for="(item, index) in mdse.bigPic"
                    :key="index"
                >
                    <div class="img" :style="{'background': `url(${item}) center/cover no-repeat`}"></div>
                </mint-swipe-item>
            </mint-swipe>
        </div>
        <div class="mdse-title">
            <h3 class="name">{{mdse.name}}</h3>
            <p class="name-en">{{mdse.nameEn}}</p>
            <p class="price">￥{{formatPrice(mdse.price / 100)}}</p>
        </div>
        <div class="mdse-property">
            <h4>酒品属性</h4>
            <ul class="mdse-property-list">
                <li v-for="item in layoutPolymer"
                    :style="{
                        'display': item.type !== 'text' ? 'block' : 'flex',
                        'margin-top': item.type !== 'text' ? '5px' : 0
                    }"
                >
                    <template v-if="item.type === 'text'">
                        <span class="label">{{item.label}}</span>
                        <span class="text">{{item.value}}</span>
                    </template>
                    <template v-if="item.type === 'description'">
                        <h4>{{item.label}}</h4>
                        <p>{{item.value}}</p>
                    </template>
                    <template v-if="item.type === 'images'">
                        <img :src="item.value" alt="" width="100%">
                    </template>
                </li>
            </ul>
        </div>
        <tabbar class="mdse-detai-tabbar" :mdse="mdse" @showPopup="showPopup"></tabbar>
        <popup-mdse-count class="mdse-detail-count" ref="mdseCount" @submit="addShopCart"></popup-mdse-count>
    </div>
</template>

<style lang="stylus" scoped>
h3, h4, p
    margin 0
    padding 0
    font-weight normal
ul
    margin 0
    padding 0
    list-style none
.mdse-detail-wrapper
    position relative
    padding-bottom 50px
    background #f8f8f8
.go-back
    position absolute
    top 17px
    left 17px
    display inline-block
    width 40px
    height 40px
    border-radius 20px
    line-height 40px
    text-align center
    background #000
    opacity 0.7
    z-index 1
.back-icon
    font-size 20px
    color #fff
.mdse-swipe-wrapper
    height 300px
.swipe-item
    background #eee
    .img
        height 100%
.mdse-title
    padding 15px
    overflow hidden
    background #fff
    .name
        line-height 26px
        font-size 17px
        color #181818
    .name-en
        line-height 24px
        word-break break-all
        font-size 12px
        color #999
    .price
        line-height 25px
        font-size 17px
        color #cf1f34
.mdse-property
    margin-top 15px
    padding 15px
    background #fff
    h4
        line-height 26px
        font-size 15px
        color #181818
.mdse-property-list
    font-size 14px
    color #999
    li
        display flex
        line-height 25px
    .label
        flex 1
    .text
        flex 1
.mdse-detai-tabbar
    position fixed
    bottom 0
    right 0
    left 0
.mdse-detail-count
    width 100%
    padding 0
</style>
