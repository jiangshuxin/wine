<script>
import headFilter from './filter';
export default {
    created() {
        console.log('mall');
    },
    components: {
        headFilter
    }
};
</script>

<template>
    <div class="mall-wrapper">
        <head-filter></head-filter>
    </div>
</template>

<style lang="stylus" scoped>
</style>
