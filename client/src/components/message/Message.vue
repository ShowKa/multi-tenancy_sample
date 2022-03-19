<template>
  <transition name="fade">
    <div
      class="notification _flash-message has-text-weight-bold"
      :class="`is-${color}`"
      v-show="display"
    >
      <button class="delete" @click="hideMessage">x</button>
      {{ message }}
    </div>
  </transition>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { emitter, FLASH_EVENT } from './useMessage'
// data
const display = ref(false)
const message = ref('')
const color = ref('')
const timeoutId = ref(null)
// method
const hideMessage = () => {
  display.value = false
}
const setDelayedHide = () => {
  if (timeoutId.value) {
    clearTimeout(timeoutId.value)
  }
  timeoutId.value = setTimeout(hideMessage, 60000)
}
const showMessage = payload => {
  message.value = payload.message
  color.value = payload.color
  display.value = true
  setDelayedHide()
}
// life time vent
onMounted(() => {
  emitter.on(FLASH_EVENT, e => {
    if (!e) {
      return
    }
    showMessage(e)
  })
})
</script>

<style lang="scss" scoped>
._flash-message {
  position: relative;
  // top: 30px;
  width: 400px;
  margin: auto;
  left: 0;
  right: 0;
  transition: all 0.4s;
}
.notification {
  background-color: whitesmoke;
  border-radius: 4px;
  // position: relative;
  padding: 1.25rem 2.5rem 1.25rem 1.5rem;
}
.notification > .delete {
  // position
  right: 0.5rem;
  position: absolute;
  top: 0.5rem;
  // design
  user-select: none;
  appearance: none;
  background-color: rgba(10, 10, 10, 0.2);
  border: none;
  border-radius: 9999px;
  cursor: pointer;
  pointer-events: auto;
  display: inline-block;
  flex-grow: 0;
  flex-shrink: 0;
  font-size: 100%;
  font-weight: 900;
  line-height: 0;
  color: rgba(0, 0, 0, 0);
  height: 20px;
  max-height: 20px;
  max-width: 20px;
  min-height: 20px;
  min-width: 20px;
  outline: none;
  vertical-align: top;
  width: 20px;
  &:hover {
    background-color: rgba(230, 230, 230, 0.7);
    color: gray;
  }
}
.notification.is-success {
  background-color: #48c78e;
  color: #fff;
}
.notification.is-danger {
  background-color: #f14668;
  color: #fff;
}
.notification.is-warning {
  background-color: #ffe08a;
  color: rgba(0, 0, 0, 0.7);
}
.notification.is-info {
  background-color: #3e8ed0;
  color: #fff;
}
// fade
.fade-leave-active {
  // bottom: 30px;
  opacity: 1;
}
.fade-leave-to {
  // bottom: -30px;
  opacity: 0;
}
.fade-enter-active {
  // bottom: -30px;
  opacity: 0;
}
.fade-enter-to {
  // bottom: 30px;
  opacity: 1;
}
</style>
