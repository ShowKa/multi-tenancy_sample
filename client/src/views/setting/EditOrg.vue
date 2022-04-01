<template>
  <div class="setting-org__form">
    <div>{{ organization.displayName }}</div>
    <a class="setting-org__form__link" @click="show = true">edit</a>
  </div>
  <Modal v-if="show" class="setting-org__modal">
    <template v-slot:header>
      edit organization
    </template>
    <template v-slot:body>
      <EditOrganization @done="onDone" />
    </template>
    <template v-slot:footer>
      <a class="setting-org__modal__link" @click="show = false">cancel</a>
    </template>
  </Modal>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useStore } from 'vuex'
import Modal from '@/components/modal/Modal.vue'
import EditOrganization from '@/views/organization/Edit.vue'
// inject plugin
const $store = useStore()
// data
const show = ref(false)
// computed
const organization = computed(() => $store.state.organization.current)
// method
const onDone = () => {
  show.value = false
}
</script>

<style lang="scss" scoped>
.setting-org {
  &__form {
    @extend %form;
    &__link {
      @extend %text_link;
    }
  }
  &__modal {
    &__link {
      @extend %text_link;
    }
  }
}
</style>
