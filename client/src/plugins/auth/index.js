import createAuth0Client from '@auth0/auth0-spa-js'
import { computed, reactive } from 'vue'

let client
const state = reactive({
  loading: true,
  isAuthenticated: false,
  user: {},
  popupOpen: false,
  error: null,
})

// Auth0 api

async function loginWithPopup() {
  state.popupOpen = true

  try {
    await client.loginWithPopup(0)
  } finally {
    state.popupOpen = false
  }

  state.user = await client.getUser()
  state.isAuthenticated = true
}

async function handleRedirectCallback() {
  state.loading = true

  try {
    await client.handleRedirectCallback()
    state.user = await client.getUser()
    state.isAuthenticated = true
  } catch (e) {
    state.error = e
  } finally {
    state.loading = false
  }
}

async function getUser() {
  return await client.getUser()
}

async function getUserRoles() {
  const user = await getUser()
  return user["http://mts.showka.com/roles"]
}

function loginWithRedirect(o) {
  return client.loginWithRedirect(o)
}

function getIdTokenClaims(o) {
  return client.getIdTokenClaims(o)
}

async function getIdToken(o) {
  const ret = await client.getIdTokenClaims(o)
  return ret.__raw
}

function getTokenSilently(o) {
  // access token
  return client.getTokenSilently(o)
}

async function reloadToken(o) {
  o = o || {}
  return await client.getTokenSilently({ ignoreCache: true, ...o })
}

function getTokenWithPopup(o) {
  return client.getTokenWithPopup(o)
}

function logout(o) {
  return client.logout(o)
}

async function canChangePassword(o) {
  const claims = await client.getIdTokenClaims(o)
  return claims.sub ? claims.sub.startsWith('auth0') : false
}

// setup plugin

const authPlugin = {
  isAuthenticated: computed(() => state.isAuthenticated),
  loading: computed(() => state.loading),
  user: computed(() => state.user),
  getUser,
  getUserRoles,
  getIdTokenClaims,
  getIdToken,
  getTokenSilently,
  reloadToken,
  getTokenWithPopup,
  handleRedirectCallback,
  loginWithRedirect,
  loginWithPopup,
  logout,
  canChangePassword,
}

export const useAuth = () => {
  return authPlugin
}

export const setupAuth = async (options, callbackRedirect) => {

  client = await createAuth0Client(options)

  try {
    // If the user is returning to the app after authentication
    if (
      window.location.search.includes('code=') &&
      window.location.search.includes('state=')
    ) {
      // handle the redirect and retrieve tokens
      const { appState } = await client.handleRedirectCallback()
      // Notify subscribers that the redirect callback has happened, passing the appState
      // (useful for retrieving any pre-authentication state)
      callbackRedirect(appState)
    }
  } catch (e) {
    state.error = e
  } finally {
    // Initialize our internal authentication state
    state.isAuthenticated = await client.isAuthenticated()
    state.user = await client.getUser()
    state.loading = false
  }

  return {
    install: (app) => {
      app.config.globalProperties.$auth = authPlugin
      app.provide('$auth', authPlugin)
    },
  }
}
