<template>
  <div class="admin-page">
    <div class="container">
      <div class="header">
        <h1>Admin Dashboard</h1>
        <Button
          label="Create New Article"
          icon="pi pi-plus"
          @click="showCreateDialog = true"
        />
      </div>
      
      <div class="content">
        <h2>Manage Articles</h2>
        <DataTable
          :value="articles"
          :loading="loading"
          paginator
          :rows="10"
          :rowsPerPageOptions="[5, 10, 20]"
        >
          <Column field="title" header="Title" sortable></Column>
          <Column field="author.username" header="Author" sortable></Column>
          <Column field="status" header="Status" sortable>
            <template #body="{ data }">
              <Tag :value="data.status" :severity="getStatusSeverity(data.status)" />
            </template>
          </Column>
          <Column field="createdAt" header="Created" sortable>
            <template #body="{ data }">
              {{ formatDate(data.createdAt) }}
            </template>
          </Column>
          <Column header="Actions">
            <template #body="{ data }">
              <Button
                icon="pi pi-pencil"
                class="p-button-text p-button-sm"
                @click="editArticle(data)"
              />
              <Button
                icon="pi pi-trash"
                class="p-button-text p-button-danger p-button-sm"
                @click="deleteArticle(data)"
              />
            </template>
          </Column>
        </DataTable>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import Button from 'primevue/button'
import Tag from 'primevue/tag'

const loading = ref(false)
const showCreateDialog = ref(false)
const articles = ref([])

const editArticle = (article: any) => {
  // TODO: Implement edit functionality
  console.log('Edit article:', article)
}

const deleteArticle = (article: any) => {
  // TODO: Implement delete functionality
  console.log('Delete article:', article)
}

const getStatusSeverity = (status: string) => {
  return status === 'PUBLISHED' ? 'success' : 'warning'
}

const formatDate = (date: string) => {
  return new Date(date).toLocaleDateString()
}

onMounted(() => {
  // TODO: Load articles from API
  articles.value = [
    {
      id: '1',
      title: 'Sample Article',
      author: { username: 'Admin' },
      status: 'PUBLISHED',
      createdAt: new Date().toISOString()
    }
  ]
})
</script>

<style scoped>
.admin-page {
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

.content {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
</style> 