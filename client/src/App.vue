<template>
  <Message class="app__message" />
  <HeaderFrame />
  <BodyFrame class="app__body" />
  <Modal v-if="isAuthenticated && !currentOrgIsSetup">
    <template v-slot:header><br /></template>
    <template v-slot:body>
      <Switch />
    </template>
    <template v-slot:footer><br /></template>
  </Modal>
</template>

<script setup>
import { ref, inject } from 'vue'
import Modal from '@/components/modal/Modal.vue'
import Message from '@/components/message/Message.vue'
import HeaderFrame from '@/views/frames/HeaderFrame.vue'
import BodyFrame from '@/views/frames/BodyFrame.vue'
import Switch from '@/views/organization/Switch.vue'
// inject plugin
const $current = inject('$currentOrganization')
const $auth = inject('$auth')
// data
const currentOrgIsSetup = ref($current.get() ? true : false)
// computed
const isAuthenticated = $auth.isAuthenticated
</script>

<style lang="scss">
body {
  margin: 0;
}
#app {
  // setup
  font-family: $font-primary, $font-secondary, Avenir, Helvetica, Arial,
    sans-serif;
  // flex
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  .app__body {
    // flex
    flex: 1;
  }
  .app__message {
    position: absolute;
    top: 11px;
  }
}
</style>
