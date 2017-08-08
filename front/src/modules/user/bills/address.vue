<script>
import { Indicator, Cell } from 'mint-ui';
import { mapGetters, mapActions } from 'vuex';
export default {
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            nowAddress: 'addressNowSelected'
        })
    },
    methods: {
        ...mapActions({
            getAddressList: 'getAddressListInfo'
        }),
        async init() {
            if (!this.nowAddress) {
                await this.getAddress();
            }
        },
        async getAddress() {
            Indicator.open();
            try {
                await this.getAddressList();
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        goAddressList() {
            this.$router.push({
                name: 'address',
                query: this.$route.query
            });
        }
    },
    components: {
        mintCell: Cell
    }
};
</script>

<template>
    <div class="bills-address">
        <v-touch tag="div" @tap="goAddressList">
            <mint-cell is-link>
                <div slot="title" class="address-cell">
                    <i class="iconfont icon-position icon-address"></i>
                    <div class="title" v-if="nowAddress">
                        <div class="name">
                            <h4>{{nowAddress.receiver}}</h4>
                            <p>{{nowAddress.phone}}</p>
                        </div>
                        <div class="address">{{nowAddress.province}}{{nowAddress.address}}</div>
                    </div>
                    <div v-else class="no-more">
                        请选择您的收货地址
                    </div>
                </div>
            </mint-cell>
        </v-touch>
    </div>
</template>

<style lang="stylus" scoped>
h4, p
    margin 0
    padding 0
    font-weight normal
.bills-address
    .mint-cell
        background-image none
.address-cell
    display flex
    flex-flow row nowrap
    .icon-address
        align-self center
        margin-right 15px
        font-size 20px
        color #999
    .name
        height 34px
        line-height 34px
        font-size 0
        color #181818
        h4, p
            display inline-block
            font-size 16px
        h4
            margin-right 10px
    .address
        margin-bottom 6px
        line-height 1.5
        font-size 12px
        color #999
    .no-more
        font-size 14px
        line-height 20px
        color #999
</style>
