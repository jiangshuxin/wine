<script>
import { formatPrice } from 'common/util';
import { Swipe, SwipeItem, Indicator } from 'mint-ui';
import { mapGetters, mapActions, mapMutations } from 'vuex';
import imgModal from '../../manor/imgModal';
import textModal from '../../manor/textModal';
import cell from 'components/cell';
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
        }),
        clientWidth() {
            return document.documentElement.clientWidth;
        }
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
        showModal(item) {
            if (item.type === 'btn-group-text') {
                this.$refs.textModal.set(item, item.value);
                return;
            }
            this.$refs.imgModal.set(item, item.value);
        },
        showShopPopup() {
            if (+this.mdse.status === 2) {
                this.changeHint('该商品已售罄');
                return;
            }
            this.$refs.mdseCount.setMdseInfo(this.mdse);
        },
        showBuyPopup() {
            if (+this.mdse.status === 2) {
                this.changeHint('该商品已售罄');
                return;
            }
            this.$refs.mdseBuyCount.setMdseInfo(this.mdse);
        },
        addShopCart(info) {
            this.setShopCart(info);
            this.changeHint('成功添加商品至购物车');
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
        formatPrice
    },
    components: {
        mintSwipe: Swipe,
        mintSwipeItem: SwipeItem,
        imgModal,
        textModal,
        cell,
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
        <div class="mdse-swipe-wrapper" :style="{'height': `${clientWidth}px`}">
            <div v-if="+mdse.status === 2" class="sell-out">售罄</div>
            <mint-swipe :auto="4000" :prevent="true" :stopPropagation="true">
                <mint-swipe-item
                    class="swipe-item"
                    v-for="(item, index) in mdse.bigPics"
                    :key="index"
                >
                    <div class="img" :style="{'background': `url(${item}) center/cover no-repeat`}"></div>
                </mint-swipe-item>
            </mint-swipe>
        </div>
        <div class="mdse-title">
            <h3 class="name" v-if="mdse.name">{{mdse.name}}</h3>
            <p class="name-en" v-if="mdse.nameEn">{{mdse.nameEn}}</p>
            <p class="price" v-if="mdse.price">￥{{formatPrice(mdse.price / 100)}}</p>
        </div>
        <div class="mdse-property">
            <h4>酒品属性</h4>
            <ul class="mdse-property-list">
                <li
                    v-if="item.value"
                    v-for="item in layoutPolymer.filter(item => item.type === 'text')"
                >
                    <span class="label">{{item.label}}:</span>
                    <span class="text">{{item.value}}</span>
                </li>
            </ul>
        </div>
        <div class="mdse-group">
            <cell
                v-if="item.value && item.value.length"
                v-for="item in layoutPolymer.filter(item => item.type.search('btn-group') !== -1)"
                :label="item.label"
                is-link
                @click-cell="showModal(item)"
            >
            </cell>
        </div>
        <tabbar class="mdse-detai-tabbar" :status="+mdse.status" @showShopPopup="showShopPopup" @showBuyPopup="showBuyPopup"></tabbar>
        <popup-mdse-count class="mdse-detail-count" ref="mdseCount" @submit="addShopCart"></popup-mdse-count>
        <popup-mdse-count class="mdse-buy-count" ref="mdseBuyCount" @submit="goBills"></popup-mdse-count>
        <img-modal ref="imgModal"></img-modal>
        <text-modal ref="textModal"></text-modal>
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
    font-size 16px
    color #fff
.mdse-swipe-wrapper
    position relative
    height 300px
.swipe-item
    background #eee
    .img
        height 100%
.sell-out
    position absolute
    top 22px
    right 0
    width 60px
    text-align center
    line-height 30px
    border-radius 15px 0 0 15px
    font-size 14px
    background #000
    opacity 0.7
    color #fff
    z-index 1
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
    padding 0 15px 15px
    background #fff
    h4
        margin-bottom 10px
        border-bottom 1px solid #eee
        line-height 40px
        font-size 15px
        color #181818
.mdse-property-list
    font-size 14px
    color #444
    li
        display flex
        line-height 25px
    .label
        margin-right 10px
        white-space nowrap
.mdse-detai-tabbar
    position fixed
    bottom 0
    right 0
    left 0
.mdse-group
    margin-top 15px
.mdse-detail-count,
.mdse-buy-count
    width 100%
    padding 0
</style>
