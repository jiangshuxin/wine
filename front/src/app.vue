<script>
import { Header, Tabbar, TabItem } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
import { checkAgent } from 'common/util';
export default {
    created() {
        this.isPC = checkAgent();
    },
    data() {
        return {
            isPC: true,
            tabShow: true
        };
    },
    computed: {
        ...mapGetters({
            tabList: 'envTabList',
            headMap: 'envHeadMap',
            selectedTab: 'envSelectedTab'
        }),
        headInfo() {
            const { name } = this.$route;
            return name ? this.headMap[name] : '';
        },
        tab: {
            get() {
                return this.selectedTab;
            },
            set(val) {
                this.changeTab(val);
            }
        }
    },
    watch: {
        $route() {
            const isValidName = this.tabList.some(item => item.id === this.$route.name);
            if (isValidName && !this.isPC) {
                this.tabShow = true;
                return;
            }
            this.tabShow = false;
        },
        selectedTab() {
            this.$router.push({name: this.selectedTab});
        }
    },
    methods: {
        ...mapMutations({
            changeTab: 'CHANGE_ENV_SELECTED_TAB'
        })
    },
    components: {
        mintTabbar: Tabbar,
        mintTabItem: TabItem,
        mintHeader: Header
    }
};
</script>

<template>
    <div class="main">
        <mint-header
            fixed
            class="header"
            :title="headInfo.text"
        ></mint-header>
        <div class="content">
            <router-view></router-view>
        </div>
        <mint-tabbar class="tabbar" v-if="tabShow" v-model="tab">
            <mint-tab-item
                v-for="item in tabList"
                class="tabbar-item"
                :id="item.id"
            >
                <i
                    slot="icon"
                    :class="['iconfont', item.iconClass, 'tabbar-icon']"
                ></i>
                {{item.text}}
            </mint-tab-item>
        </mint-tabbar>
    </div>
</template>

<style lang="stylus" scoped>
.header
    border-bottom 1px solid #9a9a9a
    background #fff
    font-size 16px
    color #181818
.content
    width 100%
    margin-top 40px
.tabbar
    position fixed
    right 0
    left 0
    bottom 0
    border-top 1px solid #d0d0d0
    background #f9f9f9
    &-item
        color #c0c0c0
    &-icon
        font-size 24px
        color #c0c0c0
.tabbar > .is-selected
    background #f9f9f9
    color #333
    .tabbar-icon
        color #333
</style>
