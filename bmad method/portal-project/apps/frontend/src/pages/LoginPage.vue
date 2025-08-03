<template>
  <div class="login-page">
    <div class="login-container">
      <Card class="login-card">
        <template #title>
          <h2>Login to AI Knowledge Portal</h2>
        </template>
        <template #content>
          <form @submit.prevent="handleLogin" class="login-form">
            <div class="field">
              <label for="username">Username</label>
              <InputText
                id="username"
                v-model="form.username"
                :class="{ 'p-invalid': errors.username }"
                placeholder="Enter your username"
                required
              />
              <small v-if="errors.username" class="p-error">{{ errors.username }}</small>
            </div>
            
            <div class="field">
              <label for="password">Password</label>
              <Password
                id="password"
                v-model="form.password"
                :class="{ 'p-invalid': errors.password }"
                placeholder="Enter your password"
                :feedback="false"
                toggleMask
                required
              />
              <small v-if="errors.password" class="p-error">{{ errors.password }}</small>
            </div>
            
            <div class="field">
              <Button
                type="submit"
                label="Login"
                :loading="loading"
                class="w-full"
              />
            </div>
            
            <div class="field text-center">
              <p>Don't have an account? <router-link to="/register">Register here</router-link></p>
            </div>
          </form>
        </template>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from 'primevue/usetoast'
import { useAuthStore } from '@/stores/auth'
import Card from 'primevue/card'
import InputText from 'primevue/inputtext'
import Password from 'primevue/password'
import Button from 'primevue/button'

const router = useRouter()
const toast = useToast()
const authStore = useAuthStore()

const loading = ref(false)
const errors = reactive({
  username: '',
  password: ''
})

const form = reactive({
  username: '',
  password: ''
})

const handleLogin = async () => {
  // Reset errors
  errors.username = ''
  errors.password = ''
  
  // Validate form
  if (!form.username.trim()) {
    errors.username = 'Username is required'
    return
  }
  
  if (!form.password) {
    errors.password = 'Password is required'
    return
  }
  
  loading.value = true
  
  try {
    await authStore.login(form.username, form.password)
    toast.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Login successful!',
      life: 3000
    })
    router.push('/')
  } catch (error: any) {
    toast.add({
      severity: 'error',
      summary: 'Error',
      detail: error.message || 'Login failed',
      life: 5000
    })
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-container {
  width: 100%;
  max-width: 400px;
}

.login-card {
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.login-card :deep(.p-card-title) {
  text-align: center;
  color: #333;
  margin-bottom: 1rem;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.field label {
  font-weight: 600;
  color: #333;
}

.w-full {
  width: 100%;
}

.text-center {
  text-align: center;
}

a {
  color: #667eea;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style> 