<template>
  <Message class="app__message" />
  <HeaderFrame />
  <BodyFrame class="app__body" />
  <Modal v-if="goSwitch">
    <template v-slot:header>switch organization</template>
    <template v-slot:body>
      <Switch />
    </template>
    <template v-slot:footer><br /></template>
  </Modal>
  <Modal v-if="goRegister">
    <template v-slot:header>new organization</template>
    <template v-slot:body>
      <Register />
    </template>
    <template v-slot:footer><br /></template>
  </Modal>
</template>

<script setup>
import { ref, inject, onMounted } from 'vue'
import { useStore } from 'vuex'
import Organization from '@/models/Organization'
import Modal from '@/components/modal/Modal.vue'
import Message from '@/components/message/Message.vue'
import HeaderFrame from '@/views/frames/HeaderFrame.vue'
import BodyFrame from '@/views/frames/BodyFrame.vue'
import Switch from '@/views/organization/Switch.vue'
import Register from '@/views/organization/Register.vue'
// inject plugin
const $current = inject('$currentOrganization')
const $auth = inject('$auth')
const $store = useStore()
// data
const currentOrgIsSetup = ref($current.get() ? true : false)
// computed
const isAuthenticated = $auth.isAuthenticated
const goSwitch = ref(isAuthenticated.value && !currentOrgIsSetup.value)
const goRegister = ref(false)
// lefcycle event
onMounted(async () => {
  const orgs = await Organization.getAll()
  goRegister.value = orgs.length == 0
  $store.dispatch('organization/updateCurrent')
})
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
