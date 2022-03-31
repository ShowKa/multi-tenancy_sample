<template>
  <div class="setting-top">
    <div class="setting-top__title">Setting and Members</div>
    <div>
      <label>organization</label>
      <div>{{ orgName }}</div>
      <a class="setting-top__link" @click="goEdit = true">edit</a>
    </div>
    <label>members</label>
    <MemberList />
    <Modal v-if="goEdit">
      <template v-slot:header>
        edit organization
      </template>
      <template v-slot:body>
        <EditOrganization @done="onDone($event)" />
      </template>
      <template v-slot:footer>
        <a class="setting-top__link" @click="goEdit = false">cancel</a>
      </template>
    </Modal>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import Organization from '@/models/Organization'
import Modal from '@/components/modal/Modal.vue'
import EditOrganization from '@/views/organization/Edit.vue'
import MemberList from './MemberList.vue'
// data
const orgName = ref('')
const goEdit = ref(false)
// method
const onDone = org => {
  orgName.value = org.displayName
  goEdit.value = false
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
