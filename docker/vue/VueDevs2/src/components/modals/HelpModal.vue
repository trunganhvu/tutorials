<template>
  <ModalComponent>
    <div>
      {{ params.title || 'HelpModal' }}
    </div>
    <button @click="() => $emit('close')">
      Close
    </button>
    <div>
      {{ params.message || 'HelpMessage' }}
    </div>
    <div>
      <button
        v-for="(btn, index) in params.buttons"
        :key="index"
        :type="btn.type || 'default'"
        @click="() => click(btn)"
      >
        {{ btn.text }}
      </button>
    </div>
  </ModalComponent>
</template>

<script>
import ModalComponent from "@/components/parts/Modal";

export default {
  name: "HelpModal",
  components: {
    ModalComponent
  },
  props: {
    params: Object
  },
  methods: {
    click (btn) {
      if (btn.click) {
        this.clickHandler(btn.click)
      }

      if (btn.afterClick) {
        this.clickHandler(btn.afterClick)
      }
    },
    clickHandler (click) {
      if (typeof click === 'string') {
        if (click === 'emitClose') {
          return this.$emit('close')
        }
      }

      return click()
    }
  }
}
</script>

<style scoped>

</style>
