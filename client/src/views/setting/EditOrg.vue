<template>
  <Modal v-if="show" class="org-modal">
    <template v-slot:header>
      edit organization
    </template>
    <template v-slot:body>
      <EditOrganization @done="onDone" />
    </template>
    <template v-slot:footer>
      <a class="org-modal__link" @click="show = false">cancel</a>
    </template>
  </Modal>
</template>

<script setup>
import { defineExpose, ref } from 'vue'
import Modal from '@/components/modal/Modal.vue'
import EditOrganization from '@/views/organization/Edit.vue'
// data
const show = ref(false)
// methods
const onDone = () => {
  show.value = false
}
const open = () => {
  show.value = true
}
// expose
defineExpose({
  open
})
</script>

<style lang="scss" scoped>
.org-modal {
  &__link {
    @extend %text_link;
  }
}
</style>
