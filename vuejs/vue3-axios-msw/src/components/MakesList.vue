<template>
    <div class="p-4">
      <div class="flex items-center justify-between mb-4">
        <h2 class="text-xl font-semibold">Makes (NHTSA VPIC)</h2>
        <div>
          <PButton label="Tải lại" @click="load" />
        </div>
      </div>
  
      <DataTable :value="makes" :paginator="true" :rows="10" tableStyle="min-width:40rem">
        <Column field="Make_ID" header="ID" />
        <Column field="Make_Name" header="Name" />
      </DataTable>
  
      <div v-if="loading" class="mt-3">Loading...</div>
      <div v-if="error" class="mt-3 text-red-600">{{ error }}</div>
    </div>
  </template>
  
<script setup>
import { computed } from 'vue'
import makesService from '../services/makesService'
import { useList } from '../composables/useApi'

// Sử dụng composable để quản lý state tự động
const { items, loading, error, refresh } = useList(
  makesService.getAll, 
  { 
    immediate: true,
    onSuccess: (data) => {
      console.log(`✅ Loaded ${data.Count} makes successfully`)
    },
    onError: (err) => {
      console.error('❌ Failed to load makes:', err)
    }
  }
)

// Alias cho dễ đọc
const makes = computed(() => items.value)

// Load function để reload data
const load = () => refresh()
</script>
  
  <style scoped>
  /* thêm nếu cần */
  </style>
  