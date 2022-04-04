<template>
  <div class="menus">
    <Menu label="home" path="/home">
      <HomeIcon icon-size="26" />
    </Menu>
    <Menu label="setting" path="/setting" v-if="$can('read', 'settings')">
      <SettingIcon icon-size="26" />
    </Menu>
  </div>
</template>

<script setup>
import Menu from './Menu.vue'
import HomeIcon from '@/components/icon/HomeIcon.vue'
import SettingIcon from '@/components/icon/SettingIcon.vue'
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
