<script>
import { mapMutations } from 'vuex';
export default {
    methods: {
        ...mapMutations({
            userLogout: 'USER_LOGOUT'
        }),
        logout(e) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            this.userLogout();
            this.$router.go(-1);
        }
    }
};
</script>

<template>
    <div class="personal-wrapper">
        <v-touch tag="div" class="logout" @tap="logout">退出登录</v-touch>
    </div>
</template>

<style lang="stylus" scoped>
.personal-wrapper
    text-align center
.logout
    margin 50px auto
    width 80%
    line-height 44px
    border-radius 6px
    background #cf1f34
    color #fff
</style>
