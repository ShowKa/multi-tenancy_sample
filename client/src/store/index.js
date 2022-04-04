import { createStore } from 'vuex'
import { useAuth } from '@/plugins/auth'
import Organization from '@/models/Organization'
import Permissions from '@/models/Permissions'

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

const auth = {
  namespaced: true,
  state: () => ({
    roles: [],
    permissions: {}
  }),
  mutations: {
    roles(state, roles) {
      state.roles = roles
    },
    permissions(state, permissions) {
      state.permissions = permissions
    },
  },
  actions: {
    async updateRoles({ commit }) {
      const $auth = useAuth()
      const roles = await $auth.getUserRoles()
      commit('roles', roles);
    },
    async updatePermissions({ commit }) {
      const permissions = await Permissions.get()
      commit('permissions', permissions);
    }
  },
}

export default createStore({
  modules: {
    organization: organization,
    auth: auth
  }
})
