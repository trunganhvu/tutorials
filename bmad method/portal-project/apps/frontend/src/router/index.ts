import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/pages/HomePage.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/pages/LoginPage.vue'),
      meta: { requiresAuth: false }
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/pages/RegisterPage.vue'),
      meta: { requiresAuth: false }
    },
    {
      path: '/articles',
      name: 'articles',
      component: () => import('@/pages/ArticlesPage.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/articles/:id',
      name: 'article-detail',
      component: () => import('@/pages/ArticleDetailPage.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('@/pages/AdminPage.vue'),
      meta: { requiresAuth: true, requiresAdmin: true }
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('@/pages/ProfilePage.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: () => import('@/pages/NotFoundPage.vue')
    }
  ]
})

// Navigation guards
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  
  // Check if route requires authentication
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/login')
    return
  }
  
  // Check if route requires admin role
  if (to.meta.requiresAdmin && !authStore.isAdmin) {
    next('/')
    return
  }
  
  // Redirect authenticated users away from auth pages
  if (authStore.isAuthenticated && (to.name === 'login' || to.name === 'register')) {
    next('/')
    return
  }
  
  next()
})

export default router 