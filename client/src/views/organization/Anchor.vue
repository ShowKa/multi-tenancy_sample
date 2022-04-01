<template>
  <div
    class="org-anchor"
    :class="{ 'org-anchor--switchable': switchable }"
    @click="switchable ? goSwitchView() : null"
  >
    <span class="org-anchor__label">{{ name }}</span>
    <Icon class="org-anchor__icon" v-if="switchable" />
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import Icon from './SwitchIcon.vue'
const $router = useRouter()
const $store = useStore()
// computed
const current = computed(() => $store.state.organization.current)
const orgs = computed(() => $store.state.organization.all)
const name = computed(() => (current.value ? current.value.displayName : ''))
const switchable = computed(() => orgs.value.length > 1)
// method
const goSwitchView = () => {
  $router.push({ name: 'SwitchOrganization' })
}
</script>

<style lang="scss" scoped>
.org-anchor {
  display: inline-block;
  opacity: 0.7;
  &--switchable {
    cursor: pointer;
    &:hover {
      opacity: 1;
    }
  }
  &__label {
    @extend %text_normal;
  }
  &__icon {
    // positioin
    position: relative;
    top: 3px;
    left: 7px;
  }
}
</style>
