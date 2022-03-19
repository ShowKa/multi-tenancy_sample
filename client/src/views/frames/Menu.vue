<template>
  <div class="menu" @click="onClick">
    <span class="menu__icon"><slot /></span>
    <span class="menu__label">{{ label }}</span>
  </div>
</template>

<script>
export default {
  props: {
    label: {
      type: String,
      required: true
    },
    path: {
      type: String,
      required: true
    }
  },
  methods: {
    onClick() {
      this.$router.push('/' + this.path.replace(/^\//g, ''))
    }
  }
}
</script>

<style lang="scss">
.menu {
  height: 40px;
  padding-top: 10px;
  &:hover {
    cursor: pointer;
  }
  &__icon {
    vertical-align: middle;
  }
  &__label {
    padding-left: 10px;
    vertical-align: middle;
    @extend %text_label;
    font-size: 12px;
    color: $color_on-primary;
    &:hover {
      font-weight: bold;
    }
  }
}
</style>
