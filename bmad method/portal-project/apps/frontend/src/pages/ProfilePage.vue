<template>
  <div class="profile-page">
    <div class="container">
      <Card class="profile-card">
        <template #title>
          <h2>User Profile</h2>
        </template>
        <template #content>
          <div class="profile-info">
            <div class="field">
              <label>Username</label>
              <p>{{ user?.username }}</p>
            </div>
            <div class="field">
              <label>Email</label>
              <p>{{ user?.email }}</p>
            </div>
            <div class="field">
              <label>Role</label>
              <Tag :value="user?.role" :severity="user?.role === 'ADMIN' ? 'danger' : 'info'" />
            </div>
            <div class="field">
              <label>Member Since</label>
              <p>{{ formatDate(user?.createdAt) }}</p>
            </div>
          </div>
          
          <div class="profile-actions">
            <Button
              label="Change Password"
              icon="pi pi-key"
              @click="showChangePassword = true"
            />
            <Button
              label="Logout"
              icon="pi pi-sign-out"
              severity="secondary"
              @click="logout"
            />
          </div>
        </template>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import Card from 'primevue/card'
import Button from 'primevue/button'
import Tag from 'primevue/tag'

const router = useRouter()
const authStore = useAuthStore()
const showChangePassword = ref(false)

const user = ref({
  username: 'admin',
  email: 'admin@company.com',
  role: 'ADMIN',
  createdAt: new Date().toISOString()
})

const logout = () => {
  authStore.logout()
  router.push('/login')
}

const formatDate = (date: string) => {
  return new Date(date).toLocaleDateString()
}
</script>

<style scoped>
.profile-page {
  padding: 2rem;
}

.container {
  max-width: 600px;
  margin: 0 auto;
}

.profile-card {
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
}

.profile-info {
  margin-bottom: 2rem;
}

.field {
  margin-bottom: 1.5rem;
}

.field label {
  font-weight: 600;
  color: #333;
  display: block;
  margin-bottom: 0.5rem;
}

.field p {
  margin: 0;
  color: #666;
}

.profile-actions {
  display: flex;
  gap: 1rem;
}
</style> 