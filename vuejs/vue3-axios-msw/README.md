# Vue 3 API Best Practices

Vue 3 project với API Best Practices, Axios và MSW.

## Features

- API Best Practices - Clean architecture với services + composables
- JSDoc Comments - Full IDE IntelliSense support
- Mock Service Worker v2 - API mocking trong development
- Centralized Error Handling - Errors handled in interceptors
- Auto Loading States - Composables manage loading/error states
- Environment Variables - .env.local, .env.development
- PrimeVue + Tailwind CSS - Modern UI components

## Documentation

**Đọc ngay:** [DOCUMENTATION_INDEX.md](./DOCUMENTATION_INDEX.md)

### Quick Links:
- [QUICK_START.md](./QUICK_START.md) - Get started in 5 minutes
- [API_BEST_PRACTICES.md](./API_BEST_PRACTICES.md) - Understand the system
- [JSDOC_GUIDE.md](./JSDOC_GUIDE.md) - Autocomplete + type hints
- [AUTHENTICATION_GUIDE.md](./AUTHENTICATION_GUIDE.md) - Login/logout demo

## Quick Examples

### Simple API Call with Auto-Loading
```javascript
import { useList } from '@/composables/useApi'
import makesService from '@/services/makesService'

const { items, loading, error } = useList(
  makesService.getAll,
  { immediate: true }
)
```

### Create New Service
```javascript
// src/services/userService.js
import http from './api'

export const userService = {
  async getAll() {
    return await http.get('/users')
  },
  async create(data) {
    return await http.post('/users', data)
  }
}
```

## Project Setup

```bash
npm install
```

### Development

```bash
npm run dev
```

Visit:
- http://localhost:5173/ - Home
- http://localhost:5173/makes - Production example
- http://localhost:5173/examples - 5 interactive examples
- http://localhost:5173/auth - Authentication demo

### Production

```bash
npm run build
```

### Lint

```bash
npm run lint
```

## Project Structure

```
src/
├── services/          # API services
│   ├── api.js         # HTTP client with interceptors
│   ├── makesService.js # Makes API endpoints
│   └── authService.js  # Authentication API
├── composables/       # Reusable composition functions
│   └── useApi.js      # API state management
├── components/        # Vue components
│   ├── MakesList.vue  # Production example
│   ├── MakesExample.vue # 5 usage examples
│   └── AuthDemo.vue   # Authentication demo
├── examples/          # Demo & learning files
│   └── ide-suggestions-demo.js
└── mocks/            # MSW mock handlers
    ├── browser.js
    ├── handlers.js
    └── auth.js
```

## Learning Path

1. [QUICK_START.md](./QUICK_START.md) - Quick start guide
2. Run dev server → Navigate to /examples
3. [API_BEST_PRACTICES.md](./API_BEST_PRACTICES.md) - Deep dive
4. [JSDOC_GUIDE.md](./JSDOC_GUIDE.md) - IDE support

## Key Benefits

| Before | After |
|--------|-------|
| ~20 lines/component | ~3 lines |
| Manual error handling | Automatic |
| No IDE hints | Full autocomplete |
| Copy-paste logic | Reusable composables |

## Tech Stack

- Vue 3 - Progressive framework
- Vite - Fast build tool
- Axios - HTTP client
- MSW v2 - API mocking
- PrimeVue - UI components
- Tailwind CSS - Utility-first CSS
- JSDoc - Type hints without TypeScript

## More Info

See [DOCUMENTATION_INDEX.md](./DOCUMENTATION_INDEX.md) for complete documentation.