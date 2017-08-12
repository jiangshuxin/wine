<script>
import { formatPrice } from 'common/util';
import { CellSwipe, Button, MessageBox } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
export default {
    computed: {
        ...mapGetters({
            list: 'shopCartList'
        })
    },
    methods: {
        ...mapMutations({
            changeItem: 'CHANGE_SHOP_CART_INFO',
            delItem: 'DEL_SHOP_CART_INFO'
        }),
        del(item) {
            this.delItem(item);
        },
        plus(e, item) {
            e.srcEvent.stopPropagation();
            e.preventDefault();
            this.changeItem(Object.assign({}, item, {count: +item.count + 1}));
        },
        async minus(e, item) {
            e.srcEvent.stopPropagation();
            e.preventDefault();
            if (item.count <= 1) {
                try {
                    await MessageBox.confirm('是否删除商品?');
                } catch (err) {
                    return;
                }
                this.del(item);
                return;
            }
            this.changeItem(Object.assign({}, item, {count: +item.count - 1}));
        },
        check(e, item) {
            e.srcEvent.stopPropagation();
            e.preventDefault();
            this.changeItem(Object.assign({}, item, {checked: !item.checked}));
        },
        goDetail(e, item) {
            const plus = this.$refs.plus.length ? this.$refs.plus : [this.$refs.plus];
            const clickPlus = plus.some(item => item.$el === e.target);
            const minus = this.$refs.minus.length ? this.$refs.minus : [this.$refs.minus];
            const clickMinus = minus.some(item => item.$el === e.target);
            const check = this.$refs.check.length ? this.$refs.check : [this.$refs.check];
            const clickCheck = check.some(item => item.$el === e.target);
            const checkIcon = this.$refs.checkIcon.length ? this.$refs.checkIcon : [this.$refs.checkIcon];
            const clickCheckIcon = checkIcon.some(item => item === e.target);
            if (clickPlus || clickMinus || clickCheck || clickCheckIcon) {
                return;
            }
            this.$router.push({
                name: 'mdseDetail',
                query: {
                    mdseId: item.mdse.mdseId,
                    merchantId: this.$route.query.merchantId
                }
            });
        },
        goMall() {
            this.$router.push({name: 'mall', query: this.$route.query});
        },
        formatPrice
    },
    components: {
        mintCellSwipe: CellSwipe,
        mintButton: Button
    }
};
</script>

<template>
    <div class="shop-cart-list" v-if="list.length">
        <mint-cell-swipe
            v-for="item in list"
            :right="[{
                content: '删除',
                style: { background: '#cf1f34', color: '#fff', fontSize: '14px', lineHeight: '120px' },
                handler: () => del(item)
             }]"
        >
            <v-touch tag="div" class="shop-cart-list-item" slot="title" @tap="goDetail($event, item)">
                <v-touch tag="div" ref="check" class="checkbox-icon" @tap="check($event, item)">
                    <i ref="checkIcon" class="icon" :class="{checked: item.checked}"></i>
                </v-touch>
                <div class="shop-cart-list-item-img">
                    <img :src="item.mdse.smallPic" :alt="item.mdse.name" width="100%" height="100%">
                </div>
                <div class="shop-cart-list-item-info">
                    <h3 class="name">{{item.mdse.name}}</h3>
                    <p class="name-en">{{item.mdse.nameEn}}</p>
                    <div class="info-footer">
                        <span class="price">￥{{formatPrice(item.mdse.price / 100)}}</span>
                        <div class="counter">
                            <v-touch tag="span" ref="minus" class="counter-btn" @tap="minus($event, item)" :options="{domEvent: true, threshold: 100}">-</v-touch>
                            <span class="counter-num">{{item.count}}</span>
                            <v-touch tag="span" ref="plus" class="counter-btn" @tap="plus($event, item)" :options="{domEvent: true, threshold: 100}">+</v-touch>
                        </div>
                    </div>
                </div>
            </v-touch>
        </mint-cell-swipe>
    </div>
    <div v-else class="default">
        <p>
            亲，购物车空空的耶~赶紧去商城挑选美酒吧
        </p>
        <v-touch tag="div" class="btn-touch" @tap="goMall">
            <mint-button class="btn" type="default" plain size="small">去商城</mint-button>
        </v-touch>
    </div>
</template>

<style lang="stylus" scoped>
@import '../../common/mixin.styl';
h3, p
    margin 0
    padding 0
.shop-cart-list-item
    display flex
    padding 15px 0
    &-img
        width 100px
        height 100px
        flex-basis 100px
    &-info
        flex 1
        padding 10px 0 3px 15px
        overflow hidden
    .name
        line-height 1
        font-size 16px
        color #181818
        ellipsis()
    .name-en
        margin-top 5px
        line-height 24px
        font-size 12px
        color #999
        ellipsis()
.checkbox-icon
    padding 0 12px 0 8px
    line-height 100px
    .icon
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
.info-footer
    display flex
    flex row nowrap
    margin-top 16px
    overflow hidden
.price
    flex 1
    line-height 25px
    font-size 15px
    color #cf1f34
    ellipsis()
.counter
    flex-basis 102px
    font-size 0
    &-btn
        display inline-block
        width 24px
        height 24px
        line-height 22px
        text-align center
        vertical-align top
        border 1px solid #dedede
        font-size 16px
        background #f0f0f0
        color #999
    &-num
        display inline-block
        width 50px
        line-height 26px
        text-align center
        vertical-align top
        font-size 16px
.default
    padding-top 140px
    text-align center
    font-size 14px
    color #999
    .btn-touch
        margin-top 10px
        display inline-block
    .btn
        width 80px
        font-size 14px
</style>
