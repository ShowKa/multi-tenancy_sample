import { createRouter, createWebHistory } from 'vue-router'
import { watchEffect } from 'vue'
import { useAuth } from '@/plugins/auth'

const routeGuard = (to, from, next) => {
  const { isAuthenticated, loading, loginWithRedirect } = useAuth()
  const verify = () => {
    // If the user is authenticated, continue with the route
    if (isAuthenticated.value) {
      return next()
    }
    // Otherwise, log in
    loginWithRedirect({ appState: { targetUrl: to.fullPath } })
  }
  // If loading has already finished, check our auth state using `fn()`
  if (!loading.value) {
    return verify()
  }
  // Watch for the loading property to change before we check isAuthenticated
  watchEffect(() => {
    if (loading.value === false) {
      return verify()
    }
  })
}

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('../views/Home.vue'),
    beforeEnter: routeGuard
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    beforeEnter: routeGuard
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
    beforeEnter: routeGuard
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('../views/Profile.vue'),
    beforeEnter: routeGuard
  },
  {
    path: '/organization-switch',
    name: 'SwitchOrganization',
    component: () => import('../views/organization/Switch.vue'),
    beforeEnter: routeGuard
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
