<script>
import { Cell } from 'mint-ui';
import { mapMutations, mapGetters } from 'vuex';
import { formatPrice } from 'common/util';
export default {
    computed: {
        ...mapGetters({
            layout: 'personalLayout',
            userInfo: 'userInfo'
        })
    },
    methods: {
        ...mapMutations({
            userLogout: 'USER_LOGOUT'
        }),
        logout(e) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            this.userLogout();
            this.$router.go(-1);
        },
        formatPrice
    },
    components: {
        mintCell: Cell
    }
};
</script>

<template>
    <div class="personal-list">
        <mint-cell
            v-for="item in layout"
            :title="item.text"
            :value="userInfo[item.id]"
        >
            <span v-if="item.id !== 'balance'">{{userInfo[item.id]}}</span>
            <span v-else>￥{{formatPrice(userInfo[item.id] / 100)}}</span>
        </mint-cell>
        <v-touch tag="div" class="logout" @tap="logout">退出登录</v-touch>
    </div>
</template>

<style lang="stylus" scoped>
.logout
    margin 50px auto
    width 80%
    text-align center
    line-height 44px
    border-radius 6px
    background #cf1f34
    color #fff
</style>
