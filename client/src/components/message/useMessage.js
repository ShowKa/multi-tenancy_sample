import mitt from "mitt";

export const FLASH_EVENT = Symbol("flashMessage")

export const emitter = mitt();

export const showSuccess = (message => {
  emitter.emit(FLASH_EVENT, { message, color: "success" })
})

export const showError = (message => {
  emitter.emit(FLASH_EVENT, { message, color: "danger" })
})

export const showWarning = (message => {
  emitter.emit(FLASH_EVENT, { message, color: "warning" })
})

export const showInfo = (message => {
  emitter.emit(FLASH_EVENT, { message, color: "info" })
})
