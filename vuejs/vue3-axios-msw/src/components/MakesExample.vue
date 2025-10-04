<template>
  <div class="p-6 space-y-8">
    <h1 class="text-2xl font-bold mb-4">API Usage Examples</h1>

    <!-- Example 1: Basic useApi with manual trigger -->
    <section class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Example 1: Manual Fetch</h2>
      <PButton 
        label="Fetch Makes" 
        @click="fetchMakes" 
        :loading="loading1"
      />
      <div v-if="error1" class="text-red-500 mt-2">{{ error1 }}</div>
      <div v-if="data1" class="mt-2">
        <p class="text-sm text-gray-600">Count: {{ data1.Count }}</p>
        <p class="text-sm">First 3 items:</p>
        <ul class="list-disc list-inside">
          <li v-for="make in data1.Results?.slice(0, 3)" :key="make.Make_ID">
            {{ make.Make_Name }}
          </li>
        </ul>
      </div>
    </section>

    <!-- Example 2: useList with immediate load -->
    <section class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Example 2: Auto Load List</h2>
      <div v-if="loading2">⏳ Loading...</div>
      <div v-else-if="error2" class="text-red-500">{{ error2 }}</div>
      <div v-else>
        <p class="text-sm text-gray-600 mb-2">
          Loaded {{ items2.length }} makes
        </p>
        <PButton label="Refresh" @click="refresh2" size="small" />
        <ul class="list-disc list-inside mt-2 max-h-40 overflow-y-auto">
          <li v-for="make in items2.slice(0, 10)" :key="make.Make_ID">
            {{ make.Make_Name }} (ID: {{ make.Make_ID }})
          </li>
        </ul>
      </div>
    </section>

    <!-- Example 3: CRUD Operations -->
    <section class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Example 3: Create Operation</h2>
      <div class="flex gap-2 items-center">
        <InputText 
          v-model="newMakeName" 
          placeholder="Enter make name"
          class="flex-1"
        />
        <PButton 
          label="Create" 
          @click="createMake" 
          :loading="loading3"
        />
      </div>
      <div v-if="error3" class="text-red-500 mt-2">{{ error3 }}</div>
      <div v-if="createdMake" class="text-green-600 mt-2">
        ✅ Created: {{ createdMake.Make_Name }} (ID: {{ createdMake.Make_ID }})
      </div>
    </section>

    <!-- Example 4: With Callbacks -->
    <section class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Example 4: With Success/Error Callbacks</h2>
      <PButton 
        label="Fetch with Callbacks" 
        @click="execute4" 
        :loading="loading4"
      />
      <div class="mt-2">
        <p v-for="(log, idx) in logs" :key="idx" class="text-sm" :class="log.type === 'error' ? 'text-red-500' : 'text-gray-600'">
          {{ log.message }}
        </p>
      </div>
    </section>

    <!-- Example 5: Error Handling -->
    <section class="border p-4 rounded">
      <h2 class="text-xl font-semibold mb-2">Example 5: Error Handling</h2>
      <PButton 
        label="Trigger Error (Invalid Endpoint)" 
        @click="triggerError" 
        :loading="loading5"
        severity="danger"
      />
      <div v-if="error5" class="mt-2 p-2 bg-red-50 border border-red-200 rounded">
        <p class="text-red-700 font-semibold">Error:</p>
        <p class="text-red-600 text-sm">{{ error5 }}</p>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useApi, useList } from '../composables/useApi'
import makesService from '../services/makesService'
import http from '../services/api'

// Example 1: Basic useApi
const { data: data1, loading: loading1, error: error1, execute: execute1 } = useApi(makesService.getAll)
const fetchMakes = () => execute1()

// Example 2: useList with immediate
const { items: items2, loading: loading2, error: error2, refresh: refresh2 } = useList(
  makesService.getAll,
  { immediate: true }
)

// Example 3: Create operation
const newMakeName = ref('')
const createdMake = ref(null)
const { loading: loading3, error: error3, execute: execute3 } = useApi(makesService.create)

const createMake = async () => {
  if (!newMakeName.value.trim()) {
    error3.value = 'Vui lòng nhập tên'
    return
  }
  
  try {
    const result = await execute3({ Make_Name: newMakeName.value })
    createdMake.value = result
    newMakeName.value = ''
    
    // Refresh list after create
    refresh2()
  } catch (err) {
    console.error('Create failed:', err)
  }
}

// Example 4: With callbacks
const logs = ref([])
const { loading: loading4, execute: execute4 } = useApi(
  makesService.getAll,
  {
    onSuccess: (data) => {
      logs.value.push({
        type: 'success',
        message: `✅ Success! Loaded ${data.Count} makes at ${new Date().toLocaleTimeString()}`
      })
    },
    onError: (error) => {
      logs.value.push({
        type: 'error',
        message: `❌ Error: ${error.message} at ${new Date().toLocaleTimeString()}`
      })
    }
  }
)

// Example 5: Error handling
const { loading: loading5, error: error5, execute: execute5 } = useApi(
  () => http.get('/invalid-endpoint')
)
const triggerError = () => execute5()
</script>

<style scoped>
.space-y-8 > * + * {
  margin-top: 2rem;
}
</style>

