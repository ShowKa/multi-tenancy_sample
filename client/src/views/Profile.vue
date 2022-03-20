<template>
  <div class="profile__title">ログインユーザー情報</div>
  <div class="profile__form">
    <div>
      <label>メールアドレス</label>
      <span>{{ mailAddress }}</span>
    </div>
    <div>
      <label>氏名</label>
      <span>{{ familyName }} {{ givenName }}</span>
    </div>
    <div>
      <button @click="logout">ログアウト</button>
    </div>
  </div>
  <a v-if="canChangePassword" class="profile__change-pass">
    パスワードを変更する（未実装）
  </a>
</template>

<script setup>
import { onMounted, ref, inject } from 'vue'
// plugin
const $auth = inject('$auth')
// ref
const mailAddress = ref('')
const givenName = ref('')
const familyName = ref('')
const canChangePassword = ref(false)
// methods
const logout = () => {
  $auth.logout()
}
// life cycle event
onMounted(async () => {
  const user = await $auth.getUser()
  mailAddress.value = user.email
  givenName.value = user.given_name
  familyName.value = user.family_name
  canChangePassword.value = await $auth.canChangePassword()
})
</script>

<style lang="scss" scoped>
.profile {
  &__title {
    @extend %text_title;
  }
  &__form {
    @extend %form;
  }
  &__change-pass {
    @extend %text_link;
    &__ok {
      @extend %button_ok;
      margin-right: 27px;
    }
    &__cancel {
      @extend %button_cancel;
    }
  }
}
</style>
