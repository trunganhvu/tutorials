<template>
  <div class="not-found-page">
    <div class="container">
      <div class="content">
        <h1>404</h1>
        <h2>Page Not Found</h2>
        <p>The page you're looking for doesn't exist.</p>
        <Button
          label="Go Home"
          icon="pi pi-home"
          @click="goHome"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import Button from 'primevue/button'

const router = useRouter()

const goHome = () => {
  router.push('/')
}
</script>

<style scoped>
.not-found-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.container {
  text-align: center;
  color: white;
}

.content h1 {
  font-size: 6rem;
  margin: 0;
  font-weight: bold;
}

.content h2 {
  font-size: 2rem;
  margin: 1rem 0;
}

.content p {
  font-size: 1.2rem;
  margin-bottom: 2rem;
  opacity: 0.9;
}
</style> 