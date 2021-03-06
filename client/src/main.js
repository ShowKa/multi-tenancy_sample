import { createApp, computed } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// persistent storage
import storage, { useStorage } from './plugins/storage'
// Api
import api from './plugins/api'
// Auth
import authConfig from '../auth_config.json'
import { setupAuth } from './plugins/auth'
import currentOrganizationManager from './plugins/auth/current-organization'
// ACL
import acl from './plugins/acl'

const app = createApp(App)
app.use(api)
app.use(store)
app.use(router)
app.use(acl, {
  roles: computed(() => store.state.auth.roles),
  acl: computed(() => store.state.auth.permissions)
})

// storage
app.use(storage, { keys: ['CurrentOrganization'] })
const $storage = useStorage()

// organization
app.use(currentOrganizationManager, {
  get: () => $storage.getCurrentOrganization(),
  set: (value) => $storage.setCurrentOrganization(value),
  remove: () => $storage.removeCurrentOrganization()
})

// auth option
const currentOrganization = $storage.getCurrentOrganization()
const authOption = {
  ...authConfig,
  ...(currentOrganization ? { organization: currentOrganization } : null),
  beforeLogin: () => {
    clearCache()
  }
}

// auth
setupAuth(authOption, callbackRedirect).then((auth) => {
  app.use(auth)
  app.mount('#app')
}).catch(e => {
  clearCache()
  throw e
})

// private function

function callbackRedirect(appState) {
  router.push(
    appState && appState.targetUrl
      ? appState.targetUrl
      : '/'
  );
}

function clearCache() {
  $storage.removeCurrentOrganization()
}

export { app }
