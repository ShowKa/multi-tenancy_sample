<template>
  <div class="org-switch__form" action="">
    <label>Organization</label>
    <Selector v-model="organization" />
    <button :disabled="!selected" @click="select">
      Select organization.
    </button>
  </div>
</template>

<script setup>
import { computed, inject, ref } from 'vue'
import { useRouter } from 'vue-router'
// import SubmitButton from '@/components/input/SubmitButton.vue'
import Selector from './Selector.vue'
// injects
const $currentOrganization = inject('$currentOrganization')
const $router = useRouter()
// data
const current = ref($currentOrganization.get())
const organization = ref(current.value)
// computed
const selected = computed(() => (organization.value ? true : false))
const changed = computed(
  () => selected.value && organization.value != current.value
)
// method
const select = () => {
  // set current tenant and reload page.
  $currentOrganization.set(organization.value)
  $router.push({ path: '/' }).then(() => {
    if (changed.value) {
      $router.go()
    }
  })
}
</script>

<style lang="scss" scoped>
.org-switch {
  &__form {
    @extend %form;
  }
}
</style>
