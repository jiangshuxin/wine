<script>
import { formatPrice } from 'common/util';
import { Indicator } from 'mint-ui';
import { mapGetters, mapActions } from 'vuex';
export default {
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            mdseList: 'billsMdseList'
        }),
        formatMdseInfo() {
            let mdseInfo = this.$route.query.mdseId;
            if (typeof mdseInfo === 'string') {
                mdseInfo = [mdseInfo];
            }
            return mdseInfo.reduce((obj, item) => {
                const info = item.split('-');
                if (info.length !== 2) {
                    this.$router.repalce({name: '/', query: this.$route.query});
                    throw new Error('arguments error!!');
                }
                obj[info[0]] = info[1];
                return obj;
            }, {});
        }
    },
    methods: {
        ...mapActions({
            getBillsMdseList: 'getBillsMdseList'
        }),
        async init() {
            await this.getMdseList();
            if (!this.mdseList.length) {
                this.$router.replace({
                    name: 'home',
                    query: {
                        merchantId: this.$route.query.merchantId
                    }
                });
            }
        },
        async getMdseList() {
            Indicator.open();
            try {
                await this.getBillsMdseList(Object.keys(this.formatMdseInfo));
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        formatPrice
    }
};
</script>

<template>
    <div class="bills-list">
        <div class="list-item-wrapper" v-for="item in mdseList">
            <div class="img">
                <img :src="item.smallPic" alt="item.name">
            </div>
            <div class="content">
                <div class="title">
                    <p class="name">{{item.name}}</p>
                    <p class="year">{{item.year}}</p>
                </div>
                <p class="description">{{item.nameEn}}</p>
                <div class="content-footer">
                    <p class="price">￥{{formatPrice(item.price / 100)}}</p>
                    <p class="count">x {{formatMdseInfo[item.mdseId]}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="stylus" scoped>
@import '../../../common/mixin.styl';
p
    margin 0
    padding 0
.bills-list
    padding-left 15px
    background #fff
.list-item-wrapper
    display flex
    padding 15px 0
    border-bottom 1px solid #c0c0c0
    &:last-child
        border-bottom 0
.img
    width 100px
    height 100px
    flex-basis 100px
    img
        width 100%
        height 100%
.content
    flex 1
    padding 10px 15px 3px
    overflow hidden
.title
    display flex
    flex-flow row nowrap
.name
    flex 3
    line-height 1
    font-size 16px
    color #181818
    ellipsis()
.year
    flex 1
    min-width 38px
    text-align right
    line-height 1
    font-size 16px
    color #181818
.description
    margin-top 5px
    line-height 24px
    font-size 12px
    color #999
    ellipsis()
.content-footer
    display flex
    flex row nowrap
    margin-top 16px
    overflow hidden
.price
    flex 1
    line-height 25px
    font-size 15px
    color #cf1f34
    ellipsis()
.count
    height 25px
    line-height 25px
    font-size 15px
    color #999
</style>
