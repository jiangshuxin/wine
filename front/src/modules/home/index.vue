<script>
import { Header } from 'mint-ui';
export default {
    created() {
        console.log('home');
    },
    components: {
        mintHeader: Header
    }
};
</script>

<template>
    <div class="home">
        <mint-header fixed title="财富酒庄"></mint-header>
    </div>
</template>

<style lang="stylus" scoped>
</style>
