import { createStore } from 'vuex'
import Organization from '@/models/Organization'

const organization = {
  namespaced: true,
  state: () => ({
    all: [],
    current: {}
  }),
  // get computed values
  getters: {
  },
  // modify state
  // commit mutations
  mutations: {
    all(state, all) {
      state.all = all
    },
    current(state, organization) {
      state.current = organization
    },
  },
  // modify with async method
  // dispatch actions
  actions: {
    async updateAll({ commit }) {
      const all = await Organization.getAll()
      commit('all', all);
    },
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
