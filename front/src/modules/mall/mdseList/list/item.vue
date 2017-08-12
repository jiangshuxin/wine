<script>
import { formatPrice } from 'common/util';
import { mapMutations } from 'vuex';
export default {
    props: {
        item: {
            type: Object,
            require: true
        }
    },
    methods: {
        ...mapMutations({
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        goDetail(e) {
            if (e.target === this.$refs['add-cart'].$el) {
                return;
            }
            this.$router.push({name: 'mdseDetail', query: {
                mdseId: this.item.mdseId,
                merchantId: this.$route.query.merchantId
            }});
        },
        addShopCart(e) {
            e.srcEvent.stopPropagation();
            e.preventDefault();
            if (+this.item.status === 2) {
                this.changeHint('该商品已售罄，无法加入购物车');
                return;
            }
            this.$emit('addShopCart', this.item);
        },
        formatPrice
    }
};
</script>

<template>
    <v-touch tag="div" ref="list-item-wrapper" class="list-item-wrapper" @tap="goDetail">
        <div class="img">
            <div v-if="+item.status === 2" class="sell-out">售罄</div>
            <img :src="item.smallPic" :alt="item.name">
        </div>
        <div class="content">
            <div class="title">
                <p class="name">{{item.name}}</p>
                <p class="year">{{item.year}}</p>
            </div>
            <p class="description">{{item.nameEn}}</p>
            <div class="content-footer">
                <p class="price">￥{{formatPrice(item.price / 100)}}</p>
                <v-touch
                    tag="div"
                    ref='add-cart'
                    class="add-cart"
                    :class="{'add-cart-sellout': +item.status === 2}"
                    :options="{domEvents: true}"
                    @tap="addShopCart"
                >
                    加入购物车
                </v-touch>
            </div>
        </div>
    </v-touch>
</template>

<style lang="stylus" scoped>
@import '../../../../common/mixin.styl';
p
    margin 0
    padding 0
.list-item-wrapper
    display flex
    padding 15px 0
.img
    position relative
    width 100px
    height 100px
    flex-basis 100px
    background #f0f0f0
    img
        width 100%
        height 100%
.sell-out
    position absolute
    top 0
    bottom 0
    left 0
    right 0
    text-align center
    line-height 100px
    font-weight normal
    background rgba(0, 0, 0, .4)
    color #f0f0f0
.content
    flex 1
    padding 10px 15px 3px
    overflow hidden
.title
    display flex
    flex-flow row nowrap
.name
    flex 3
    line-height 1
    font-size 16px
    color #181818
    ellipsis()
.year
    flex 1
    min-width 38px
    text-align right
    line-height 1
    font-size 16px
    color #181818
.description
    margin-top 5px
    line-height 24px
    font-size 12px
    color #999
    ellipsis()
.content-footer
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
.add-cart
    width 80px
    height 25px
    border-radius 4px
    text-align center
    line-height 25px
    font-size 12px
    background #cf1f34
    color #fff
.add-cart-sellout
    background #ccc
</style>
