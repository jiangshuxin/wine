<script>
import { Popup, Field } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
export default {
    created() {
        const { list, selected } = this.invoice;
        this.selected = Object.assign({}, this.selected, list.filter(item => item.id === selected)[0]);
    },
    props: {
        show: {
            type: Boolean
        }
    },
    data() {
        return {
            innerShow: false,
            selected: {
                id: -1,
                text: '',
                input: ''
            }
        };
    },
    computed: {
        ...mapGetters({
            invoice: 'invoice'
        })
    },
    methods: {
        ...mapMutations({
            setSelected: 'SET_INVOICE_SELECTED'
        }),
        changeItem(id) {
            this.selected.id = id;
        },
        submit() {
            this.setSelected(this.selected);
            this.$emit('close');
        }
    },
    watch: {
        show() {
            this.innerShow = this.show;
        },
        innerShow() {
            if (!this.innerShow) {
                this.$emit('close');
            }
        }
    },
    components: {
        mintPopup: Popup,
        mintField: Field
    }
};
</script>

<template>
    <mint-popup class="invoice-popup" position="bottom" v-model="innerShow">
        <div class="invoice-content">
            <div class="invoice-title">配送信息</div>
            <ul class="invoice-labels">
                <v-touch
                    tag="li"
                    class="invoice-label"
                    :class="{'selected': item.id === selected.id}"
                    v-for="item in invoice.list"
                    :key="item.id"
                    @tap="changeItem(item.id)"
                >
                    {{item.text}}
                </v-touch>
            </ul>
            <mint-field v-if="selected.id === 3"
                placeholder="请输入发票抬头"
                type="textarea"
                rows="4"
                v-model="selected.input"
            ></mint-field>
        </div>
    </mint-popup>
</template>

<style lang="stylus">
ul, li
    margin 0
    padding 0
    list-style none
.invoice-popup
    width 100%
    box-sizing border-box
    .invoice-content
        padding-left 15px
    .invoice-title
        line-height 44px
        font-size 15px
        color #181818
    .invoice-labels
        margin-bottom 15px
        font-size 0
    .invoice-label
        display inline-block
        margin-right 15px
        padding 0 15px
        line-height 20px
        border 1px solid #999
        border-radius 10px
        font-size 13px
        color #999
    .selected
        border 1px solid #181818
        color #181818
    .submit
        height 45px
        line-height 45px
        text-align center
        font-size 16px
        background #cf1f34
        color #fff
</style>
