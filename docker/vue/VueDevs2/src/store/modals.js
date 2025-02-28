export const randomInteger = (min, max) => {
  const rand = min + Math.random() * (max - min);
  return Math.round(rand);
}

export const genHash = (length = 32) => {
  let hash = "";
  const chars = "abcdefghijklmnopqrstuvwxyz0123456789";

  for (let i = 0; i < length; i++) {
    hash += chars[randomInteger(0, chars.length-1)]
  }

  return hash + Date.now();
}

export default {
  namespaced: true,
  state: {
    modals: {}
  },
  getters: {
    getModals: (state) => state.modals
  },
  mutations: {
    removeModal: (state, hash) => {
      delete state.modals[hash]
      state.modals = { ...state.modals }
    },
    openModal: (state, { component, params }) => {
      const hash = genHash()
      state.modals[hash] = {
        component,
        hash,
        params
      }
      state.modals = { ...state.modals }
    }
  }
}
