<template>
  <div class="article-detail-page">
    <div class="container">
      <div v-if="loading" class="loading">
        <ProgressSpinner />
        <p>Loading article...</p>
      </div>
      
      <div v-else-if="article" class="article-content">
        <div class="article-header">
          <h1>{{ article.title }}</h1>
          <div class="article-meta">
            <span class="author">By {{ article.author?.username }}</span>
            <span class="date">{{ formatDate(article.createdAt) }}</span>
          </div>
        </div>
        
        <div class="article-body">
          <p>{{ article.content }}</p>
        </div>
        
        <div class="article-actions">
          <Button
            label="Back to Articles"
            icon="pi pi-arrow-left"
            @click="goBack"
          />
        </div>
      </div>
      
      <div v-else class="not-found">
        <h2>Article not found</h2>
        <p>The article you're looking for doesn't exist.</p>
        <Button
          label="Back to Articles"
          @click="goBack"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import Button from 'primevue/button'
import ProgressSpinner from 'primevue/progressspinner'

const route = useRoute()
const router = useRouter()
const loading = ref(false)
const article = ref(null)

const goBack = () => {
  router.push('/articles')
}

const formatDate = (date: string) => {
  return new Date(date).toLocaleDateString()
}

onMounted(() => {
  const articleId = route.params.id as string
  loading.value = true
  
  // TODO: Load article from API
  setTimeout(() => {
    article.value = {
      id: articleId,
      title: 'Sample Article',
      content: 'This is a sample article content...',
      author: { username: 'Admin' },
      createdAt: new Date().toISOString()
    }
    loading.value = false
  }, 1000)
})
</script>

<style scoped>
.article-detail-page {
  padding: 2rem;
}

.container {
  max-width: 800px;
  margin: 0 auto;
}

.article-header {
  margin-bottom: 2rem;
}

.article-header h1 {
  margin-bottom: 1rem;
}

.article-meta {
  display: flex;
  gap: 1rem;
  color: #666;
  font-size: 0.9rem;
}

.article-body {
  line-height: 1.6;
  margin-bottom: 2rem;
}

.article-actions {
  margin-top: 2rem;
}

.loading {
  text-align: center;
  padding: 2rem;
}

.not-found {
  text-align: center;
  padding: 2rem;
}
</style> 