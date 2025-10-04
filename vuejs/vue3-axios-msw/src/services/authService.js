import http from './api'

/**
 * @typedef {Object} User
 * @property {number} id - User ID
 * @property {string} username - Username
 * @property {string} email - Email address
 * @property {string} role - User role (admin, user)
 * @property {string} name - Display name
 */

/**
 * @typedef {Object} LoginRequest
 * @property {string} username - Username
 * @property {string} password - Password
 */

/**
 * @typedef {Object} LoginResponse
 * @property {boolean} success - Login success status
 * @property {string} token - JWT token
 * @property {User} user - User information
 * @property {string} message - Response message
 */

/**
 * @typedef {Object} LogoutRequest
 * @property {string} token - JWT token to invalidate
 */

/**
 * @typedef {Object} LogoutResponse
 * @property {boolean} success - Logout success status
 * @property {string} message - Response message
 */

/**
 * @typedef {Object} ValidateResponse
 * @property {boolean} success - Validation success status
 * @property {User} [user] - User information (if valid)
 * @property {string} message - Response message
 */

/**
 * @typedef {Object} CreateUserRequest
 * @property {string} username - Username
 * @property {string} password - Password
 * @property {string} email - Email address
 * @property {string} role - User role
 * @property {string} name - Display name
 */

/**
 * @typedef {Object} CreateUserResponse
 * @property {boolean} success - Creation success status
 * @property {User} [user] - Created user information
 * @property {string} message - Response message
 */

/**
 * @typedef {Object} UsersListResponse
 * @property {boolean} success - Request success status
 * @property {User[]} [users] - List of users
 * @property {string} message - Response message
 */

export const authService = {
  /**
   * Authenticate user with username and password
   * 
   * @param {LoginRequest} credentials - Login credentials
   * @returns {Promise<LoginResponse>} Login response with token and user info
   */
  async login(credentials) {
    return await http.post('/api/auth/login', credentials)
  },

  /**
   * Logout user by invalidating token
   * 
   * @param {string} token - JWT token to invalidate
   * @returns {Promise<LogoutResponse>} Logout response
   */
  async logout(token) {
    return await http.post('/api/auth/logout', { token })
  },

  /**
   * Validate JWT token and get user information
   * 
   * @param {string} token - JWT token to validate
   * @returns {Promise<ValidateResponse>} Validation response with user info
   */
  async validateToken(token) {
    return await http.get('/api/auth/validate', {
      params: { token }
    })
  },

  /**
   * Get all users (admin only)
   * 
   * @param {string} token - Admin JWT token
   * @returns {Promise<UsersListResponse>} List of all users
   */
  async getAllUsers(token) {
    return await http.get('/api/auth/users', {
      params: { token }
    })
  },

  /**
   * Create new user (admin only)
   * 
   * @param {string} token - Admin JWT token
   * @param {CreateUserRequest} userData - New user data
   * @returns {Promise<CreateUserResponse>} Creation response
   */
  async createUser(token, userData) {
    return await http.post('/api/auth/users', userData, {
      params: { token }
    })
  }
}

export default authService
