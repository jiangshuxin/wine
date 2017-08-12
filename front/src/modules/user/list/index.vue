<script>
import { Cell } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
import Clipboard from 'clipboard';
export default {
    mounted() {
        this.clip = new Clipboard('.copy-referralcode');
        this.clip.on('success', () => {
            this.changeHint('推荐码已复制到剪贴板');
        });
    },
    computed: {
        ...mapGetters({
            layout: 'userLayout',
            userInfo: 'userInfo'
        })
    },
    methods: {
        ...mapMutations({
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        routeHandler(info) {
            this.$router.push({
                name: info.routeName,
                query: {
                    merchantId: this.$route.query.merchantId
                }
            });
        }
    },
    components: {
        mintCell: Cell
    },
    beforeDestroy() {
        this.clip.destroy();
    }
};
</script>

<template>
    <div class="user-list-wrapper">
        <div class="header">
            <div class="head-icon">
                <img src="../assets/head-icon.png" width="100%">
            </div>
            <h3 class="user-name">{{userInfo.userName}}</h3>
        </div>
        <div class="list">
            <v-touch
                tag="div"
                v-for="item in layout"
                :key="item.routeName"
                @tap="routeHandler(item)"
            >
                <mint-cell
                    :title="item.text"
                    :is-link="item.id !== 'referralCode'"
                    style="fontSize: 12px;"
                >
                <span v-if="item.id === 'referralCode'">{{userInfo[item.id]}}</span>
                    <button
                        class="copy-referralcode"
                        v-if="item.id === 'referralCode'"
                        :data-clipboard-text="userInfo[item.id]"
                        data-clipboard-action="copy"
                    >
                        复制
                    </button>
                </mint-cell>
            </v-touch>
        </div>
    </div>
</template>

<style lang="stylus">
h3
    margin 0
    padding 0
button
    padding 0
    border 0
.user-list-wrapper
    .header
        height 200px
        overflow hidden
        background url('../assets/user-bg.jpg') center/cover no-repeat
    .head-icon
        margin 48px auto 10px
        width 54px
    .user-name
        text-align center
        font-size 16px
        font-weight normal
        color #fff
    .mint-cell-wrapper
        font-size 14px
    .copy-referralcode
        margin-left 5px
        width 40px
        text-align center
        line-height 26px
        border-radius 4px
        font-size 12px
        background #181818
        color #fff
        outline none
</style>
