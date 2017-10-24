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
            layout: 'merchantLayout'
        }),
        banner() {
            if (this.info.videoLink) {
                return [this.info.videoLink];
            }
            return this.info.chateauPics;
        },
        title() {
            return this.layout.title
                .reduce((obj, item) => {
                    obj[item.id] = item;
                    return obj;
                }, {});
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
            <mint-swipe :auto="info.videoLink ? 0 : 4000" :prevent="true" :stopPropagation="true" :showIndicators="info.videoLink ? false : true">
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
                        <h3>{{info[title.name.id]}}</h3>
                        <p>{{info[title.nameEn.id]}}</p>
                    </div>
                    <v-touch class="appointment-btn" @tap="appointment">马上预约</v-touch>
                </div>
            </div>
            <div class="manor-property">
                <h4>{{layout.info.title}}</h4>
                <dl v-for="item in layout.info.node" v-if="info[item.id]">
                    <dt class="label">{{item.label}}</dt>
                    <dd class="text">{{info[item.id]}}</dd>
                </dl>
            </div>
            <div class="manor-pics">
                <h4>{{layout.introduce.title}}</h4>
                <cell
                    class="manor-pics-cell"
                    v-if="info[item.id] && info[item.id].length"
                    v-for="item in layout.introduce.node"
                    :label="item.label"
                    is-link
                    @click-cell="showModal(item)"
                >
                </cell>
            </div>
            <div class="manor-pics">
                <h4>{{layout.certificate.title}}</h4>
                <cell
                    class="manor-pics-cell"
                    v-if="info[item.id] && info[item.id].length"
                    v-for="item in layout.certificate.node"
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
        margin-bottom 15px
        padding 15px 0 15px 15px
        background #fff
    &-title
        display flex
        padding 0 15px 0 0
        text-align left
        align-items center
        h3
            font-size 18px
            color #333
        p
            font-size 14px
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
        font-size 14px
        color #333
        background #fff
        h4
            position relative
            margin 0
            padding-left 10px
            line-height 50px
            font-weight bold
            &:before
                content ''
                position absolute
                top 50%
                left 0
                margin-top -10px
                width 6px
                height 20px
                background #cf1f34
        dl
            display flex
            margin 0
        dt
            flex 1
            border-top 1px solid #d9d9d9
            border-right 1px solid #d9d9d9
            padding 0 10px
            line-height 35px
            white-space nowrap
            text-align left
        dd
            flex 2
            margin 0
            border-top 1px solid #d9d9d9
            padding 0 10px
            line-height 35px
            text-align left
    &-pics
        margin-top 15px
        font-size 14px
        color #333
        background #fff
        h4
            position relative
            margin 0
            border-bottom 1px solid #d9d9d9
            padding-left 10px
            line-height 50px
            font-weight bold
            z-index 1
            &:before
                content ''
                position absolute
                top 50%
                left 0
                margin-top -10px
                width 6px
                height 20px
                background #cf1f34
        &-cell
            margin-top -1px
.group-label
    font-size 14px
    color #333
</style>
