<template>
  <div class="body">
    <div class="body__menus">
      <MenuFrame />
    </div>
    <div class="body__view">
      <router-view />
    </div>
  </div>
</template>
<script>
import MenuFrame from './MenuFrame.vue'
export default { components: { MenuFrame } }
</script>
<style lang="scss">
.body {
  display: flex;
  &__menus {
    padding-right: 0;
    padding-left: 0;
  }
  &__view {
    flex: 1;
    padding-top: 11px;
    padding-left: 11px;
  }
}
</style>
