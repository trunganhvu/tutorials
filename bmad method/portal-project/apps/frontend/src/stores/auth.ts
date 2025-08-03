import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export interface User {
  id: string
  username: string
  email: string
  role: 'ADMIN' | 'USER'
  createdAt: string
  updatedAt: string
}

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User | null>(null)
  const token = ref<string | null>(null)
  const isLoading = ref(false)

  const isAuthenticated = computed(() => !!token.value)
  const isAdmin = computed(() => user.value?.role === 'ADMIN')
  const isUser = computed(() => user.value?.role === 'USER')

  function setUser(userData: User | null) {
    user.value = userData
  }

  function setToken(tokenValue: string | null) {
    token.value = tokenValue
  }

  function setLoading(loading: boolean) {
    isLoading.value = loading
  }

  function logout() {
    user.value = null
    token.value = null
  }

  async function login(username: string, password: string) {
    setLoading(true)
    try {
      // Simulate an API call
      const response = await new Promise<{ token: string; user: User }>((resolve, reject) => {
        setTimeout(() => {
          if (username === 'admin' && password === 'admin123') {
            resolve({
              token: 'fake-jwt-token',
              user: {
                id: '1',
                username: 'admin',
                email: 'admin@company.com',
                role: 'ADMIN',
                createdAt: new Date().toISOString(),
                updatedAt: new Date().toISOString()
              }
            })
          } else {
            reject(new Error('Invalid username or password'))
          }
        }, 1000)
      })

      setToken(response.token)
      setUser(response.user)
    } catch (error) {
      throw error
    } finally {
      setLoading(false)
    }
  }

  return {
    user,
    token,
    isLoading,
    isAuthenticated,
    isAdmin,
    isUser,
    setUser,
    setToken,
    setLoading,
    logout,
    login
  }
}) 