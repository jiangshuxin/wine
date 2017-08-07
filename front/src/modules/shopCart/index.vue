<script>
import shopList from './list';
import shopTabbar from './tabbar';
import { Indicator } from 'mint-ui';
import { mapActions, mapGetters } from 'vuex';
export default {
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            envIsPC: 'envIsPC'
        })
    },
    methods: {
        ...mapActions({
            initShopCart: 'initShopCartInfo'
        }),
        async init() {
            Indicator.open();
            try {
                await this.initShopCart();
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        }
    },
    components: {
        shopList,
        shopTabbar
    }
};
</script>

<template>
    <div
        class="shop-cart"
        :style="{paddingBottom: '50px'}"
    >
        <shop-list></shop-list>
         <!--
            -&& !envIsPC
            -->
        <shop-tabbar
            class="shop-tabbar"
            :style="{bottom: $route.name === 'shopCart' ? '56px': '0px'}"
        ></shop-tabbar>
    </div>
</template>

<style lang="stylus" scoped>
.shop-tabbar
    position fixed
    right 0
    left 0
</style>
