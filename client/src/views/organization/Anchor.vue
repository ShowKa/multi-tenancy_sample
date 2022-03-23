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
import { inject, onMounted, ref } from 'vue'
import Organization from '@/models/Organization'
import Icon from './SwitchIcon.vue'
// plugin
const $current = inject('$currentOrganization')
// data
const name = ref(null)
const switchable = ref(false)
// method
const goSwitchView = () => {}
// life cycle event
onMounted(async () => {
  const orgs = await Organization.getAll()
  const currentId = $current.get()
  name.value = orgs.find(it => it.id === currentId).displayName
  switchable.value = orgs.length > 1
})
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
