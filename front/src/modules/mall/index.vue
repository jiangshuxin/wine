<script>
import { mapActions } from 'vuex';
export default {
    created() {
        this.init();
    },
    methods: {
        ...mapActions({
            initShopCart: 'initShopCartInfo'
        }),
        init() {
            this.initShopCart();
        }
    }
};
</script>

<template>
    <div>
        <router-view></router-view>
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
