<template>
  <Modal v-if="show">
    <template v-slot:header>
      Invite new member
    </template>
    <template v-slot:body>
      <div class="invite">
        <label>inviter</label>
        <input v-model="form.inviterName" placeholder="your name or mail" />
        <label>new member's mail</label>
        <input v-model="form.inviteeMailAddress" />
        <SubmitButton @click="register">invite</SubmitButton>
      </div>
    </template>
    <template v-slot:footer>
      <button @click="show = false">cancel</button>
    </template>
  </Modal>
</template>

<script setup>
import { inject, onMounted, reactive, ref } from 'vue'
import Invitations from '@/models/Invitations'
import Modal from '@/components/modal/Modal.vue'
import SubmitButton from '@/components/input/SubmitButton.vue'
import { showSuccess } from '@/components/message/useMessage'
// inject plugtin
const $auth = inject('$auth')
// data
const show = ref(true)
const form = reactive({
  inviterName: '',
  inviteeMailAddress: ''
})
// method
const register = async () => {
  await Invitations.post(form)
  show.value = false
  showSuccess('you have invited a new member.')
}
// life cycle event
onMounted(async () => {
  const user = await $auth.getUser()
  form.inviterName = user.name
})
</script>

<style lang="scss" scoped>
.invite {
  @extend %form;
}
</style>
