<script>
import { Field, Indicator } from 'mint-ui';
import { mapGetters, mapMutations, mapActions } from 'vuex';
export default {
    beforeRouteEnter(to, from, next) {
        next(vm => {
            vm.from = from.name;
        });
    },
    created() {
        if (this.isLogin) {
            this.$router.replace({
                name: 'home',
                query: this.$route.query
            });
            return;
        }
        this.init();
    },
    data() {
        return {
            from: ''
        };
    },
    computed: {
        ...mapGetters({
            layout: 'loginLayout',
            tabs: 'loginTabs',
            options: 'loginOptions',
            delay: 'loginVerifyDelay',
            isLogin: 'userIsLogin'
        })
    },
    methods: {
        ...mapActions({
            getVerifyCode: 'getLoginVerifyCode',
            loginWine: 'loginWine'
        }),
        ...mapMutations({
            initLogin: 'INIT_LOGIN',
            setType: 'SET_LOGIN_TYPE',
            setForm: 'SET_LOGIN_FORM',
            setDelay: 'SET_LOGIN_DELAY',
            checkForm: 'CHECK_LOGIN_FORM',
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        init() {
            this.initLogin();
        },
        changeType(type) {
            this.setType(type);
        },
        changeForm(value, id) {
            this.setForm({id, value});
        },
        async getVerify() {
            if (this.delay > 0) {
                return;
            }
            this.checkForm('phone');
            const phone = this.layout.filter(item => item.id === 'phone')[0];
            if (phone.state !== 'success') {
                this.changeHint('请输入正确的手机号码');
                return;
            }
            Indicator.open();
            try {
                await this.getVerifyCode();
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
            this.setDelay(60);
        },
        async login() {
            this.checkForm();
            const isValid = this.layout.every(item => {
                if (item.rule) {
                    return item.state === 'success';
                }
                return true;
            });
            if (isValid) {
                Indicator.open();
                try {
                    await this.loginWine();
                } catch (e) {
                    Indicator.close();
                    throw e;
                }
                Indicator.close();
                this.setDelay(0);
                if (this.from) {
                    this.$router.go(-1);
                    return;
                }
                this.$router.push({
                    name: 'home',
                    query: this.$route.query
                });
            }
        }
    },
    watch: {
        delay() {
            if (+this.delay > 0) {
                setTimeout(() => {
                    this.setDelay(+this.delay - 1);
                }, 1000);
            }
        }
    },
    components: {
        mintField: Field
    }
};
</script>

<template>
    <div class="entry-wrapper">
        <ul class="tab-bar">
            <v-touch
                tag="li"
                class="tab"
                :class="[`tab${tab.id}`, {'selected': tab.id === options.type}]"
                v-for="tab in tabs"
                @tap="changeType(tab.id)"
            >
                {{tab.text}}
            </v-touch>
        </ul>
        <div class="form">
            <mint-field
                :style="{fontSize: '14px'}"
                class="form-item"
                v-for="item in layout"
                :type="item.type"
                :placeholder="item.placeholder"
                :value="options[item]"
                :state="item.state"
                @input="changeForm($event, item.id)"
                @change="checkForm(item.id)"
            >
                <v-touch
                    v-if="item.id === 'verifyCode'"
                    tag="span"
                    class="get-verify"
                    :class="{'delay': delay > 0}"
                    @tap="getVerify"
                >
                    获取验证码
                    <span v-show="delay">({{delay}}s)</span>
                </v-touch>
            </mint-field>
            <v-touch tag="div" class="login" @tap="login">登录</v-touch>
        </div>
    </div>
</template>

<style lang="stylus">
ul, li
    margin 0
    padding 0
    list-style none
.entry-wrapper
    margin 0 10px
    .tab-bar
        margin-top 80px
        height 40px
        line-height 40px
        text-align left
        font-size 0
        color #999
        li
            position relative
            display inline-block
            padding 0 15px
            font-size 16px
            background #f2f2f2
        .tab2
            border-radius 10px 0 0 0
            &:before
                content: ''
                display inline-block
                position absolute
                top 0
                right -16px
                width 0
                height 0
                border-bottom 40px solid #fff
                border-right 16px solid transparent
                z-index -1
        .tab1
            border-radius 0 10px 0 0
            &:before
                content: ''
                display inline-block
                position absolute
                top 0
                left -16px
                width 0
                height 0
                border-bottom 40px solid #fff
                border-left 16px solid transparent
                z-index -1
        .selected
            height 40px
            background #fff
            color #181818
            &:before
                z-index 1
    .form
        padding 0 15px
        border-radius 0 10px 10px 10px
        overflow hidden
        background #fff
    .form-item
        .mint-cell-wrapper
            padding 0
            font-size 14px
            background-image linear-gradient(0deg, #d9d9d9, #d9d9d9 50%, transparent 50%)
            background-position bottom
    .get-verify
        display inline-block
        margin-left 10px
        width 120px
        height 30px
        line-height 30px
        text-align center
        border-radius 6px
        background #181818
        color #fff
    .delay
        background #999
    .login
        margin 80px 0 20px
        height 44px
        line-height 44px
        text-align center
        border-radius 6px
        font-size 16px
        background #181818
        color #fff
</style>
