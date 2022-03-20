import { createApp } from 'vue'
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

const app = createApp(App)
app.use(api)
app.use(store)
app.use(router)

// storage
app.use(storage, { keys: ['CurrentOrganization'] })
const $storage = useStorage()

// organization
const currentOrganization = $storage.getCurrentOrganization()

// auth option
const authOption = {
  ...authConfig,
  ...(currentOrganization ? { organization: currentOrganization } : null)
}

// auth
setupAuth(authOption, callbackRedirect).then((auth) => {
  app.use(auth)
  app.mount('#app')
})

function callbackRedirect(appState) {
  router.push(
    appState && appState.targetUrl
      ? appState.targetUrl
      : '/'
  );
}

export { app }
