import { createStore } from 'vuex'
import Organization from '@/models/Organization'

const organization = {
  namespaced: true,
  state: () => ({
    current: {}
  }),
  // get computed values
  getters: {
  },
  // modify state
  // commit mutations
  mutations: {
    current(state, organization) {
      state.current = organization
    },
  },
  // modify with async method
  // dispatch actions
  actions: {
    async updateCurrent({ commit }) {
      const mine = await Organization.getMine()
      commit('current', mine);
    }
  },
}

export default createStore({
  modules: {
    organization: organization
  }
})
