<script>
import { Popup, Cell } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
export default {
    props: {
        show: {
            type: Boolean
        }
    },
    data() {
        return {
            innerShow: false
        };
    },
    computed: {
        ...mapGetters({
            distribution: 'distribution'
        }),
        list() {
            return this.distribution.list;
        },
        selected() {
            return this.distribution.selected;
        },
        selectedItem() {
            return this.list.filter(item => item.id === this.selected)[0];
        }
    },
    methods: {
        ...mapMutations({
            setSelected: 'SET_DISTRIBUTION_SELECTED'
        }),
        checked(id) {
            this.setSelected(id);
            this.innerShow = false;
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
        mintCell: Cell
    }
};
</script>

<template>
    <mint-popup class="distribution-popup" position="bottom" v-model="innerShow">
        <div class="distribution-title">配送信息</div>
        <v-touch
            tag="div"
            class="distribution-item-container"
            v-for="item in list"
            @tap="checked(item.id)"
        >
            <mint-cell
                class="distribution-item"
                :title="item.text"
            >
                <i class="radio" :class="{checked: item.id === selected}"></i>
            </mint-cell>
        </v-touch>
    </mint-popup>
</template>

<style lang="stylus">
.distribution-popup
    width 100%
    padding-left 15px
    box-sizing border-box
    .distribution-title
        line-height 44px
        font-size 15px
        color #181818
    .mint-cell
        background-image none
    .mint-cell-wrapper
        padding-left 0
        background-image none
    .mint-cell-text
        font-size 14px
    .distribution-item
        border-top 1px solid #c0c0c0
    .radio
        position relative
        display inline-block
        width 16px
        height 16px
        border 1px solid #c0c0c0
        border-radius 9px
    .checked
        background #181818
        border 1px solid #181818
        &:before
            position absolute
            top 4px
            left 3px
            display inline-block
            content ''
            width 7px
            height 4px
            border-left 2px solid #fff
            border-bottom 2px solid #fff
            transform rotate(-45deg)
</style>
