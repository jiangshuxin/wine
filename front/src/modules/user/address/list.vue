<script>
import { phoneNumberShelter } from 'common/util';
import { CellSwipe, Indicator, MessageBox } from 'mint-ui';
import { mapActions, mapGetters, mapMutations } from 'vuex';
export default {
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            list: 'addressList'
        }),
        sortList() {
            return [].concat(
                this.list.filter(item => item.isDefault),
                this.list.filter(item => !item.isDefault)
            );
        }
    },
    methods: {
        ...mapActions({
            getAddressList: 'getAddressListInfo',
            deleteAddress: 'deleteAddress'
        }),
        ...mapMutations({
            setNowAddress: 'SET_NOW_ADDRESS'
        }),
        async init() {
            await this.getList();
        },
        async getList() {
            Indicator.open();
            try {
                await this.getAddressList();
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        edit(e, item) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            this.$router.push({
                name: 'addressModify',
                query: {
                    merchantId: this.$route.query.merchantId,
                    addressId: item.addressId
                }
            });
        },
        async del(e, item) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            /* eslint-disable */
            const result = await MessageBox({
                title: '提示',
                message: '是否删除地址?',
                showCancelButton: true,
                showConfirmButton: true,
                confirmButtonClass: 'wine-confirm',
                cancelButtonClass: 'wine-cancel'
            });
            /* eslint-enable */
            if (result === 'cancel') {
                return;
            }
            Indicator.open();
            try {
                await this.deleteAddress(item.addressId);
            } catch (err) {
                Indicator.close();
                throw err;
            }
            Indicator.close();
            this.init();
        },
        goCreate() {
            this.$router.push({
                name: 'addressCreate',
                query: {
                    merchantId: this.$route.query.merchantId
                }
            });
        },
        selectAddress(e, item) {
            if (this.$refs.del.some(item => item.$el === e.target) ||
                this.$refs.edit.some(item => item.$el === e.target) ||
                !this.$route.query.mdseId
            ) {
                return;
            }
            this.setNowAddress(item);
            this.$router.go(-1);
        },
        phoneNumberShelter
    },
    components: {
        mintCellSwipe: CellSwipe
    }
};
</script>

<template>
    <div class="address-list-wrapper">
        <div class="address-list" v-if="list.length">
            <v-touch tag="div" class="address-item" v-for="item in sortList" @tap="selectAddress($event, item)" :options="{domEvents: true}">
                <div class="receiver">
                    <h4>{{item.receiver}}</h4>
                    <p>{{phoneNumberShelter(item.phone)}}</p>
                </div>
                <div class="address">{{item.province}}{{item.address}}</div>
                <div class="footer">
                    <div class="default" v-if="item.isDefault">默认收货地址</div>
                    <v-touch tag="div" ref="del" class="del" @tap="del($event, item)" :options="{domEvents: true}">
                        <i class="iconfont icon-artboard8"></i>
                        <span>删除</span>
                    </v-touch>
                    <v-touch tag="div" ref="edit" class="edit" @tap="edit($event, item)" :options="{domEvents: true}">
                        <i class="iconfont icon-edit"></i>
                        <span>编辑</span>
                    </v-touch>
                </div>
            </v-touch>
        </div>
        <div class="hint" v-else>您还没有添加地址信息.</div>
        <v-touch tag="div" class="add-address" @tap="goCreate">
            添加新地址
        </v-touch>
    </div>
</template>

<style lang="stylus" scoped>
h4, p
    margin 0
    padding 0
    font-weight normal
.address-list-wrapper
    padding-bottom 50px
.add-address
    position fixed
    left 0
    right 0
    bottom 0
    height 50px
    line-height 50px
    text-align center
    font-size 14px
    background #cf1f34
    color #fff
.address-item
    margin-top 15px
    padding-left 15px
    background #fff
    &:first-child
        margin 0
.receiver
    padding-right 15px
    font-size 0
    h4, p
        display inline-block
        line-height 34px
        font-size 16px
        color #181818
    h4
        margin-right 10px
.address
    padding 0 10px 5px 0
    line-height 1.5
    border-bottom 1px solid #eee
    font-size 12px
    color #999
.footer
    overflow hidden
    .default
        float left
        margin 9px 0
        padding 0 10px
        line-height 18px
        border 1px solid #cf1f34
        border-radius 10px
        font-size 12px
        color #cf1f34
    .edit,
    .del
        float right
        margin-right 5px
        padding 0 5px
        height 36px
        line-height 36px
        font-size 12px
        color #999
        span
            vertical-align middle
        i
            vertical-align middle
            font-size 20px
.hint
    margin-top 100px
    text-align center
    color #999
</style>
