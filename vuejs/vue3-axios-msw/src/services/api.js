import axios from 'axios'

// Tạo axios instance với cấu hình mặc định
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE || 'https://vpic.nhtsa.dot.gov/api/vehicles',
  headers: { 
    'Content-Type': 'application/json',
  },
  timeout: 15000,
})

// Request Interceptor - thêm token, logging, etc
apiClient.interceptors.request.use(
  (config) => {
    // Có thể thêm token vào header
    // const token = localStorage.getItem('token')
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`
    // }
    
    // Log request trong dev mode
    if (import.meta.env.DEV) {
      console.log('🚀 Request:', config.method?.toUpperCase(), config.url)
    }
    
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Response Interceptor - xử lý response và error tập trung
apiClient.interceptors.response.use(
  (response) => {
    // Log response trong dev mode
    if (import.meta.env.DEV) {
      console.log('✅ Response:', response.config.url, response.status)
    }
    return response
  },
  (error) => {
    // Xử lý lỗi tập trung
    const errorMessage = handleApiError(error)
    
    if (import.meta.env.DEV) {
      console.error('❌ API Error:', errorMessage)
    }
    
    // Có thể thêm logic redirect hoặc refresh token ở đây
    // if (error.response?.status === 401) {
    //   // Redirect to login hoặc refresh token
    // }
    
    return Promise.reject(error)
  }
)

// Hàm xử lý lỗi tập trung
function handleApiError(error) {
  if (error.response) {
    // Server responded with error status
    const { status, data } = error.response
    
    switch (status) {
      case 400:
        return data?.message || 'Yêu cầu không hợp lệ'
      case 401:
        return 'Chưa xác thực. Vui lòng đăng nhập'
      case 403:
        return 'Không có quyền truy cập'
      case 404:
        return 'Không tìm thấy tài nguyên'
      case 422:
        return data?.message || 'Dữ liệu không hợp lệ'
      case 500:
        return 'Lỗi máy chủ. Vui lòng thử lại sau'
      default:
        return data?.message || `Lỗi: ${status}`
    }
  } else if (error.request) {
    // Request was made but no response
    return 'Không thể kết nối đến máy chủ. Vui lòng kiểm tra kết nối mạng'
  } else {
    // Something else happened
    return error.message || 'Đã xảy ra lỗi không xác định'
  }
}

/**
 * HTTP wrapper methods - trả về data trực tiếp
 * Tất cả methods đều tự động handle loading/error states khi dùng với composables
 * 
 * @typedef {Object} AxiosConfig
 * @property {Object} [params] - Query parameters (URL params)
 * @property {Object} [headers] - Custom headers
 * @property {number} [timeout] - Request timeout (ms)
 * @property {Function} [onUploadProgress] - Upload progress callback
 * @property {Function} [onDownloadProgress] - Download progress callback
 */
export const http = {
  /**
   * HTTP GET request
   * @template T
   * @param {string} url - Endpoint URL (relative to baseURL)
   * @param {AxiosConfig} [config={}] - Axios config object
   * @returns {Promise<T>} Response data
   */
  get: async (url, config = {}) => {
    const response = await apiClient.get(url, config)
    return response.data
  },
  
  /**
   * HTTP POST request
   * @template T
   * @param {string} url - Endpoint URL (relative to baseURL)
   * @param {Object} data - Request body data
   * @param {AxiosConfig} [config={}] - Axios config object
   * @returns {Promise<T>} Response data
   */
  post: async (url, data, config = {}) => {
    const response = await apiClient.post(url, data, config)
    return response.data
  },
  
  /**
   * HTTP PUT request - Update toàn bộ resource
   * @template T
   * @param {string} url - Endpoint URL (relative to baseURL)
   * @param {Object} data - Request body data
   * @param {AxiosConfig} [config={}] - Axios config object
   * @returns {Promise<T>} Response data
   */
  put: async (url, data, config = {}) => {
    const response = await apiClient.put(url, data, config)
    return response.data
  },
  
  /**
   * HTTP PATCH request - Update một phần resource
   * @template T
   * @param {string} url - Endpoint URL (relative to baseURL)
   * @param {Object} data - Partial data to update
   * @param {AxiosConfig} [config={}] - Axios config object
   * @returns {Promise<T>} Response data
   */
  patch: async (url, data, config = {}) => {
    const response = await apiClient.patch(url, data, config)
    return response.data
  },
  
  /**
   * HTTP DELETE request
   * @template T
   * @param {string} url - Endpoint URL (relative to baseURL)
   * @param {AxiosConfig} [config={}] - Axios config object
   * @returns {Promise<T>} Response data (usually empty or status message)
   */
  delete: async (url, config = {}) => {
    const response = await apiClient.delete(url, config)
    return response.data
  },
  
  /**
   * Truy cập raw Axios instance nếu cần custom logic
   * @type {import('axios').AxiosInstance}
   */
  rawInstance: apiClient,
}

export default http
