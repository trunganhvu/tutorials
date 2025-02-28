<template>
  <div v-if="showModalContainer" class="modal-container">
    <component v-for="i in getModals" :key="i.hash" :is="i.component" :params="i.params" @close="() => removeModal(i.hash)" />
  </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';

export default {
  name: 'ModalContainer',
  computed: {
    ...mapGetters('modals', [
      'getModals'
    ]),
    showModalContainer () {
      return Object.keys(this.getModals).length
    }
  },
  methods: {
    ...mapMutations('modals', [
      'removeModal'
    ])
  }
}
</script>

<style scoped lang="less">
.modal-container {
  width: 0;
  height: 0;
}
</style>
