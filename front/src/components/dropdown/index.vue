<script>
export default {
    props: {
        options: {
            type: Array,
            required: true
        },
        value: {
            type: [String, Number]
        },
        vertical: {
            type: String,
            default: 'bottom'
        },
        horizontal: {
            type: String
        },
        width: {
            type: String,
            default: 'auto'
        }
    },
    mounted() {
        const el = this.$slots.default[0].elm;
        el.appendChild(this.$refs['dropdown-list']);
        el.style.position = 'relative';
        el.style.zIndex = '1';
        const wrapper = this.$refs['dropdown-wrapper'];
        this._parent = wrapper.parentNode;
        this._parent.appendChild(this.$refs['dropdown-shade']);
        this._parent.replaceChild(el, wrapper);
    },
    computed: {
        right() {
            if (this.horizontal === 'right' ||
                this.horizontal !== 'right' &&
                this.horizontal !== 'left'
            ) {
                return 0;
            }
            return 'inital';
        },
        left() {
            if (this.horizontal === 'left' ||
                this.horizontal !== 'right' &&
                this.horizontal !== 'left'
            ) {
                return 0;
            }
            return 'inital';
        }
    },
    beforeDestory() {
        this._parent.removeChild(this.$slots.default[0].elm);
        this._parent.removeChild(this.$refs['dropdown-shade']);
    },
    methods: {
        changeSelected(val) {
            this.$emit('change', val);
        },
        close() {
            this.$emit('close');
        }
    }
};
</script>

<template>
    <div class="dropdown-wrapper" ref="dropdown-wrapper">
        <slot></slot>
        <ul
            v-show="options.length"
            ref="dropdown-list"
            class="dropdown-list"
            :style="{
                'right': right,
                'left': left,
                'top': vertical === 'bottom' ? 'calc(100% + 1px)' : '0',
                'width': width
            }"
        >
            <v-touch
                tag="li"
                v-for="option in options"
                :class="{'dropdown-selected': option.value === value}"
                @tap="changeSelected(option.value)"
            >
                {{option.text}}
            </v-touch>
        </ul>
        <div
            v-show="options.length"
            class="dropdown-shade"
            ref="dropdown-shade"
        >
            <v-touch tag="div" style="height: 100%;" @tap="close"></v-touch>
        </div>
    </div>
</template>

<style lang="stylus" scoped>
@import '../../common/mixin.styl';
ul,
li
    margin 0
    padding 0
    list-style none
.dropdown-shade
    position fixed
    top 0
    left 0
    right 0
    bottom 0
    background #000
    opacity .5
.dropdown-btn
    display inline-block
.dropdown-list
    position absolute
    max-height 300px
    border-bottom 1px solid #eee
    overflow hidden
    overflow-y auto
    text-align left
    background #fff
    &::-webkit-scrollbar-track-piece {
      background-color: rgba(0, 0, 0, 0);
      border-left: 1px solid rgba(0, 0, 0, 0);
    }
    &::-webkit-scrollbar {
      width: 5px;
      height: 13px;
      -webkit-border-radius: 5px;
      -moz-border-radius: 5px;
      border-radius: 5px;
    }
    &::-webkit-scrollbar-thumb {
      background-color: rgba(0, 0, 0, 0.5);
      background-clip: padding-box;
      -webkit-border-radius: 5px;
      -moz-border-radius: 5px;
      border-radius: 5px;
      min-height: 28px;
    }
    &::-webkit-scrollbar-thumb:hover {
      background-color: rgba(0, 0, 0, 0.5);
      -webkit-border-radius: 5px;
      -moz-border-radius: 5px;
      border-radius: 5px;
    }
    li
        margin-left 20px
        line-height 40px
        border-bottom 1px solid #eee
        font-size 12px
    li:last-child
        border-bottom 0
.dropdown-selected
    color #cf1f34
</style>
