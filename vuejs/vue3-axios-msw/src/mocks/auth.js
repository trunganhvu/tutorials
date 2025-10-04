let users = [
  {
    id: 1,
    username: 'admin',
    password: 'admin123', // In real app, this would be hashed
    email: 'admin@example.com',
    role: 'admin',
    name: 'Administrator'
  },
  {
    id: 2,
    username: 'user',
    password: 'user123',
    email: 'user@example.com',
    role: 'user',
    name: 'Regular User'
  },
  {
    id: 3,
    username: 'demo',
    password: 'demo123',
    email: 'demo@example.com',
    role: 'user',
    name: 'Demo User'
  }
]

let sessions = new Map()

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
 * @typedef {Object} AuthError
 * @property {boolean} success - Always false
 * @property {string} message - Error message
 * @property {string} code - Error code
 */

export const authService = {
  /**
   * Authenticate user with username/password
   * 
   * @param {LoginRequest} credentials - Login credentials
   * @returns {Promise<LoginResponse | AuthError>} Login response
   */
  async login(credentials) {
    const { username, password } = credentials
    
    await new Promise(resolve => setTimeout(resolve, 500))
    
    const user = users.find(u => u.username === username)
    
    if (!user) {
      return {
        success: false,
        message: 'User not found',
        code: 'USER_NOT_FOUND'
      }
    }
    
    if (user.password !== password) {
      return {
        success: false,
        message: 'Invalid password',
        code: 'INVALID_PASSWORD'
      }
    }
    
    const token = `jwt_${user.id}_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`
    
    sessions.set(token, {
      userId: user.id,
      username: user.username,
      role: user.role,
      loginTime: new Date().toISOString(),
      expiresAt: new Date(Date.now() + 24 * 60 * 60 * 1000).toISOString()
    })
    
    const { password: _, ...userWithoutPassword } = user
    
    return {
      success: true,
      token,
      user: userWithoutPassword,
      message: 'Login successful'
    }
  },

  /**
   * Logout user by invalidating token
   * 
   * @param {string} token - JWT token to invalidate
   * @returns {Promise<{success: boolean, message: string}>} Logout response
   */
  async logout(token) {
    await new Promise(resolve => setTimeout(resolve, 200))
    
    if (sessions.has(token)) {
      sessions.delete(token)
      return {
        success: true,
        message: 'Logout successful'
      }
    }
    
    return {
      success: false,
      message: 'Invalid token'
    }
  },

  /**
   * Validate JWT token and get user info
   * 
   * @param {string} token - JWT token to validate
   * @returns {Promise<{success: boolean, user?: User, message: string}>} Validation response
   */
  async validateToken(token) {
    await new Promise(resolve => setTimeout(resolve, 300))
    
    const session = sessions.get(token)
    
    if (!session) {
      return {
        success: false,
        message: 'Invalid or expired token'
      }
    }
    
    if (new Date() > new Date(session.expiresAt)) {
      sessions.delete(token)
      return {
        success: false,
        message: 'Token expired'
      }
    }
    
    const user = users.find(u => u.id === session.userId)
    if (!user) {
      return {
        success: false,
        message: 'User not found'
      }
    }
    
    const { password: _, ...userWithoutPassword } = user
    
    return {
      success: true,
      user: userWithoutPassword,
      message: 'Token valid'
    }
  },

  /**
   * Get all users (admin only)
   * 
   * @param {string} token - Admin token
   * @returns {Promise<{success: boolean, users?: User[], message: string}>} Users list
   */
  async getAllUsers(token) {
    await new Promise(resolve => setTimeout(resolve, 400))
    
    const session = sessions.get(token)
    if (!session || session.role !== 'admin') {
      return {
        success: false,
        message: 'Unauthorized - Admin access required'
      }
    }
    
    const usersWithoutPasswords = users.map(({ password, ...user }) => user)
    
    return {
      success: true,
      users: usersWithoutPasswords,
      message: 'Users retrieved successfully'
    }
  },

  /**
   * Create new user (admin only)
   * 
   * @param {string} token - Admin token
   * @param {Omit<User, 'id'> & {password: string}} userData - New user data
   * @returns {Promise<{success: boolean, user?: User, message: string}>} Creation response
   */
  async createUser(token, userData) {
    await new Promise(resolve => setTimeout(resolve, 600))
    
    const session = sessions.get(token)
    if (!session || session.role !== 'admin') {
      return {
        success: false,
        message: 'Unauthorized - Admin access required'
      }
    }
    
    const existingUser = users.find(u => u.username === userData.username)
    if (existingUser) {
      return {
        success: false,
        message: 'Username already exists'
      }
    }
    
    const newUser = {
      id: Math.max(...users.map(u => u.id)) + 1,
      ...userData
    }
    
    users.push(newUser)
    
    const { password: _, ...userWithoutPassword } = newUser
    
    return {
      success: true,
      user: userWithoutPassword,
      message: 'User created successfully'
    }
  }
}

export default authService
