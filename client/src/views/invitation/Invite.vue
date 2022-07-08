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
      <a class="invite__link" @click="show = false">cancel</a>
    </template>
  </Modal>
</template>

<script setup>
import { inject, onMounted, reactive, ref, defineExpose } from 'vue'
import Invitations from '@/models/auth/Invitations'
import Modal from '@/components/modal/Modal.vue'
import SubmitButton from '@/components/input/SubmitButton.vue'
import { showSuccess } from '@/components/message/useMessage'
// inject plugtin
const $auth = inject('$auth')
// data
const show = ref(false)
const form = reactive({
  inviterName: '',
  inviteeMailAddress: ''
})
// method
const register = async () => {
  await Invitations.post(form)
  show.value = false
  showSuccess('you have invited a new member.')
  form.inviteeMailAddress = ''
}
const open = () => {
  show.value = true
}
// life cycle event
onMounted(async () => {
  const user = await $auth.getUser()
  form.inviterName = user.name
})
// export
defineExpose({
  open
})
</script>

<style lang="scss" scoped>
.invite {
  @extend %form;
  &__link {
    @extend %text_link;
  }
}
</style>
