<template>
  <Message class="app__message" />
  <HeaderFrame />
  <BodyFrame class="app__body" />
</template>

<script>
import HeaderFrame from '@/views/frames/HeaderFrame.vue'
import BodyFrame from '@/views/frames/BodyFrame.vue'
import Message from '@/components/message/Message.vue'
export default {
  name: 'App',
  components: { HeaderFrame, BodyFrame, Message }
}
</script>

<style lang="scss">
body {
  margin: 0;
}
#app {
  // setup
  font-family: $font-primary, $font-secondary, Avenir, Helvetica, Arial,
    sans-serif;
  // flex
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  .app__body {
    // flex
    flex: 1;
  }
  .app__message {
    position: absolute;
    top: 11px;
  }
}
</style>
