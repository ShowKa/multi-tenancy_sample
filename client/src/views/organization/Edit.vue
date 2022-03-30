<template>
  <div class="org-update">
    <label>name</label>
    <input v-model="form.displayName" />
    <SubmitButton @click="save">save</SubmitButton>
  </div>
</template>

<script setup>
import { inject, onMounted, reactive, defineEmits } from 'vue'
import Organization from '@/models/Organization'
import SubmitButton from '@/components/input/SubmitButton.vue'
import { showSuccess } from '@/components/message/useMessage'
// emit
const $emit = defineEmits(['done'])
// inject plugin
const $current = inject('$currentOrganization')
// data
const form = reactive({
  displayName: ''
})
// method
const save = async () => {
  // resiter and reload
  const org = await Organization.patch(form)
  showSuccess('organization updated')
  $emit('done', org)
}
// life cycle event
onMounted(async () => {
  const current = $current.get()
  const org = await Organization.get(current)
  form.displayName = org.displayName
})
</script>

<style lang="scss" scoped>
.org-update {
  @extend %form;
}
</style>
