<template>
  <div v-for="member in dataList" :key="member.id">
    <span v-if="member.name != member.mailAddress">
      {{ member.name }}
    </span>
    <span> &lt;{{ member.mailAddress }}&gt; </span>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Members from '@/models/Members'
// data
const dataList = ref([])
// life cycle event
onMounted(async () => {
  const members = await Members.getAll()
  dataList.value = members
})
</script>

<style lang="scss" scoped></style>
