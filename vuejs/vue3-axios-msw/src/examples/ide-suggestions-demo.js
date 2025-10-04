/**
 * File demo để test IDE IntelliSense/Autocomplete
 * 
 * Hướng dẫn:
 * 1. Mở file này trong VS Code hoặc IDE khác
 * 2. Bắt đầu gõ code bên dưới
 * 3. IDE sẽ tự động suggest parameters và return types
 * 4. Hover chuột lên functions để xem documentation
 */

import http from '../services/api'
import makesService from '../services/makesService'
import { useApi, useList } from '../composables/useApi'

// ==========================================
// DEMO 1: http methods suggestions
// ==========================================

// Khi gõ: http.
// IDE sẽ suggest: get, post, put, patch, delete, rawInstance

// Khi gõ: http.get(
// IDE sẽ hint: (url: string, config?: AxiosConfig) => Promise<T>

async function demo1() {
  // Hover chuột lên 'get' để xem documentation + examples
  const response = await http.get('/getallmakes', {
    // IDE suggest khi gõ: params, headers, timeout, onUploadProgress...
    params: { format: 'json' },
    timeout: 5000
  })

  return response
}

// ==========================================
// DEMO 2: makesService suggestions
// ==========================================

// Khi gõ: makesService.
// IDE sẽ suggest: getAll, getById, search, create, update, delete

async function demo2() {
  // Hover lên 'getAll' để xem JSDoc:
  // - @param params: GetAllParams
  // - @returns Promise<VPICResponse>
  // - @example code
  const response = await makesService.getAll({
    // IDE suggest: format, page, limit
    format: 'json',
    page: 1
  })

  // Hover lên 'response' để xem type: VPICResponse
  // IDE biết response có properties: Count, Message, Results
  console.log(response.Count)    // ✅ IDE suggest 'Count'
  console.log(response.Message)  // ✅ IDE suggest 'Message'
  console.log(response.Results)  // ✅ IDE suggest 'Results'

  // Results là array of Make
  // IDE biết mỗi item có: Make_ID, Make_Name
  response.Results.forEach(make => {
    console.log(make.Make_ID)    // ✅ IDE suggest 'Make_ID'
    console.log(make.Make_Name)  // ✅ IDE suggest 'Make_Name'
  })
}

// ==========================================
// DEMO 3: makesService.create suggestions
// ==========================================

async function demo3() {
  // Hover lên 'create' để xem type: CreateMakeInput
  // IDE hint: { Make_Name: string }
  const newMake = await makesService.create({
    // IDE suggest: Make_Name (required)
    Make_Name: 'Tesla'
  })

  // IDE biết return type là Make
  console.log(newMake.Make_ID)    // ✅ Suggest Make_ID
  console.log(newMake.Make_Name)  // ✅ Suggest Make_Name
}

// ==========================================
// DEMO 4: useApi composable suggestions
// ==========================================

function demo4() {
  // Hover lên 'useApi' để xem full documentation
  const { data, loading, error, execute } = useApi(
    makesService.getAll,
    {
      // IDE suggest options: immediate, onSuccess, onError
      immediate: true,
      onSuccess: (result) => {
        // IDE biết result type là VPICResponse
        console.log(result.Count)    // ✅ Suggest Count
        console.log(result.Results)  // ✅ Suggest Results
      },
      onError: (err) => {
        // IDE biết err là Error
        console.log(err.message)     // ✅ Suggest message
      }
    }
  )

  // IDE biết return types:
  // - data: Ref<VPICResponse | null>
  // - loading: Ref<boolean>
  // - error: Ref<string | null>
  // - execute: Function

  // Autocomplete khi gõ data.
  console.log(data.value?.Count)    // ✅ Suggest Count, Message, Results

  // Autocomplete khi gõ loading.
  if (loading.value) {              // ✅ Suggest .value (Ref type)
    console.log('Loading...')
  }

  return { data, loading, error, execute }
}

// ==========================================
// DEMO 5: useList composable suggestions
// ==========================================

function demo5() {
  // Hover lên 'useList' để xem documentation
  const { items, data, loading, error, refresh } = useList(
    makesService.getAll,
    {
      // IDE suggest: immediate, onSuccess, onError
      immediate: true,
      onSuccess: (response) => {
        // IDE biết response có Count
        console.log(`Loaded ${response.Count} items`)
      }
    }
  )

  // IDE biết types:
  // - items: Ref<Make[]>  <- Array of Make objects
  // - data: Ref<VPICResponse>
  // - loading: Ref<boolean>
  // - error: Ref<string | null>
  // - refresh: Function

  // Autocomplete cho items
  items.value.forEach(make => {
    // IDE suggest: Make_ID, Make_Name
    console.log(make.Make_ID)
    console.log(make.Make_Name)
  })

  // Autocomplete cho data (raw response)
  console.log(data.value?.Count)    // ✅ Suggest Count
  console.log(data.value?.Message)  // ✅ Suggest Message

  return { items, loading, refresh }
}

// ==========================================
// DEMO 6: execute với parameters
// ==========================================

async function demo6() {
  // Case 1: execute function với parameters
  const { execute: getById } = useApi(makesService.getById)
  
  // Hover lên execute để xem nó nhận gì
  // IDE hint: execute(...args: any[]) => Promise<VPICResponse>
  const result = await getById(474)  // Pass makeId
  console.log(result.Results)

  // Case 2: create với data
  const { execute: createMake } = useApi(makesService.create)
  
  // IDE hint parameter type: CreateMakeInput
  const newMake = await createMake({
    Make_Name: 'Tesla'  // ✅ IDE suggest Make_Name
  })
  console.log(newMake.Make_ID)
}

// ==========================================
// DEMO 7: Multiple HTTP methods
// ==========================================

async function demo7() {
  // GET với params
  const users = await http.get('/users', {
    params: { role: 'admin' }  // ✅ IDE suggest 'params'
  })

  // POST với body
  const created = await http.post('/users', {
    name: 'John',
    email: 'john@example.com'
  })

  // PUT full update
  const updated = await http.put('/users/1', {
    name: 'Jane',
    email: 'jane@example.com'
  })

  // PATCH partial update
  const patched = await http.patch('/users/1', {
    name: 'Jane Doe'  // Only update name
  })

  // DELETE
  await http.delete('/users/1')

  return { users, created, updated, patched }
}

// ==========================================
// DEMO 8: Error handling với types
// ==========================================

async function demo8() {
  const { data, error, execute } = useApi(makesService.getAll)

  try {
    await execute()
    
    if (data.value) {
      // IDE biết data.value type là VPICResponse
      console.log(data.value.Count)    // ✅ Autocomplete
    }
  } catch (err) {
    // IDE biết error.value type là string | null
    console.error(error.value)         // ✅ Type safety
  }
}

// ==========================================
// Export để không có linter warning
// ==========================================

export {
  demo1,
  demo2,
  demo3,
  demo4,
  demo5,
  demo6,
  demo7,
  demo8,
}

/**
 * ========================================
 * KẾT QUẢ MONG ĐỢI KHI GÕ CODE:
 * ========================================
 * 
 * 1. Autocomplete cho functions:
 *    - Gõ "http." → suggest: get, post, put, patch, delete
 *    - Gõ "makesService." → suggest: getAll, getById, create...
 * 
 * 2. Parameter hints:
 *    - Gõ "http.get(" → hint: (url: string, config?: AxiosConfig)
 *    - Gõ "makesService.create(" → hint: (data: CreateMakeInput)
 * 
 * 3. Return type hints:
 *    - response. → suggest: Count, Message, Results
 *    - make. → suggest: Make_ID, Make_Name
 * 
 * 4. Hover documentation:
 *    - Hover lên function → show full JSDoc
 *    - Hover lên variable → show type
 * 
 * 5. Examples trong hover:
 *    - Mỗi function có @example code
 *    - Copy-paste examples trực tiếp
 * 
 * ========================================
 * VS CODE TIPS:
 * ========================================
 * 
 * - Ctrl+Space: Force trigger autocomplete
 * - Ctrl+Shift+Space: Parameter hints
 * - Ctrl+K Ctrl+I: Hover information
 * - F12: Go to definition
 * - Alt+F12: Peek definition
 * - Shift+F12: Find all references
 * 
 */

