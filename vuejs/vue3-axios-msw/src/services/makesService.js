import http from './api'

/**
 * @typedef {Object} Make
 * @property {number} Make_ID - ID của hãng xe
 * @property {string} Make_Name - Tên hãng xe
 */

/**
 * @typedef {Object} VPICResponse
 * @property {number} Count - Số lượng kết quả
 * @property {string} Message - Thông báo từ server
 * @property {string} SearchCriteria - Tiêu chí tìm kiếm
 * @property {Make[]} Results - Danh sách kết quả
 */

/**
 * @typedef {Object} GetAllParams
 * @property {string} [format='json'] - Format của response
 * @property {number} [page] - Số trang (nếu API hỗ trợ pagination)
 * @property {number} [limit] - Số items per page
 */

/**
 * @typedef {Object} CreateMakeInput
 * @property {string} Make_Name - Tên hãng xe (required)
 */

/**
 * @typedef {Object} UpdateMakeInput
 * @property {string} [Make_Name] - Tên hãng xe mới
 */

/**
 * Service cho các API liên quan đến Makes (hãng xe)
 * Tập trung tất cả các API calls vào một nơi để dễ quản lý
 * 
 * API Documentation: https://vpic.nhtsa.dot.gov/api/
 */
export const makesService = {
  /**
   * Lấy danh sách tất cả các hãng xe từ NHTSA VPIC API
   * 
   * @param {GetAllParams} [params={}] - Query parameters
   * @returns {Promise<VPICResponse>} Response với Count, Message, Results
   */
  async getAll(params = {}) {
    const defaultParams = { format: 'json', ...params }
    return await http.get('/getallmakes', { params: defaultParams })
  },

  /**
   * Lấy thông tin một hãng xe theo ID
   * 
   * @param {number} makeId - ID của hãng xe cần lấy
   * @returns {Promise<VPICResponse>} Response với thông tin hãng xe
   */
  async getById(makeId) {
    return await http.get(`/getmakeformanufacturer/${makeId}`, { 
      params: { format: 'json' } 
    })
  },

  /**
   * Tìm kiếm hãng xe theo tên
   * 
   * @param {string} makeName - Tên hãng xe cần tìm
   * @returns {Promise<VPICResponse>} Response với kết quả tìm kiếm
   */
  async search(makeName) {
    return await http.get('/getallmakes', { 
      params: { 
        format: 'json',
        // Thêm các params search nếu API hỗ trợ
      } 
    })
  },

  /**
   * Tạo một hãng xe mới (mock API - chỉ hoạt động với MSW trong dev)
   * 
   * @param {CreateMakeInput} data - Dữ liệu hãng xe mới
   * @returns {Promise<Make>} Hãng xe vừa tạo với Make_ID mới
   */
  async create(data) {
    return await http.post('/api/makes', data)
  },

  /**
   * Cập nhật thông tin hãng xe (mock API - chỉ hoạt động với MSW trong dev)
   * 
   * @param {number} id - ID của hãng xe cần update
   * @param {UpdateMakeInput} data - Dữ liệu cần update
   * @returns {Promise<Make>} Hãng xe sau khi update
   */
  async update(id, data) {
    return await http.put(`/api/makes/${id}`, data)
  },

  /**
   * Xóa một hãng xe (mock API - chỉ hoạt động với MSW trong dev)
   * 
   * @param {number} id - ID của hãng xe cần xóa
   * @returns {Promise<void>} Không có response body (status 204)
   */
  async delete(id) {
    return await http.delete(`/api/makes/${id}`)
  },
}

export default makesService

