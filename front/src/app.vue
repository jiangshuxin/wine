<script>
import {
    Header,
    Tabbar,
    TabItem,
    Button,
    Indicator
} from 'mint-ui';
import popupHint from 'components/popupHint';
import { mapGetters, mapMutations, mapActions } from 'vuex';
import { checkAgent, getStorageItem } from 'common/util';
import errorEventBus from 'common/error';
export default {
    async created() {
        await this.init();
        errorEventBus.$on('requestError', data => {
            console.log(data);
        });
    },
    data() {
        return {
            tabShow: true
        };
    },
    computed: {
        ...mapGetters({
            tabList: 'envTabList',
            headMap: 'envHeadMap',
            isLogin: 'userIsLogin',
            selectedTab: 'envSelectedTab',
            selectedMap: 'envSelectedMap',
            merchantInfo: 'merchantInfo',
            envHint: 'envHintMsg',
            envMerchantId: 'envMerchantId',
            envIsPC: 'envIsPC'
        }),
        headInfo() {
            const { name } = this.$route;
            return name ? this.headMap[name] || null : '';
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
        async $route() {
            await this.init();
        },
        selectedTab() {
            this.$router.push({
                name: this.selectedTab,
                query: {
                    merchantId: this.envMerchantId
                }
            });
        },
        envHint() {
            if (!this.envHint) {
                return;
            }
            this.$refs.hint.show(this.envHint).then(() => {
                this.changeHint('');
            });
        }
    },
    methods: {
        ...mapActions({
            getUserInfo: 'getUserInfo',
            getMerchantInfo: 'getMerchantInfo'
        }),
        ...mapMutations({
            changeTab: 'CHANGE_ENV_SELECTED_TAB',
            setMerchantId: 'SET_ENV_MERCHANT_ID',
            setAgent: 'SET_ENV_AGENT',
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        async init() {
            this.setMerchantId(this.$route.query.merchantId);
            this.setAgent(this.checkAgent());
            const matchId = this.selectedMap[this.$route.name];
            // 路由name 能匹配上tabid的页面
            // 才会展示下方tab
            const isValidName = this.tabList.some(item => item.id === matchId);
            // pc 端不显示tab
            // !this.envIsPC
            if (isValidName) {
                this.tabShow = true;
                this.changeTab(matchId);
            } else {
                this.tabShow = false;
            }
            await this.getMerchant();
            this.setTitle();
            await this.getUser();
        },
        async getUser() {
            const userId = this.getStorageItem('wineUserId');
            if (userId && !this.isLogin) {
                Indicator.open();
                try {
                    await this.getUserInfo(userId);
                } catch (e) {
                    Indicator.close();
                    throw e;
                }
                Indicator.close();
            }
        },
        async getMerchant() {
            if (!this.$route.query.merchantId) {
                return;
            }
            if (!this.merchantInfo.name) {
                Indicator.open();
                try {
                    await this.getMerchantInfo(this.$route.query.merchantId);
                } catch (e) {
                    Indicator.close();
                    throw e;
                }
                Indicator.close();
            }
        },
        setTitle() {
            document.querySelector('title').innerHTML = this.merchantInfo.name;
        },
        goBack() {
            const route = this.$route;
            if (route.name === 'gopay') {
                if (route.query.from === 'bills') {
                    this.$router.replace({
                        name: 'orderDetail',
                        query: {
                            merchantId: route.query.merchantId,
                            orderId: route.query.orderId
                        }
                    });
                    return;
                }
            }
            if (route.params.goBack) {
                this.$router.replace({
                    name: route.params.goBack,
                    query: route.query
                });
                return;
            }
            this.$router.go(-1);
        },
        checkAgent,
        getStorageItem
    },
    components: {
        mintTabbar: Tabbar,
        mintTabItem: TabItem,
        mintHeader: Header,
        mintButton: Button,
        popupHint: popupHint
    }
};
</script>

<template>
    <div class="main"
        :style="{
            'padding-top': headInfo ? '40px' : 0,
            'padding-bottom': tabShow ? '56px' : 0
         }"
    >
        <mint-header
            v-if="headInfo"
            fixed
            class="header"
            :title="headInfo.text"
        >
            <v-touch v-if="headInfo.goBack" tag="div" slot="left" @tap="goBack">
                <mint-button icon="back"></mint-button>
            </v-touch>
        </mint-header>
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
        <popup-hint ref="hint"></popup-hint>
    </div>
</template>

<style lang="stylus" scoped>
.main
    box-sizing border-box
.header
    border-bottom 1px solid #9a9a9a
    background #fff
    font-size 16px
    color #181818
.content
    margin 0 auto
    width 100%
    max-width 1000px
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
    .icon-format
        color #cf1f34
.icon-format
    position relative
    font-size 32px
    &:before
        position absolute
        top -3px
        left -4px
</style>
