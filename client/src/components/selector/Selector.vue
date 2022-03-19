<template>
  <select v-model="selectedValue">
    <option v-for="data in dataList" :key="data.value" :value="data.value">
      {{ data.name }}
    </option>
  </select>
</template>

<script setup>
import { toRefs, defineProps, computed, defineEmits } from 'vue'
// props
const props = defineProps({
  selected: {
    type: String,
    required: false,
    default: ''
  },
  dataList: {
    type: Array,
    required: true
  }
})
// data
const { selected, dataList } = toRefs(props)
// emit
const emit = defineEmits(['update:selected'])
// computed
const selectedValue = computed({
  get: () => selected.value,
  set: value => {
    emit('update:selected', value)
  }
})
</script>

<style lang="scss" scoped></style>
