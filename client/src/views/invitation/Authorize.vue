<template>
  <Modal>
    <template v-slot:header><br /></template>
    <template v-slot:body>
      Loading....
    </template>
    <template v-slot:footer><br /></template>
  </Modal>
</template>

<script setup>
import { onMounted } from '@vue/runtime-core'
import { inject } from 'vue'
import { useRoute } from 'vue-router'
import Modal from '@/components/modal/Modal.vue'
// inject plugin
const $auth = inject('$auth')
const $route = useRoute()
// life cycle event
onMounted(() => {
  $auth.loginWithRedirect({
    invitation: $route.query.invitation,
    organization: $route.query.organization,
    redirect_uri: window.location.origin
  })
})
</script>

<style lang="scss" scoped></style>
