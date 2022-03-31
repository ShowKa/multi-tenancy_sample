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
    <EditOrg ref="EditOrgView" />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import Organization from '@/models/Organization'
import MemberList from './MemberList.vue'
import EditOrg from './EditOrg.vue'
// data
const orgName = ref('')
// reffer component
const EditOrgView = ref('')
// method
const edit = () => {
  EditOrgView.value.open()
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
