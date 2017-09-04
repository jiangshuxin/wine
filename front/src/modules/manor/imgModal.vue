<script>
import { Popup, Swipe, SwipeItem } from 'mint-ui';
export default {
    data() {
        return {
            show: false,
            images: []
        };
    },
    methods: {
        set(info, images) {
            if (images && images.length) {
                this.images = images;
                this.show = true;
            }
        }
    },
    components: {
        mintPopup: Popup,
        mintSwipe: Swipe,
        mintSwipeItem: SwipeItem
    },
    watch: {
        show() {
            if (!this.show) {
                this.images = [];
                document.body.style.overflow = 'auto';
            } else {
                document.body.style.overflow = 'hidden';
            }
        }
    }
};
</script>
<template>
    <mint-popup
        class="img-modal"
        v-model="show"
        position="right"
        close-on-click-modal="false"
    >
        <mint-swipe :auto="0" :prevent="true" :stopPropagation="true">
            <mint-swipe-item
                v-for="(item, index) in images"
                :key="index"
            >
                <!--
                   -<div class="img" :style="{'background': `url(${item}) center/cover no-repeat`}"></div>
                   -->
                <div class="swipe-item">
                    <img class="img" :src="item">
                </div>
            </mint-swipe-item>
        </mint-swipe>
    </mint-popup>
</template>
<style lang="stylus" scoped>
.img-modal
    top 50%
    bottom 0
    left 0
    right 0
    background #000
.swipe-item
    display flex
    height 100%
    align-items center
    justify-content center
    text-align center
.img
    max-width 100%
    max-height 100%
</style>
