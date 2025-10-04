# 🔐 Authentication Guide

## 📋 Overview

Mock authentication system với JSDoc comments đầy đủ cho development và testing.

## 🎯 Features

- ✅ **Login/Logout** - Username/password authentication
- ✅ **JWT Token** - Mock JWT tokens với expiration
- ✅ **Role-based Access** - Admin và User roles
- ✅ **Token Validation** - Validate tokens và get user info
- ✅ **User Management** - Admin có thể tạo/xem users
- ✅ **Session Storage** - localStorage integration
- ✅ **Full JSDoc** - IDE IntelliSense support

## 📁 Files Created

### 1. `src/mocks/auth.js` - Mock Authentication Logic
- Mock database với 3 users
- Session management
- Token generation/validation
- Role-based permissions

### 2. `src/services/authService.js` - API Service
- HTTP client integration
- Full JSDoc comments
- Type definitions
- Examples trong documentation

### 3. `src/components/AuthDemo.vue` - Demo Component
- Login form
- User info display
- Token validation
- Admin panel
- User management

### 4. Updated `src/mocks/handlers.js`
- MSW handlers cho auth endpoints
- Error handling
- Status codes

## 🚀 Quick Start

### 1. Run Project
```bash
npm run dev
```

### 2. Navigate to Auth Demo
```
http://localhost:5173/auth
```

### 3. Test Login
Use demo credentials:
- **Admin:** `admin` / `admin123`
- **User:** `user` / `user123`
- **Demo:** `demo` / `demo123`

## 📚 API Endpoints

### POST `/api/auth/login`
**Login với username/password**

**Request:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response (Success):**
```json
{
  "success": true,
  "token": "jwt_1_1234567890_abc123",
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "role": "admin",
    "name": "Administrator"
  },
  "message": "Login successful"
}
```

**Response (Error):**
```json
{
  "success": false,
  "message": "Invalid password",
  "code": "INVALID_PASSWORD"
}
```

### POST `/api/auth/logout`
**Logout và invalidate token**

**Request:**
```json
{
  "token": "jwt_1_1234567890_abc123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Logout successful"
}
```

### GET `/api/auth/validate?token=xxx`
**Validate token và get user info**

**Response (Valid):**
```json
{
  "success": true,
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "role": "admin",
    "name": "Administrator"
  },
  "message": "Token valid"
}
```

### GET `/api/auth/users?token=xxx` (Admin Only)
**Get all users**

**Response:**
```json
{
  "success": true,
  "users": [
    {
      "id": 1,
      "username": "admin",
      "email": "admin@example.com",
      "role": "admin",
      "name": "Administrator"
    }
  ],
  "message": "Users retrieved successfully"
}
```

### POST `/api/auth/users?token=xxx` (Admin Only)
**Create new user**

**Request:**
```json
{
  "username": "newuser",
  "password": "newpass123",
  "email": "new@example.com",
  "role": "user",
  "name": "New User"
}
```

## 💻 Usage Examples

### Basic Login
```javascript
import authService from '@/services/authService'

// Login
const response = await authService.login({
  username: 'admin',
  password: 'admin123'
})

if (response.success) {
  console.log('Token:', response.token)
  console.log('User:', response.user.name)
}
```

### With Composable
```javascript
import { useApi } from '@/composables/useApi'
import authService from '@/services/authService'

const { data, loading, error, execute } = useApi(authService.login)

// Login
await execute({
  username: 'admin',
  password: 'admin123'
})

if (data.value?.success) {
  localStorage.setItem('token', data.value.token)
}
```

### Token Validation
```javascript
const token = localStorage.getItem('token')
const response = await authService.validateToken(token)

if (response.success) {
  console.log('User:', response.user.name)
} else {
  console.log('Invalid token')
  localStorage.removeItem('token')
}
```

### Admin Operations
```javascript
// Get all users (admin only)
const usersResponse = await authService.getAllUsers(adminToken)
if (usersResponse.success) {
  console.log('Users:', usersResponse.users)
}

// Create user (admin only)
const newUser = await authService.createUser(adminToken, {
  username: 'newuser',
  password: 'newpass123',
  email: 'new@example.com',
  role: 'user',
  name: 'New User'
})
```

## 🎨 Component Usage

### AuthDemo Component Features

1. **Login Form**
   - Username/password input
   - Loading states
   - Error handling
   - Success feedback

2. **User Info Display**
   - Current user details
   - Logout button
   - Token display

3. **Token Validation**
   - Validate current token
   - Check expiration
   - Refresh user info

4. **Admin Panel** (Admin only)
   - List all users
   - Create new users
   - Role management

5. **Session Persistence**
   - localStorage integration
   - Auto-restore on page reload
   - Session cleanup on logout

## 🔧 Configuration

### Mock Users
Edit `src/mocks/auth.js` để thêm users:

```javascript
let users = [
  {
    id: 1,
    username: 'admin',
    password: 'admin123',
    email: 'admin@example.com',
    role: 'admin',
    name: 'Administrator'
  },
  // Add more users...
]
```

### Token Expiration
Default: 24 hours. Change trong `auth.js`:

```javascript
expiresAt: new Date(Date.now() + 24 * 60 * 60 * 1000) // 24 hours
```

## 🧪 Testing

### Manual Testing
1. Navigate to `/auth`
2. Try login với different credentials
3. Test admin features
4. Test token validation
5. Test logout

### API Testing
```javascript
// Test trong browser console
const response = await fetch('/api/auth/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    username: 'admin',
    password: 'admin123'
  })
})
const data = await response.json()
console.log(data)
```

## 📊 Demo Credentials

| Username | Password | Role | Name |
|----------|----------|------|------|
| `admin` | `admin123` | admin | Administrator |
| `user` | `user123` | user | Regular User |
| `demo` | `demo123` | user | Demo User |

## 🔒 Security Notes

⚠️ **Development Only**
- Passwords stored in plain text
- No real JWT implementation
- No password hashing
- No rate limiting
- No CSRF protection

**For Production:**
- Use real JWT library
- Hash passwords with bcrypt
- Implement rate limiting
- Add CSRF protection
- Use HTTPS only

## 🎯 IDE Support

Tất cả auth functions có **full JSDoc comments**:

```javascript
// IDE sẽ suggest parameters
authService.login({
  // ↑ IDE suggests: username, password
})

// IDE sẽ show return types
const response = await authService.login(...)
response.  // ↑ IDE suggests: success, token, user, message
```

## 📚 Related Documentation

- **Services Guide:** [`src/services/README.md`](./src/services/README.md)
- **JSDoc Guide:** [`JSDOC_GUIDE.md`](./JSDOC_GUIDE.md)
- **API Best Practices:** [`API_BEST_PRACTICES.md`](./API_BEST_PRACTICES.md)

## 🚀 Next Steps

1. **Test Authentication:** Navigate to `/auth`
2. **Try Different Roles:** Login as admin vs user
3. **Test API Calls:** Use browser dev tools
4. **Create Your Own:** Follow patterns trong code
5. **Add Real Auth:** Replace mock với real implementation

---

**Happy Authenticating! 🔐**
