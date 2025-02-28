<template>
  <div class="modal__wrapper">
    <div class="modal">
      <slot />
    </div>
  </div>
</template>

<script>
export default {
  name: "ModalRCMS"
}
</script>

<style scoped lang="less">
.modal {
  background: #EBEEF2;
  border-radius: 5px;

  &__wrapper {
    position: fixed;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    z-index: 100;

    display: flex;
    justify-content: center;
    align-items: center;
    background: rgba(0, 0, 0, 0.25);
  }
}
</style>
