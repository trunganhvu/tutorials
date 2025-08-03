<template>
  <div class="register-page">
    <div class="register-container">
      <Card class="register-card">
        <template #title>
          <h2>Create Account</h2>
        </template>
        <template #content>
          <form @submit.prevent="handleRegister" class="register-form">
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
              <label for="email">Email</label>
              <InputText
                id="email"
                v-model="form.email"
                type="email"
                :class="{ 'p-invalid': errors.email }"
                placeholder="Enter your email"
                required
              />
              <small v-if="errors.email" class="p-error">{{ errors.email }}</small>
            </div>
            
            <div class="field">
              <label for="password">Password</label>
              <Password
                id="password"
                v-model="form.password"
                :class="{ 'p-invalid': errors.password }"
                placeholder="Enter your password"
                required
              />
              <small v-if="errors.password" class="p-error">{{ errors.password }}</small>
            </div>
            
            <div class="field">
              <label for="confirmPassword">Confirm Password</label>
              <Password
                id="confirmPassword"
                v-model="form.confirmPassword"
                :class="{ 'p-invalid': errors.confirmPassword }"
                placeholder="Confirm your password"
                :feedback="false"
                toggleMask
                required
              />
              <small v-if="errors.confirmPassword" class="p-error">{{ errors.confirmPassword }}</small>
            </div>
            
            <div class="field">
              <Button
                type="submit"
                label="Register"
                :loading="loading"
                class="w-full"
              />
            </div>
            
            <div class="field text-center">
              <p>Already have an account? <router-link to="/login">Login here</router-link></p>
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
  email: '',
  password: '',
  confirmPassword: ''
})

const form = reactive({
  username: '',
  email: '',
  password: '',
  confirmPassword: ''
})

const validateForm = () => {
  let isValid = true
  
  // Reset errors
  Object.keys(errors).forEach(key => {
    errors[key as keyof typeof errors] = ''
  })
  
  // Username validation
  if (!form.username.trim()) {
    errors.username = 'Username is required'
    isValid = false
  } else if (form.username.length < 3) {
    errors.username = 'Username must be at least 3 characters'
    isValid = false
  }
  
  // Email validation
  if (!form.email.trim()) {
    errors.email = 'Email is required'
    isValid = false
  } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)) {
    errors.email = 'Please enter a valid email address'
    isValid = false
  }
  
  // Password validation
  if (!form.password) {
    errors.password = 'Password is required'
    isValid = false
  } else if (form.password.length < 6) {
    errors.password = 'Password must be at least 6 characters'
    isValid = false
  }
  
  // Confirm password validation
  if (!form.confirmPassword) {
    errors.confirmPassword = 'Please confirm your password'
    isValid = false
  } else if (form.password !== form.confirmPassword) {
    errors.confirmPassword = 'Passwords do not match'
    isValid = false
  }
  
  return isValid
}

const handleRegister = async () => {
  if (!validateForm()) {
    return
  }
  
  loading.value = true
  
  try {
    await authStore.register(form.username, form.email, form.password)
    toast.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Registration successful! Please login.',
      life: 3000
    })
    router.push('/login')
  } catch (error: any) {
    toast.add({
      severity: 'error',
      summary: 'Error',
      detail: error.message || 'Registration failed',
      life: 5000
    })
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.register-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.register-container {
  width: 100%;
  max-width: 400px;
}

.register-card {
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
}

.register-card :deep(.p-card-title) {
  text-align: center;
  color: #333;
  margin-bottom: 1rem;
}

.register-form {
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