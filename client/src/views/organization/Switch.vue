<template>
  <form class="org-switch__form">
    <label>Organization</label>
    <Selector v-model="organization" />
    <SubmitButton :disabled="!selected" @click="select">
      Select organization.
    </SubmitButton>
  </form>
</template>

<script setup>
import { computed, inject, ref } from 'vue'
import { useRouter } from 'vue-router'
import SubmitButton from '@/components/input/SubmitButton.vue'
import Selector from './Selector.vue'
// injects
const $currentOrganization = inject('$currentOrganization')
const $router = useRouter()
// data
const organization = ref('')
// computed
const selected = computed(() => (organization.value ? true : false))
// method
const select = () => {
  // set current tenant and reload page.
  $currentOrganization.set(organization.value)
  $router.push('/')
}
</script>

<style lang="scss" scoped>
.org-switch {
  &__form {
    @extend %form;
  }
}
</style>
