<template>
  <div class="page-container">
    <div class="container">
      <Card>
        <template #title>
          Welcome to AI Knowledge Portal
        </template>
        <template #content>
          <p>This is your central hub for AI knowledge, guides, and insights.</p>
          <p>Get started by exploring articles or creating new content.</p>
        </template>
        <template #footer>
          <Button label="View Articles" icon="pi pi-book" @click="navigateToArticles" />
          <Button v-if="isAdmin" label="Admin Panel" icon="pi pi-cog" severity="secondary" @click="navigateToAdmin" class="ml-2" />
        </template>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import Card from 'primevue/card'
import Button from 'primevue/button'

const router = useRouter()
const authStore = useAuthStore()

const isAdmin = computed(() => authStore.isAdmin)

const navigateToArticles = () => {
  router.push('/articles')
}

const navigateToAdmin = () => {
  router.push('/admin')
}
</script>

<style scoped>
.page-container {
  min-height: 100vh;
  padding: 2rem 0;
  background-color: var(--surface-color);
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
}

.ml-2 {
  margin-left: 0.5rem;
}
</style> 