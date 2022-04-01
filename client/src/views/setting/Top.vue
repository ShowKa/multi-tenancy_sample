<template>
  <div class="setting-top">
    <div class="setting-top__title">Setting and Members</div>
    <div>
      <label>organization</label>
      <div>{{ organization.displayName }}</div>
      <a class="setting-top__link" @click="edit">edit</a>
    </div>
    <label>members</label>
    <MemberList />
    <a class="setting-top__link" @click="invite">inviete a member</a>
    <!-- popup modal window -->
    <EditOrg ref="EditOrgView" />
    <Invite ref="InviteView" />
  </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import Invite from '@/views/invitation/Invite.vue'
import MemberList from './MemberList.vue'
import EditOrg from './EditOrg.vue'
// inject plugin
const $store = useStore()
// ref component
const EditOrgView = ref(null)
const InviteView = ref(null)
// computed
const organization = computed(() => $store.state.organization.current)
// method
const edit = () => {
  EditOrgView.value.open()
}
const invite = () => {
  InviteView.value.open()
}
</script>

<style lang="scss" scoped>
.setting-top {
  @extend %form;
  &__title {
    @extend %text_title;
  }
  &__link {
    @extend %text_link;
  }
}
</style>
