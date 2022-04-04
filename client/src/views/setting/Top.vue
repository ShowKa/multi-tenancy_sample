<template>
  <div class="setting-top__title">Setting and Members</div>
  <div class="setting-top__form">
    <label>organization</label>
    <EditOrg />
    <label>members</label>
    <MemberList />
    <a
      class="setting-top__link"
      @click="invite"
      v-if="$can('update', 'settings')"
      >inviete a member</a
    >
    <Invite ref="InviteView" />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import Invite from '@/views/invitation/Invite.vue'
import MemberList from './MemberList.vue'
import EditOrg from './EditOrg.vue'
const InviteView = ref(null)
const invite = () => {
  InviteView.value.open()
}
</script>

<style lang="scss" scoped>
.setting-top {
  &__title {
    @extend %text_title;
  }
  &__form {
    @extend %form;
  }
  &__link {
    @extend %text_link;
  }
}
</style>
