import { ref } from 'vue'

/**
 * @typedef {Object} UseApiOptions
 * @property {boolean} [immediate=false] - Tự động gọi API khi component mount
 * @property {Function} [onSuccess] - Callback khi API call thành công
 * @property {Function} [onError] - Callback khi API call thất bại
 */

/**
 * @typedef {Object} UseApiReturn
 * @template T
 * @property {import('vue').Ref<T|null>} data - Response data từ API
 * @property {import('vue').Ref<boolean>} loading - Loading state
 * @property {import('vue').Ref<string|null>} error - Error message (nếu có)
 * @property {Function} execute - Function để trigger API call
 */

/**
 * Composable cho việc gọi API với state management tự động
 * Quản lý loading, error, và data state
 * 
 * @template T - Type của response data
 * @param {Function} apiFunc - API function cần gọi (từ service)
 * @param {UseApiOptions} [options={}] - Cấu hình options
 * @returns {UseApiReturn<T>} Object chứa data, loading, error, execute
 */
export function useApi(apiFunc, options = {}) {
  const data = ref(null)
  const loading = ref(false)
  const error = ref(null)
  const { immediate = false, onSuccess, onError } = options

  /**
   * Thực thi API call
   * 
   * @param {...any} args - Arguments được truyền cho API function
   * @returns {Promise<T>} Response data từ API
   * @throws {Error} Nếu API call thất bại
   */
  const execute = async (...args) => {
    loading.value = true
    error.value = null

    try {
      const result = await apiFunc(...args)
      data.value = result
      
      // Callback khi thành công
      if (onSuccess) {
        onSuccess(result)
      }
      
      return result
    } catch (err) {
      error.value = err.message || 'Đã xảy ra lỗi'
      
      // Callback khi có lỗi
      if (onError) {
        onError(err)
      }
      
      throw err
    } finally {
      loading.value = false
    }
  }

  // Tự động gọi API nếu immediate = true
  if (immediate) {
    execute()
  }

  return {
    data,
    loading,
    error,
    execute,
  }
}

/**
 * @typedef {Object} UseListReturn
 * @template T
 * @property {import('vue').Ref<T[]>} items - Array of items (parsed từ Results)
 * @property {import('vue').Ref<any>} data - Raw response data
 * @property {import('vue').Ref<boolean>} loading - Loading state
 * @property {import('vue').Ref<string|null>} error - Error message (nếu có)
 * @property {Function} refresh - Function để reload data
 */

/**
 * Composable đơn giản hóa cho list data
 * Tự động parse Results array từ VPIC API response
 * Sử dụng cho các API trả về format: { Count, Message, Results: [...] }
 * 
 * @template T - Type của mỗi item trong array
 * @param {Function} apiFunc - API function cần gọi (từ service)
 * @param {UseApiOptions} [options={}] - Cấu hình options
 * @returns {UseListReturn<T>} Object chứa items, data, loading, error, refresh
 */
export function useList(apiFunc, options = {}) {
  const { data, loading, error, execute } = useApi(apiFunc, options)
  
  // Computed để lấy Results array
  const items = ref([])
  
  /**
   * Refresh/reload data from API
   * 
   * @param {...any} args - Arguments để truyền cho API function
   * @returns {Promise<any>} Raw response data
   */
  const refresh = async (...args) => {
    const result = await execute(...args)
    // Parse Results từ VPIC API format
    items.value = result?.Results || result || []
    return result
  }

  if (options.immediate) {
    refresh()
  }

  return {
    items,
    data,
    loading,
    error,
    refresh,
  }
}

export default useApi

