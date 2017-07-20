<script>
import dropdown from 'components/dropdown';
import { mapGetters, mapMutations } from 'vuex';
export default {
    data() {
        return {
            nowOptions: [],
            nowSelected: '',
            nowId: ''
        };
    },
    computed: {
        ...mapGetters({
            filterMap: 'mallFilterMap',
            selectedInfo: 'mallFilterSelectedInfo'
        })
    },
    methods: {
        ...mapMutations({
            changeSelectedInfo: 'CHANGE_MALL_FILTER_SELECTED'
        }),
        init() {
            this.nowOptions = [];
            this.nowSelected = '';
            this.nowId = '';
        },
        showDropDown(info) {
            this.nowId = info.id;
            this.nowOptions = this.filterMap[info.id];
            this.nowSelected = this.filterMap[info.id].filter(opt => opt.value === info.value)[0].value;
        },
        changeSelected(val) {
            this.changeSelectedInfo({id: this.nowId, value: val});
            this.init();
        }
    },
    components: {
        dropdown
    }
};
</script>

<template>
    <div class="head-filter">
        <dropdown
            :options="nowOptions"
            :value="nowSelected"
            @change="changeSelected"
            @close="init"
        >
            <ul class="filter-btn-group">
                <v-touch
                    tag="li"
                    v-for="item in selectedInfo"
                    @tap="showDropDown(item)"
                >
                    {{
                        item.value ?
                        filterMap[item.id].filter(option => option.value === item.value)[0].text :
                        item.text
                    }}
                    <i
                        class="iconfont icon-arrowbottom"
                        :class="{'is-selected': nowId === item.id}"
                    ></i>
                </v-touch>
            </ul>
        </dropdown>
    </div>
</template>

<style lang="stylus" scoped>
ul
    margin 0
    padding 0
    list-style none
.filter-btn-group
    display flex
    flex-flow row nowrap
    border-bottom 1px solid #9a9a9a
    background #fff
    li
        flex-grow 1
        line-height 40px
        text-align center
        font-size 16px
.icon-arrowbottom
    &:before
        transition all 0.3s ease
    color #c0c0c0
.is-selected
    color red
    &:before
        transform rotate(180deg)
</style>
