import { mapMutations } from "vuex";
import HelpModal from "@/components/modals/HelpModal";

export const helpModal = {
  methods: {
    ...mapMutations('modals', [
      'openModal'
    ]),
    openHelpModal (params = {}) {
      this.openModal({
        component: HelpModal,
        params
      })
    }
  }
}
