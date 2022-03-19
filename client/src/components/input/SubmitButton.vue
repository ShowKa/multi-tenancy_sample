<template>
  <button type="submit" @click="emit('click', $event)" :disabled="isSubmitting">
    <slot />
  </button>
</template>

<script setup>
import { defineEmits, computed } from 'vue'
import { useStore } from 'vuex'
// plugin
const store = useStore()
// emit
const emit = defineEmits(['click'])
// computed
const isSubmitting = computed(() => store.state.isSubmitting)
</script>

<style lang="scss">
.submit-button {
  &__indicator {
    position: relative;
    top: 15px;
  }
}
</style>
