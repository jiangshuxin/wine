<script>
import { Cell } from 'mint-ui';
import { mapGetters } from 'vuex';
import distributionPopup from './distributionPopup';
import invoicePopup from './invoicePopup';
export default {
    data() {
        return {
            showDistribution: false,
            showInvoice: false
        };
    },
    computed: {
        ...mapGetters({
            distribution: 'distribution',
            invoice: 'invoice'
        }),
        distributionItem() {
            const { list, selected } = this.distribution;
            return list.filter(item => item.id === selected)[0];
        },
        invoiceItem() {
            const { list, selected } = this.invoice;
            return list.filter(item => item.id === selected)[0];
        }
    },
    methods: {
        changeDistribution(e) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            this.showDistribution = true;
        },
        changeInvoice(e) {
            e.preventDefault();
            e.srcEvent.stopPropagation();
            this.showInvoice = true;
        }
    },
    components: {
        mintCell: Cell,
        distributionPopup,
        invoicePopup
    }
};
</script>

<template>
    <div class="other-wrapper">
        <v-touch tag="div" class="distribution" @tap="changeDistribution" :options="{domEvents: true}">
            <mint-cell title="配送信息" is-link :value="distributionItem.text + ' 包邮'"></mint-cell>
        </v-touch>
        <v-touch tag="div" class="invoice" @tap="changeInvoice" :options="{domEvents: true}">
            <mint-cell title="发票信息" is-link>
                <p class="invoice-info">{{invoiceItem.input || invoiceItem.text}}</p>
            </mint-cell>
        </v-touch>
        <distribution-popup :show="showDistribution" @close="() => showDistribution = false"></distribution-popup>
        <invoice-popup :show="showInvoice" @close="() => showInvoice = false"></invoice-popup>
    </div>
</template>

<style lang="stylus">
@import '../../../common/mixin.styl';
p
    margin 0
    padding 0
.other-wrapper
    background #fff
    .mint-cell,
    .mint-cell-wrapper
        padding-left 0
        background-image none
    .mint-cell-text,
    .mint-cell-value
        font-size 14px
    .distribution
        border-bottom 1px solid #c0c0c0
        .mint-cell-wrapper
            padding-left 15px
    .invoice
        .mint-cell-wrapper
            padding-left 15px
    .invoice-info
        max-width 100px
        ellipsis()
</style>
