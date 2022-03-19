<template>
  <div class="modal__mask">
    <div class="modal__container">
      <div class="modal__header">
        <slot name="header">
          default header
        </slot>
      </div>
      <div class="modal__body">
        <slot name="body">
          default body
        </slot>
      </div>
      <div class="modal__footer">
        <slot name="footer">
          default footer
          <button class="modal__default-button" @click="$emit('close')">
            OK
          </button>
        </slot>
      </div>
    </div>
  </div>
</template>
<script>
export default {}
</script>
<style lang="scss">
.modal__mask {
  // cover window
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  // color
  background-color: rgba(0, 0, 0, 0.5);
  transition: opacity 0.3s ease;
  // center inner contents
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal__container {
  // size
  overflow-x: auto;
  // position
  padding: 20px 30px;
  // design
  background-color: $color_background;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
}
.modal__header {
  @extend %text_caption;
  text-align: left;
  margin-top: 0;
}
.modal__body {
  // size
  max-height: 500px;
  overflow-x: auto;
  overflow-y: auto;
  // position
  margin: 20px 0;
}
.modal__default-button {
  float: right;
}
.modal__enter {
  opacity: 0;
}
.modal__leave-active {
  opacity: 0;
}
.modal__enter .modal__container,
.modal__leave-active .modal__container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
