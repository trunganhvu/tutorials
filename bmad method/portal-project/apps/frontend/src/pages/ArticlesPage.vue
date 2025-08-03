<template>
  <div class="articles-page">
    <div class="container">
      <div class="header">
        <h1>AI Knowledge Articles</h1>
        <div class="search-container">
          <InputText
            v-model="searchQuery"
            placeholder="Search articles..."
            class="search-input"
            @input="handleSearch"
          />
        </div>
      </div>
      
      <div v-if="loading" class="loading">
        <ProgressSpinner />
        <p>Loading articles...</p>
      </div>
      
      <div v-else-if="articles.length === 0" class="empty-state">
        <p>No articles found.</p>
      </div>
      
      <div v-else class="articles-grid">
        <Card
          v-for="article in articles"
          :key="article.id"
          class="article-card"
          @click="viewArticle(article.id)"
        >
          <template #title>
            <h3>{{ article.title }}</h3>
          </template>
          <template #content>
            <p class="article-excerpt">{{ article.content.substring(0, 150) }}...</p>
            <div class="article-meta">
              <span class="author">By {{ article.author?.username }}</span>
              <span class="date">{{ formatDate(article.createdAt) }}</span>
            </div>
          </template>
        </Card>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Card from 'primevue/card'
import InputText from 'primevue/inputtext'
import ProgressSpinner from 'primevue/progressspinner'

const router = useRouter()
const loading = ref(false)
const searchQuery = ref('')
const articles = ref([])

const handleSearch = () => {
  // TODO: Implement search functionality
  console.log('Searching for:', searchQuery.value)
}

const viewArticle = (id: string) => {
  router.push(`/articles/${id}`)
}

const formatDate = (date: string) => {
  return new Date(date).toLocaleDateString()
}

onMounted(() => {
  // TODO: Load articles from API
  articles.value = [
    {
      id: '1',
      title: 'Introduction to AI',
      content: 'This is a sample article about AI...',
      author: { username: 'Admin' },
      createdAt: new Date().toISOString()
    }
  ]
})
</script>

<style scoped>
.articles-page {
  padding: 2rem;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
}

.search-container {
  width: 300px;
}

.articles-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1.5rem;
}

.article-card {
  cursor: pointer;
  transition: transform 0.2s;
}

.article-card:hover {
  transform: translateY(-2px);
}

.article-excerpt {
  color: #666;
  margin-bottom: 1rem;
}

.article-meta {
  display: flex;
  justify-content: space-between;
  font-size: 0.9rem;
  color: #888;
}

.loading {
  text-align: center;
  padding: 2rem;
}

.empty-state {
  text-align: center;
  padding: 2rem;
  color: #666;
}
</style> 