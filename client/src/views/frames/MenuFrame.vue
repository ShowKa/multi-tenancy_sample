<template>
  <div class="menus">
    <Menu label="ホーム" path="/home">
      <HomeIcon icon-size="26" />
    </Menu>
  </div>
</template>

<script setup>
import Menu from './Menu.vue'
import HomeIcon from '@/components/icon/HomeIcon.vue'
</script>

<style lang="scss">
.menus {
  height: 100%;
  width: 150px;
  background-color: $color_primary_variant;
  color: $color_on-primary;
  padding-left: 7px;
}
</style>
