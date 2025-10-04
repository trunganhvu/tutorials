# 🔌 Services Layer

## Tổng Quan

Thư mục này chứa tất cả API services - nơi tập trung các API calls theo domain/module.

## Files

### `api.js` - Core HTTP Client
Axios instance configuration với interceptors và error handling.

**Exports:**
- `http.get(url, config)` - GET request
- `http.post(url, data, config)` - POST request
- `http.put(url, data, config)` - PUT request (full update)
- `http.patch(url, data, config)` - PATCH request (partial update)
- `http.delete(url, config)` - DELETE request
- `http.rawInstance` - Raw Axios instance

**Features:**
✅ Request/Response interceptors
✅ Centralized error handling
✅ Auto token injection (ready to uncomment)
✅ Dev mode logging
✅ Timeout configuration

### `makesService.js` - Makes API
Service cho NHTSA VPIC Makes API endpoints.

**Exports:**
- `makesService.getAll(params)` - Get all makes
- `makesService.getById(id)` - Get make by ID
- `makesService.search(name)` - Search makes
- `makesService.create(data)` - Create make (mock)
- `makesService.update(id, data)` - Update make (mock)
- `makesService.delete(id)` - Delete make (mock)

**Types:**
- `Make` - { Make_ID, Make_Name }
- `VPICResponse` - { Count, Message, Results }

## Usage Examples

### Direct HTTP Call
```javascript
import http from '@/services/api'

const response = await http.get('/endpoint', {
  params: { page: 1 }
})
```

### Using Service
```javascript
import makesService from '@/services/makesService'

const response = await makesService.getAll()
console.log(response.Results) // Array of Make objects
```

### With Composable (Recommended)
```javascript
import { useList } from '@/composables/useApi'
import makesService from '@/services/makesService'

const { items, loading, error } = useList(
  makesService.getAll,
  { immediate: true }
)
```

## Creating New Service

```javascript
// src/services/userService.js
import http from './api'

/**
 * @typedef {Object} User
 * @property {number} id
 * @property {string} name
 * @property {string} email
 */

export const userService = {
  /**
   * @returns {Promise<User[]>}
   */
  async getAll() {
    return await http.get('/users')
  },

  /**
   * @param {number} id
   * @returns {Promise<User>}
   */
  async getById(id) {
    return await http.get(`/users/${id}`)
  },

  /**
   * @param {Partial<User>} data
   * @returns {Promise<User>}
   */
  async create(data) {
    return await http.post('/users', data)
  },

  /**
   * @param {number} id
   * @param {Partial<User>} data
   * @returns {Promise<User>}
   */
  async update(id, data) {
    return await http.put(`/users/${id}`, data)
  },

  /**
   * @param {number} id
   * @returns {Promise<void>}
   */
  async delete(id) {
    return await http.delete(`/users/${id}`)
  },
}

export default userService
```

## Best Practices

### ✅ DO:
1. **Tạo service riêng cho mỗi domain** (users, products, orders...)
2. **Add JSDoc comments** cho IDE support
3. **Define types với @typedef** cho complex objects
4. **Include @example** trong JSDoc
5. **Use async/await** thay vì .then()
6. **Return data directly** - http wrapper đã handle response.data

### ❌ DON'T:
1. ❌ Gọi `http.get()` trực tiếp trong components
2. ❌ Mix business logic vào services
3. ❌ Duplicate error handling (đã có trong interceptor)
4. ❌ Hardcode URLs (use env variables)
5. ❌ Forget to document parameters và return types

## IDE Support

Tất cả services có **full JSDoc comments** → IDE sẽ:
- ✅ Autocomplete function names
- ✅ Suggest parameters
- ✅ Show return types
- ✅ Display hover documentation
- ✅ Provide inline examples

**Test:** Gõ `makesService.` trong IDE và xem suggestions!

## Environment Variables

```env
# .env.local / .env.development
VITE_API_BASE=https://api.example.com
```

Access trong services:
```javascript
// Automatically used in api.js
baseURL: import.meta.env.VITE_API_BASE
```

## Related Documentation

- **JSDoc Guide:** `/JSDOC_GUIDE.md`
- **API Best Practices:** `/API_BEST_PRACTICES.md`
- **Composables Guide:** `/src/composables/README.md`
- **Quick Start:** `/QUICK_START.md`

