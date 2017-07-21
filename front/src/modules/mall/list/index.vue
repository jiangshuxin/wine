<script>
import listItem from './item';
import { mapGetters } from 'vuex';
export default {
    components: {
        listItem
    },
    computed: {
        ...mapGetters({
            list: 'mallCommodityList'
        })
    }
};
</script>

<template>
    <div class="list-wrapper">
        <listItem
            class="list-item"
            v-for="item in list"
            :item="item"
        ></listItem>
    </div>
</template>

<style lang="stylus" scoped>
.list-wrapper
    padding-left 15px
    border-bottom 1px solid #c0c0c0
    background #fff
.list-item
    border-bottom 1px solid #c0c0c0
    &:last-child
        border-bottom 0
</style>
