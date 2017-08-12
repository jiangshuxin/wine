<script>
import headFilter from './filter';
import list from './list';
import { Indicator } from 'mint-ui';
import { mapActions, mapMutations } from 'vuex';
import popupMdseCount from 'components/popupMdseCount';
export default {
    created() {
        this.init();
    },
    methods: {
        ...mapActions({
            getMdseList: 'getMallMdseList',
            getMdseListInit: 'getMallMdseListInit',
            setShopCart: 'setShopCartInfo'
        }),
        ...mapMutations({
            initMallInfo: 'INIT_MALL_INFO',
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        init() {
            this.initMallInfo();
            this.getList();
        },
        async getList(type) {
            Indicator.open();
            try {
                if (type) {
                    await this.getMdseListInit(this.$route);
                } else {
                    await this.getMdseList(this.$route);
                }
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        showPopup(info) {
            this.$refs.mdseCount.setMdseInfo(info);
        },
        addShopCart(info) {
            this.changeHint('成功添加商品至购物车');
            this.setShopCart(info);
        }
    },
    components: {
        headFilter,
        list,
        popupMdseCount
    }
};
</script>

<template>
    <div class="mall-wrapper">
        <head-filter class="head-filter"></head-filter>
        <list
            class="shop-list"
            @addShopCart="showPopup"
        ></list>
        <popup-mdse-count
            class="popup"
            ref="mdseCount"
            @submit="addShopCart"
        ></popup-mdse-count>
    </div>
</template>

<style lang="stylus" scoped>
.mall-wrapper
    padding-top 40px
.head-filter
    position fixed
    top 0
    left 0
    right 0
    background #fff
    z-index 1
.popup
    width 100%
</style>
