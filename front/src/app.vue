<script>
import { Tabbar, TabItem } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
export default {
    created() {
        console.log(1);
    },
    components: {
        mintTabbar: Tabbar,
        mintTabItem: TabItem
    },
    computed: {
        ...mapGetters({
            selectedTab: 'selectedTab'
        }),
        tab: {
            get() {
                return this.selectedTab;
            },
            set(val) {
                this.changeTab(val);
            }
        }
    },
    methods: {
        ...mapMutations({
            changeTab: 'CHANGE_SELECTED_TAB'
        })
    }
};
</script>

<template>
    <div class="main">
        <router-view></router-view>
        <mint-tabbar class="tabbar" v-model="tab">
            <mint-tab-item id="home" class="tabbar-item">
                <i slot="icon" class="iconfont icon-home-copy tabbar-icon"></i>
                首页
            </mint-tab-item>
            <mint-tab-item id="mall" class="tabbar-item">
                <i slot="icon" class="iconfont icon-gouwu tabbar-icon"></i>
                商城
            </mint-tab-item>
            <mint-tab-item id="shopCart" class="tabbar-item">
                <i slot="icon" class="iconfont icon-gouwuche tabbar-icon"></i>
                购物车
            </mint-tab-item>
            <mint-tab-item id="user" class="tabbar-item">
                <i slot="icon" class="iconfont icon-wode tabbar-icon"></i>
                我的
            </mint-tab-item>
        </mint-tabbar>
    </div>
</template>

<style lang="stylus" scoped>
.tabbar
    &-item
        background-color #fff
        color #c0c0c0
    &-icon
        font-size 20px
        color #c0c0c0
    .is-selected
        background-color #fff
        color #333
        .tabbar-icon
            color #333
</style>
