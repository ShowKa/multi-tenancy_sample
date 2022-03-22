<template>
  <Selector v-model="value" :data-list="dataList" />
</template>

<script setup>
import Selector from '@/components/input/Selector.vue'
import { computed, onMounted, ref, defineEmits, defineProps, toRefs } from 'vue'
import Organization from '@/models/Organization'
// props
const props = defineProps({
  modelValue: {
    type: String,
    required: false,
    default: ''
  }
})
// emit
const emit = defineEmits(['update:modelValue'])
// data
const { modelValue } = toRefs(props)
const dataList = ref([])
// computed
const value = computed({
  get: () => modelValue.value,
  set: newValue => {
    emit('update:modelValue', newValue)
  }
})
// life cycle
onMounted(async () => {
  const orgs = await Organization.getAll()
  orgs.forEach(o => {
    dataList.value.push({
      name: o.displayName,
      value: o.id
    })
  })
})
</script>

<style lang="scss" scoped></style>
