<template>
  <div class="header">
    <OrgAnchor class="header__org-anchor" />
    <img
      v-if="iconSource"
      class="header__icon"
      :src="iconSource"
      title="ユーザー情報"
      @click="openProfile"
    />
  </div>
</template>
<script setup>
import { ref, inject, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import OrgAnchor from '../organization/Anchor.vue'
// plugin
const $auth = inject('$auth')
const $router = useRouter()
// ref
const iconSource = ref(null)
// methods
const openProfile = () => {
  $router.push({ name: 'Profile' })
}
// life cycle event
onMounted(async () => {
  const claim = await $auth.getIdTokenClaims()
  iconSource.value = claim.picture
})
</script>

<style lang="scss">
.header {
  background-color: $color_background;
  box-shadow: 3px 3px 3px $color_shadow;
  height: $header_height;
  &__org-anchor {
    // display / size
    height: $header_height;
    line-height: $header_height;
    // position
    position: relative;
    left: 17px;
  }
  &__icon {
    // position
    position: absolute;
    right: 17px;
    top: 7px;
    // size
    height: $header_height * 0.7;
    border-radius: 17px;
    // effect
    cursor: pointer;
  }
}
</style>
