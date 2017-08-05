<script>
import { Field, Switch, Indicator } from 'mint-ui';
import { mapGetters, mapMutations, mapActions } from 'vuex';
export default {
    created() {
        this.init();
    },
    computed: {
        ...mapGetters({
            layout: 'addressDetailLayout',
            info: 'addressDetailInfo'
        }),
        isModify() {
            return this.$route.name === 'addressModify';
        }
    },
    methods: {
        ...mapActions({
            saveAddress: 'saveAddressDetail',
            getAddressDetail: 'getAddressDetailInfo'
        }),
        ...mapMutations({
            initAddressDetail: 'INIT_ADDRESS_DETAIL_INFO',
            setAddressFormInfo: 'SET_ADDRESS_FORM_INFO',
            checkAddressDetail: 'CHECK_ADDRESS_DETAIL'
        }),
        async init() {
            if (!this.isModify) {
                this.initAddressDetail();
                return;
            }
            Indicator.open();
            try {
                await this.getAddressDetail(this.$route.query.addressId);
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
        },
        setValue(value, id) {
            this.setAddressFormInfo({id, value});
        },
        async save() {
            Indicator.open();
            try {
                await this.saveAddress(this.isModify);
            } catch (e) {
                Indicator.close();
                throw e;
            }
            Indicator.close();
            this.$router.go(-1);
        }
    },
    components: {
        mintField: Field,
        mintSwitch: Switch
    }
};
</script>

<template>
    <div class="address-detail-wrapper">
        <template v-for="item in layout">
            <mint-field
                v-if="item.id !== 'isDefault'"
                class="input"
                :label="item.label"
                :placeholder="item.placeholder"
                :type="item.type"
                :state="item.state"
                :rows="item.rows"
                :value="info[item.id]"
                @input="setValue($event, item.id)"
                @change="checkAddressDetail(item.id)"
            >
            </mint-field>
            <mint-field
                v-else
                class="input"
                :label="item.label"
            >
                <mint-switch :value="Boolean(info[item.id])" @input="setValue($event, item.id)"></mint-switch>
            </mint-field>
        </template>
        <v-touch tag="div" class="save" @tap="save">保存</v-touch>
    </div>
</template>

<style lang="stylus">
.input
    .mint-cell-wrapper
        font-size 14px
.save
    position fixed
    left 0
    right 0
    bottom 0
    height 40px
    line-height 40px
    text-align center
    font-size 14px
    background #cf1f34
    color #fff
</style>
