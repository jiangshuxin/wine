<script>
import headFilter from './filter';
import list from './list';
export default {
    created() {
        console.log('mall');
    },
    components: {
        headFilter,
        list
    }
};
</script>

<template>
    <div class="mall-wrapper">
        <head-filter></head-filter>
        <list></list>
    </div>
</template>

<style lang="stylus" scoped>
</style>
