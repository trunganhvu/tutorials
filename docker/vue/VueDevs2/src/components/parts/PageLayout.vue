<template>
  <div>
    <div class="content">
      <slot />
    </div>
  </div>
</template>

<script>
export default {
  name: 'PageLayout',
  props: {
    loading: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style scoped lang="less">
.content {
  margin: 0 auto;
  padding: 20px 34px;
  min-height: 100vh;
  max-height: 100vh;
  overflow: auto;
  box-sizing: border-box;
  max-width: 1000px;
}
</style>
