<template>
  <div class="setting-top">
    <div class="setting-top__title">Setting and Members</div>
    <div>
      <label>organization</label>
      <div>{{ orgName }}</div>
      <a class="setting-top__link" @click="edit">edit</a>
    </div>
    <label>members</label>
    <MemberList />
    <a class="setting-top__link" @click="invite">inviete a member</a>
    <EditOrg ref="EditOrgView" />
    <Invite ref="InviteView" />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import Organization from '@/models/Organization'
import Invite from '@/views/invitation/Invite.vue'
import MemberList from './MemberList.vue'
import EditOrg from './EditOrg.vue'
// data
const orgName = ref('')
// reffer component
const EditOrgView = ref(null)
const InviteView = ref(null)
// method
const edit = () => {
  EditOrgView.value.open()
}
const invite = () => {
  InviteView.value.open()
}
// life cycle event
onMounted(async () => {
  const org = await Organization.getMine()
  orgName.value = org.displayName
})
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
