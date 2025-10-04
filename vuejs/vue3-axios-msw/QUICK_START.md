# 🚀 Quick Start Guide - API Best Practices

## 📦 Files Created

```
✅ src/services/api.js              # Axios config + interceptors
✅ src/services/makesService.js     # Makes API endpoints
✅ src/composables/useApi.js        # Reusable composables
✅ src/components/MakesExample.vue  # 5 usage examples
✅ .env.local                       # Local environment
✅ .env.development                 # Dev environment
```

## ⚡ Quick Examples

### 1️⃣ Basic Usage - Auto Load List
```vue
<script setup>
import { useList } from '@/composables/useApi'
import makesService from '@/services/makesService'

const { items, loading, error, refresh } = useList(
  makesService.getAll,
  { immediate: true }
)
</script>

<template>
  <div v-if="loading">Loading...</div>
  <div v-else-if="error">{{ error }}</div>
  <ul v-else>
    <li v-for="item in items" :key="item.Make_ID">
      {{ item.Make_Name }}
    </li>
  </ul>
</template>
```

### 2️⃣ Manual Trigger
```vue
<script setup>
import { useApi } from '@/composables/useApi'
import makesService from '@/services/makesService'

const { data, loading, execute } = useApi(makesService.getAll)
</script>

<template>
  <button @click="execute" :disabled="loading">
    Load Data
  </button>
</template>
```

### 3️⃣ Create/Update/Delete
```vue
<script setup>
import { useApi } from '@/composables/useApi'
import makesService from '@/services/makesService'

const { execute: createMake } = useApi(makesService.create)
const { execute: updateMake } = useApi(makesService.update)
const { execute: deleteMake } = useApi(makesService.delete)

// Usage
await createMake({ Make_Name: 'Toyota' })
await updateMake(1, { Make_Name: 'Honda' })
await deleteMake(1)
</script>
```

## 🎯 Tạo Service Mới

```javascript
// src/services/productService.js
import http from './api'

export const productService = {
  async getAll(params = {}) {
    return await http.get('/products', { params })
  },
  
  async getById(id) {
    return await http.get(`/products/${id}`)
  },
  
  async create(data) {
    return await http.post('/products', data)
  },
  
  async update(id, data) {
    return await http.put(`/products/${id}`, data)
  },
  
  async delete(id) {
    return await http.delete(`/products/${id}`)
  },
}

export default productService
```

## 🌐 Environment Variables

```env
# .env.local
VITE_API_BASE=https://your-api.com/api

# .env.development
VITE_API_BASE=http://localhost:3000/api

# .env.production
VITE_API_BASE=https://production-api.com/api
```

Access trong code:
```javascript
const apiBase = import.meta.env.VITE_API_BASE
```

## 🎨 See It In Action

1. Run project: `npm run dev`
2. Navigate to:
   - `/makes` - Production example
   - `/examples` - 5 interactive examples

## 📚 Full Documentation

- **Detailed Guide:** `src/composables/README.md`
- **Architecture:** `API_BEST_PRACTICES.md`
- **JSDoc & IDE Support:** `JSDOC_GUIDE.md` ⭐ NEW!
- **IDE Suggestions Demo:** `src/examples/ide-suggestions-demo.js` ⭐ NEW!

## ✨ Key Benefits

| Feature | Before | After |
|---------|--------|-------|
| Boilerplate | ~20 lines/component | ~3 lines |
| Error Handling | Manual try-catch | Automatic |
| Loading State | Manual ref() | Automatic |
| Code Reuse | Copy-paste | Composable |
| Maintainability | 😓 Hard | ✅ Easy |
| **IDE Support** | ❌ No hints | ✅ **Full IntelliSense** ⭐ |
| **Type Safety** | ❌ None | ✅ **JSDoc Types** ⭐ |

## 🔥 Pro Tips

1. **Always use services** - Không gọi `http.get()` trực tiếp trong components
2. **Use composables** - Luôn dùng `useApi` hoặc `useList` 
3. **Handle errors** - Sử dụng `onError` callback cho custom handling
4. **Type safety** - ✅ **JSDoc đã được thêm vào tất cả files!** IDE sẽ suggest khi code
5. **Organize by domain** - Tạo service riêng cho mỗi module
6. **Hover to learn** - Hover chuột lên functions để xem documentation + examples

## 🚀 Next Steps

1. ✅ Study the examples at `/examples`
2. ✅ Create your own services
3. ✅ Add authentication interceptors
4. ✅ Implement retry logic
5. ✅ Add request caching
6. ✅ Migrate to TypeScript (optional)

---

**Happy Coding! 🎉**

