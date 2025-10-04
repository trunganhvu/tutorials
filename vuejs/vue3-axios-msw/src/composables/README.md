# API Best Practices Guide

## 📁 Cấu trúc thư mục

```
src/
├── services/
│   ├── api.js              # Axios instance, interceptors, error handling
│   └── makesService.js     # Specific API endpoints cho Makes
├── composables/
│   └── useApi.js           # Reusable composition API cho API calls
└── components/
    └── MakesList.vue       # Component sử dụng các services
```

## 🎯 Best Practices

### 1. **Tách biệt concerns**
- `api.js`: Cấu hình axios, interceptors, error handling chung
- `*Service.js`: Các API endpoints cụ thể theo domain/module
- `useApi.js`: Logic tái sử dụng cho state management
- Components: Chỉ focus vào UI và business logic

### 2. **Error Handling tập trung**
Tất cả errors được xử lý trong interceptor:
```javascript
// Không cần try-catch ở mỗi component
const { data, error } = useApi(makesService.getAll)
```

### 3. **Loading State tự động**
Composable tự động quản lý loading state:
```javascript
const { loading } = useApi(makesService.getAll)
// loading = true khi đang gọi API
// loading = false khi hoàn tất
```

### 4. **Reusable Logic**
Sử dụng composables thay vì duplicate code:
```javascript
// ❌ BAD - Duplicate code
const loading = ref(false)
const error = ref(null)
async function fetch() {
  loading.value = true
  try { ... } catch { ... } finally { loading.value = false }
}

// ✅ GOOD - Reusable composable
const { loading, error, execute } = useApi(apiFunc)
```

## 📝 Các cách sử dụng

### Cách 1: Sử dụng useApi (cơ bản)

```javascript
import { useApi } from '@/composables/useApi'
import makesService from '@/services/makesService'

const { data, loading, error, execute } = useApi(makesService.getAll)

// Gọi API khi cần
const handleClick = async () => {
  await execute()
}
```

### Cách 2: Sử dụng useApi với immediate

```javascript
const { data, loading, error } = useApi(
  makesService.getAll,
  { immediate: true } // Tự động gọi khi component mount
)
```

### Cách 3: Sử dụng useList (cho danh sách)

```javascript
import { useList } from '@/composables/useApi'

const { items, loading, error, refresh } = useList(
  makesService.getAll,
  { immediate: true }
)

// items sẽ tự động parse Results array
```

### Cách 4: Với callbacks

```javascript
const { execute } = useApi(makesService.create, {
  onSuccess: (data) => {
    console.log('Created:', data)
    // Redirect hoặc show notification
  },
  onError: (error) => {
    console.error('Failed:', error)
    // Show error notification
  }
})

await execute({ Make_Name: 'New Make' })
```

### Cách 5: Truyền parameters động

```javascript
const { data, execute } = useApi(makesService.getById)

// Truyền params khi gọi
await execute(123) // Get make with ID 123
```

## 🔧 Tạo Service mới

```javascript
// src/services/userService.js
import http from './api'

export const userService = {
  async getAll() {
    return await http.get('/users')
  },
  
  async getById(id) {
    return await http.get(`/users/${id}`)
  },
  
  async create(userData) {
    return await http.post('/users', userData)
  },
  
  async update(id, userData) {
    return await http.put(`/users/${id}`, userData)
  },
  
  async delete(id) {
    return await http.delete(`/users/${id}`)
  },
}

export default userService
```

## 🎨 Component Example

```vue
<template>
  <div>
    <button @click="refresh" :disabled="loading">
      {{ loading ? 'Loading...' : 'Reload' }}
    </button>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <ul v-else>
      <li v-for="item in items" :key="item.Make_ID">
        {{ item.Make_Name }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { useList } from '@/composables/useApi'
import makesService from '@/services/makesService'

const { items, loading, error, refresh } = useList(
  makesService.getAll,
  { immediate: true }
)
</script>
```

## 🚀 Advanced Features

### Custom Interceptors

```javascript
// src/services/api.js
apiClient.interceptors.request.use((config) => {
  // Add auth token
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    // Handle 401 - redirect to login
    if (error.response?.status === 401) {
      router.push('/login')
    }
    return Promise.reject(error)
  }
)
```

### Retry Logic

```javascript
import axios from 'axios'

const apiClient = axios.create({
  // ... config
})

// Thêm retry logic với axios-retry
import axiosRetry from 'axios-retry'

axiosRetry(apiClient, {
  retries: 3,
  retryDelay: axiosRetry.exponentialDelay,
  retryCondition: (error) => {
    return error.response?.status >= 500
  }
})
```

## 📚 Resources

- [Axios Documentation](https://axios-http.com/)
- [Vue Composition API](https://vuejs.org/guide/extras/composition-api-faq.html)
- [API Best Practices](https://github.com/public-apis/public-apis)

