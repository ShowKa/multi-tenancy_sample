<template>
  <div class="org-reg">
    <label>name</label>
    <input v-model="form.displayName" />
    <SubmitButton @click="register">new organization</SubmitButton>
  </div>
</template>

<script setup>
import { inject, reactive } from 'vue'
import Organization from '@/models/auth/Organization'
import SubmitButton from '@/components/input/SubmitButton.vue'
import { useRouter } from 'vue-router'
// inject plugin
const $current = inject('$currentOrganization')
const $router = useRouter()
// data
const form = reactive({
  displayName: ''
})
// method
const register = async () => {
  // resiter and reload
  const org = await Organization.post(form)
  $current.set(org.id)
  $router.go()
}
</script>

<style lang="scss" scoped>
.org-reg {
  @extend %form;
}
</style>
