<template>
  <select v-model="value">
    <option v-for="data in dataList" :key="data.value" :value="data.value">
      {{ data.name }}
    </option>
  </select>
</template>

<script setup>
import { toRefs, defineProps, computed, defineEmits } from 'vue'
// props
const props = defineProps({
  modelValue: {
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
const { modelValue, dataList } = toRefs(props)
// emit
const emit = defineEmits(['update:modelValue'])
// computed
const value = computed({
  get: () => modelValue.value,
  set: newValue => {
    emit('update:modelValue', newValue)
  }
})
</script>

<style lang="scss" scoped></style>
