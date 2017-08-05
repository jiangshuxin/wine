<script>
import listItem from './item';
import InfiniteLoading from 'vue-infinite-loading';
import { Loadmore } from 'mint-ui';
import { mapGetters, mapMutations } from 'vuex';
export default {
    computed: {
        ...mapGetters({
            list: 'mallMdseList',
            pageInfo: 'mallMdsePageInfo'
        })
    },
    methods: {
        ...mapMutations({
            setPage: 'SET_MALL_MDSE_PAGE_INFO',
            initPageInfo: 'INIT_MALL_PAGE_INFO'
        }),
        async loadTop() {
            this.initPageInfo();
            await this.$parent.getList(true);
            this.$refs.infiniteLoading.$emit('$InfiniteLoading:reset');
            this.$refs.loadmore.onTopLoaded();
        },
        async loadMore() {
            const {pageNumber, totalPage} = this.pageInfo;
            if (+pageNumber < +totalPage) {
                this.setPage(pageNumber + 1);
                await this.$parent.getList();
                this.$refs.infiniteLoading.$emit('$InfiniteLoading:loaded');
                return;
            }
            this.$refs.infiniteLoading.$emit('$InfiniteLoading:complete');
        },
        addShopCart(info) {
            this.$emit('addShopCart', info);
        }
    },
    components: {
        mintLoadmore: Loadmore,
        listItem,
        InfiniteLoading
    }
};
</script>

<template>
    <mint-loadmore
        ref="loadmore"
        :top-method="loadTop"
        v-if="list.length"
    >
        <div class="list-wrapper">
            <list-item
                class="list-item"
                v-for="item in list"
                :item="item"
                :key="item.mdseId"
                @addShopCart="addShopCart"
            ></list-item>
        </div>
        <infinite-loading :on-infinite="loadMore" ref="infiniteLoading" spinner="waveDots" :distance="50">
            <span slot="no-more">
                暂无更多数据
            </span>
            <span slot="no-results">
                暂无更多数据
            </span>
        </infinite-loading>
    </mint-loadmore>
</template>

<style lang="stylus" scoped>
.list-wrapper
    padding-left 15px
    border-top 1px solid #c0c0c0
    border-bottom 1px solid #c0c0c0
    background #fff
.list-item
    border-bottom 1px solid #c0c0c0
    &:last-child
        border-bottom 0
</style>
