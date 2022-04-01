<template>
  <Selector v-model="value" :data-list="dataList" />
</template>

<script setup>
import { computed, defineEmits, defineProps, toRefs } from 'vue'
import { useStore } from 'vuex'
import Selector from '@/components/input/Selector.vue'
// inject plugin
const $store = useStore()
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
// computed
const value = computed({
  get: () => modelValue.value,
  set: newValue => {
    emit('update:modelValue', newValue)
  }
})
const orgs = computed(() => $store.state.organization.all)
const dataList = computed(() =>
  orgs.value.map(o => {
    return {
      name: o.displayName,
      value: o.id
    }
  })
)
</script>

<style lang="scss" scoped></style>
