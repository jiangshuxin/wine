<script>
import { Cell } from 'mint-ui';
import { mapGetters } from 'vuex';
export default {
    created() {
    },
    computed: {
        ...mapGetters({
            layout: 'userLayout',
            userInfo: 'userInfo'
        })
    },
    methods: {
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
                <mint-cell :title="item.text" is-link style="fontSize: 12px;"></mint-cell>
            </v-touch>
        </div>
    </div>
</template>

<style lang="stylus">
h3
    margin 0
    padding 0
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
</style>
