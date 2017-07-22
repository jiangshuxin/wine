<script>
export default {
    props: {
        item: {
            type: Object,
            require: true
        }
    },
    data() {
        return {
            tapOptions: {domEvents: true},
            count: 0
        };
    },
    methods: {
        test(e) {
            // 出了点问题
            if (e.target === this.$refs['add-cart'].$el) {
                return;
            }
            console.log('goDetail');
        },
        goDetail(e) {
            console.log('goDetail');
            console.log(e);
            alert('go');
        },
        addShopCart(e) {
            e.changedPointers[0].stopPropagation();
            console.log(e);
            e.srcEvent.stopPropagation();
            console.log('addShopCart');
            console.log(e);
        }
    }
};
</script>

<template>
    <v-touch tag="div" ref="list-item-wrapper" class="list-item-wrapper" @tap="goDetail" :options="tapOptions">
        <div class="img">
            <img :src="item.url" alt="item.name">
        </div>
        <div class="content">
            <div class="title">
                <p class="name">{{item.name}}</p>
                <p class="year">{{item.year}}</p>
            </div>
            <p class="description">{{item.description}}</p>
            <div class="content-footer">
                <p class="price">￥{{item.price}}</p>
                <v-touch tag="div" ref='add-cart' class="add-cart" @tap="addShopCart" :options="tapOptions">加入购物车</v-touch>
            </div>
        </div>
    </v-touch>
</template>

<style lang="stylus" scoped>
@import '../../../common/mixin.styl';
p
    margin 0
    padding 0
.list-item-wrapper
    display flex
    padding 15px 0
.img
    width 100px
    height 100px
    flex-basis 100px
    img
        width 100%
        height 100%
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
</style>
