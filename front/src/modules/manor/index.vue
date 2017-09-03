<script>
import { Swipe, SwipeItem, Indicator } from 'mint-ui';
import cell from 'components/cell';
import imgModal from './imgModal';
import textModal from './textModal';
import { mapGetters, mapActions, mapMutations } from 'vuex';
export default {
    async created() {
        Indicator.open();
        try {
            await this.getMerchantInfo(this.$route.query.merchantId);
        } catch (e) {
            Indicator.close();
            throw e;
        }
        Indicator.close();
    },
    computed: {
        ...mapGetters({
            info: 'merchantInfo',
            layoutInfo: 'merchantLayout'
        }),
        banner() {
            if (this.info.videoLink) {
                return this.info.chateauPics.reduce((arr, item) => {
                    arr.push(item);
                    return arr;
                }, [this.info.videoLink]);
            }
            return this.info.chateauPics;
        },
        title() {
            return this.layoutInfo
                .filter(item => item.id === 'name' || item.id === 'nameEn')
                .reduce((obj, item) => {
                    obj[item.id] = this.info[item.id];
                    return obj;
                }, {});
        },
        layout() {
            return this.layoutInfo.filter(item => item.id !== 'name' && item.id !== 'nameEn');
        },
        text() {
            return this.layout.filter(item => item.type === 'text');
        },
        group() {
            return this.layout.filter(item => item.type.search('btn-group') !== -1);
        }
    },
    methods: {
        ...mapActions({
            getMerchantInfo: 'getMerchantInfo'
        }),
        ...mapMutations({
            changeHint: 'CHANGE_ENV_HINT_INFO'
        }),
        showModal(item) {
            if (item.type === 'btn-group-img') {
                this.$refs.imgModal.set(item, this.info[item.id]);
                return;
            }
            this.$refs.textModal.set(item, this.info[item.id]);
        },
        appointment() {
            if (this.info.tourismLink) {
                window.location.href = this.info.tourismLink;
            } else {
                this.changeHint('暂未开通，敬请期待');
            }
        }
    },
    components: {
        mintSwipe: Swipe,
        mintSwipeItem: SwipeItem,
        imgModal,
        textModal,
        cell
    }
};
</script>

<template>
    <div class="manor" v-if="info.merchantId">
        <div class="manor-image">
            <mint-swipe :auto="info.videoLink ? 0 : 4000" :prevent="true" :stopPropagation="true">
                <mint-swipe-item
                    class="swipe-item"
                    v-for="(item, index) in banner"
                    :key="index"
                >
                    <iframe
                        v-if="info.videoLink && index === 0"
                        height="200"
                        width="100%"
                        :src="item"
                        frameborder="0"
                        allowfullscreen
                    ></iframe>
                    <div v-else class="img" :style="{'background': `url(${item}) center/cover no-repeat`}"></div>
                </mint-swipe-item>
            </mint-swipe>
        </div>
        <div>
            <div class="manor-description">
                <div class="manor-title">
                    <div class="title">
                        <h3 v-if="title.name">{{title.name}}</h3>
                        <p v-if="title.nameEn">{{title.nameEn}}</p>
                    </div>
                    <v-touch class="appointment-btn" @tap="appointment">马上预约</v-touch>
                </div>
                <ul class="manor-property">
                    <li v-for="item in text" v-if="info[item.id]">
                        <span class="label">{{item.label}}:</span>
                        <span class="text">{{info[item.id]}}</span>
                    </li>
                </ul>
            </div>
            <div>
                <cell
                    v-if="info[item.id] && info[item.id].length"
                    v-for="item in group"
                    :label="item.label"
                    is-link
                    @click-cell="showModal(item)"
                >
                </cell>
            </div>
        </div>
        <img-modal ref="imgModal"></img-modal>
        <text-modal ref="textModal"></text-modal>
    </div>
</template>

<style lang="stylus" scoped>
ul, li
    margin 0
    padding 0
    list-style none
p, h3
    margin 0
    padding 0
.manor
    &-image
        height 200px
        .img
            height 100%
    &-description
        padding 15px 0 15px 15px
        margin-bottom 15px
        background #fff
    &-title
        display flex
        padding 0 15px 15px 0
        text-align left
        align-items center
        h3
            font-size 18px
            color #333
        p
            color #666
        .title
            flex 1
        .appointment-btn
            width 80px
            height 24px
            border 1px solid #cf1f34
            border-radius 12px
            line-height 24px
            text-align center
            font-size 14px
            color #cf1f34
    &-property
        padding 15px 15px 0 0
        border-top 1px solid #eee
        font-size 14px
        color #444
        li
            display flex
            line-height 25px
        .label
            margin-right 10px
            white-space nowrap
.group-label
    font-size 14px
    color #333
</style>
