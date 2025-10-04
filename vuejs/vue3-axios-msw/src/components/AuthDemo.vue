<template>
  <div class="p-6 space-y-6">
    <h1 class="text-2xl font-bold mb-4">Authentication Demo</h1>

    <!-- Login Form -->
    <section class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Login</h2>
      <div class="space-y-2">
        <InputText 
          v-model="loginForm.username" 
          placeholder="Username"
          class="w-full"
        />
        <InputText 
          v-model="loginForm.password" 
          placeholder="Password"
          type="password"
          class="w-full"
        />
        <PButton 
          label="Login" 
          @click="handleLogin" 
          :loading="loginLoading"
          class="w-full"
        />
        <div v-if="loginError" class="text-red-500 text-sm">{{ loginError }}</div>
        <div v-if="loginSuccess" class="text-green-500 text-sm">
          ✅ Login successful! Token: {{ currentToken?.substring(0, 20) }}...
        </div>
      </div>
    </section>

    <!-- User Info -->
    <section v-if="currentUser" class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Current User</h2>
      <div class="bg-gray-50 p-3 rounded">
        <p><strong>Name:</strong> {{ currentUser.name }}</p>
        <p><strong>Username:</strong> {{ currentUser.username }}</p>
        <p><strong>Email:</strong> {{ currentUser.email }}</p>
        <p><strong>Role:</strong> {{ currentUser.role }}</p>
      </div>
      <PButton 
        label="Logout" 
        @click="handleLogout" 
        :loading="logoutLoading"
        severity="danger"
        class="mt-2"
      />
    </section>

    <!-- Token Validation -->
    <section v-if="currentToken" class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Token Validation</h2>
      <PButton 
        label="Validate Token" 
        @click="validateToken" 
        :loading="validateLoading"
      />
      <div v-if="validateError" class="text-red-500 text-sm mt-2">{{ validateError }}</div>
      <div v-if="validateSuccess" class="text-green-500 text-sm mt-2">
        ✅ Token is valid!
      </div>
    </section>

    <!-- Admin Section -->
    <section v-if="currentUser?.role === 'admin'" class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Admin Panel</h2>
      
      <!-- Get All Users -->
      <div class="mb-4">
        <PButton 
          label="Get All Users" 
          @click="getAllUsers" 
          :loading="usersLoading"
          class="mr-2"
        />
        <div v-if="usersError" class="text-red-500 text-sm mt-2">{{ usersError }}</div>
      </div>

      <!-- Users List -->
      <div v-if="usersList.length > 0" class="space-y-2">
        <h3 class="font-semibold">Users:</h3>
        <div v-for="user in usersList" :key="user.id" class="bg-gray-50 p-2 rounded">
          <p><strong>{{ user.name }}</strong> ({{ user.username }}) - {{ user.role }}</p>
          <p class="text-sm text-gray-600">{{ user.email }}</p>
        </div>
      </div>

      <!-- Create User Form -->
      <div class="mt-4">
        <h3 class="font-semibold mb-2">Create New User</h3>
        <div class="space-y-2">
          <InputText 
            v-model="newUser.username" 
            placeholder="Username"
            class="w-full"
          />
          <InputText 
            v-model="newUser.password" 
            placeholder="Password"
            type="password"
            class="w-full"
          />
          <InputText 
            v-model="newUser.email" 
            placeholder="Email"
            class="w-full"
          />
          <InputText 
            v-model="newUser.name" 
            placeholder="Display Name"
            class="w-full"
          />
          <select v-model="newUser.role" class="w-full p-2 border rounded">
            <option value="user">User</option>
            <option value="admin">Admin</option>
          </select>
          <PButton 
            label="Create User" 
            @click="createUser" 
            :loading="createLoading"
            class="w-full"
          />
          <div v-if="createError" class="text-red-500 text-sm">{{ createError }}</div>
          <div v-if="createSuccess" class="text-green-500 text-sm">
            ✅ User created successfully!
          </div>
        </div>
      </div>
    </section>

    <!-- Demo Credentials -->
    <section class="border p-4 rounded bg-blue-50">
      <h2 class="text-xl font-semibold mb-2">Demo Credentials</h2>
      <div class="space-y-1 text-sm">
        <p><strong>Admin:</strong> admin / admin123</p>
        <p><strong>User:</strong> user / user123</p>
        <p><strong>Demo:</strong> demo / demo123</p>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useApi } from '../composables/useApi'
import authService from '../services/authService'

// Form data
const loginForm = ref({
  username: '',
  password: ''
})

const newUser = ref({
  username: '',
  password: '',
  email: '',
  name: '',
  role: 'user'
})

// State
const currentUser = ref(null)
const currentToken = ref(null)
const usersList = ref([])

// Login
const { 
  loading: loginLoading, 
  error: loginError, 
  execute: executeLogin 
} = useApi(authService.login)

const loginSuccess = ref(false)

const handleLogin = async () => {
  try {
    const response = await executeLogin(loginForm.value)
    if (response.success) {
      currentUser.value = response.user
      currentToken.value = response.token
      loginSuccess.value = true
      loginError.value = null
      
      // Store in localStorage
      localStorage.setItem('auth_token', response.token)
      localStorage.setItem('auth_user', JSON.stringify(response.user))
    } else {
      loginError.value = response.message
      loginSuccess.value = false
    }
  } catch (err) {
    loginError.value = err.message
    loginSuccess.value = false
  }
}

// Logout
const { 
  loading: logoutLoading, 
  execute: executeLogout 
} = useApi(authService.logout)

const handleLogout = async () => {
  if (currentToken.value) {
    await executeLogout(currentToken.value)
  }
  
  // Clear state
  currentUser.value = null
  currentToken.value = null
  usersList.value = []
  
  // Clear localStorage
  localStorage.removeItem('auth_token')
  localStorage.removeItem('auth_user')
}

// Validate Token
const { 
  loading: validateLoading, 
  error: validateError, 
  execute: executeValidate 
} = useApi(authService.validateToken)

const validateSuccess = ref(false)

const validateToken = async () => {
  if (!currentToken.value) return
  
  try {
    const response = await executeValidate(currentToken.value)
    validateSuccess.value = response.success
    validateError.value = response.success ? null : response.message
  } catch (err) {
    validateError.value = err.message
    validateSuccess.value = false
  }
}

// Get All Users
const { 
  loading: usersLoading, 
  error: usersError, 
  execute: executeGetUsers 
} = useApi(authService.getAllUsers)

const getAllUsers = async () => {
  if (!currentToken.value) return
  
  try {
    const response = await executeGetUsers(currentToken.value)
    if (response.success) {
      usersList.value = response.users
      usersError.value = null
    } else {
      usersError.value = response.message
    }
  } catch (err) {
    usersError.value = err.message
  }
}

// Create User
const { 
  loading: createLoading, 
  error: createError, 
  execute: executeCreateUser 
} = useApi(authService.createUser)

const createSuccess = ref(false)

const createUser = async () => {
  if (!currentToken.value) return
  
  try {
    const response = await executeCreateUser(currentToken.value, newUser.value)
    if (response.success) {
      createSuccess.value = true
      createError.value = null
      
      // Clear form
      newUser.value = {
        username: '',
        password: '',
        email: '',
        name: '',
        role: 'user'
      }
      
      // Refresh users list
      await getAllUsers()
    } else {
      createError.value = response.message
      createSuccess.value = false
    }
  } catch (err) {
    createError.value = err.message
    createSuccess.value = false
  }
}

// Check for existing session on mount
const checkExistingSession = () => {
  const token = localStorage.getItem('auth_token')
  const user = localStorage.getItem('auth_user')
  
  if (token && user) {
    currentToken.value = token
    currentUser.value = JSON.parse(user)
  }
}

// Initialize
checkExistingSession()
</script>

<style scoped>
.space-y-6 > * + * {
  margin-top: 1.5rem;
}

.space-y-2 > * + * {
  margin-top: 0.5rem;
}
</style>
