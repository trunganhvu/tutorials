# Development Guide

This document provides comprehensive instructions for setting up and developing the AI Knowledge Portal.

## 🚀 Quick Start

### Prerequisites

- **Node.js** >= 18.0.0
- **npm** >= 9.0.0
- **Docker** >= 20.0.0
- **PostgreSQL** >= 15.0.0 (optional, can use Docker)

### Initial Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ai-knowledge-portal
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Set up environment variables**
   ```bash
   # Backend
   cp apps/backend/env.example apps/backend/.env
   # Edit apps/backend/.env with your configuration
   
   # Frontend
   cp apps/frontend/env.example apps/frontend/.env.local
   # Edit apps/frontend/.env.local with your configuration
   ```

4. **Set up database**
   ```bash
   # Using Docker (recommended)
   npm run docker:up
   
   # Or using local PostgreSQL
   # Create database and run migrations
   npm run db:migrate
   npm run db:seed
   ```

5. **Start development servers**
   ```bash
   npm run dev
   ```

## 🛠️ Development Workflow

### Available Scripts

#### Root Level Commands
- `npm run dev` - Start both frontend and backend in development mode
- `npm run build` - Build all packages for production
- `npm run test` - Run all tests across packages
- `npm run lint` - Run linting across all packages
- `npm run format` - Format code with Prettier

#### Backend Commands
- `npm run dev:backend` - Start backend development server
- `npm run test:backend` - Run backend tests
- `npm run db:migrate` - Run database migrations
- `npm run db:seed` - Seed database with initial data
- `npm run db:studio` - Open Prisma Studio

#### Frontend Commands
- `npm run dev:frontend` - Start frontend development server
- `npm run test:frontend` - Run frontend tests
- `npm run test:e2e` - Run end-to-end tests

#### Docker Commands
- `npm run docker:build` - Build all Docker containers
- `npm run docker:up` - Start all services with Docker
- `npm run docker:down` - Stop all Docker services
- `npm run docker:logs` - View Docker logs

### Development Servers

- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:3000
- **Database**: localhost:5432
- **Redis**: localhost:6379

## 📁 Project Structure

```
ai-knowledge-portal/
├── apps/
│   ├── frontend/          # Vue.js 3 + Vite
│   │   ├── src/
│   │   │   ├── components/     # UI components
│   │   │   ├── pages/          # Page components
│   │   │   ├── stores/         # Pinia state management
│   │   │   ├── services/       # API client services
│   │   │   ├── utils/          # Frontend utilities
│   │   │   └── types/          # TypeScript types
│   │   └── tests/              # Frontend tests
│   └── backend/                # Next.js + TypeScript
│       ├── src/
│       │   ├── pages/api/      # API routes
│       │   ├── lib/            # Backend utilities
│       │   ├── services/       # Business logic
│       │   ├── middleware/     # API middleware
│       │   └── types/          # TypeScript types
│       ├── prisma/             # Database schema
│       └── tests/              # Backend tests
├── packages/
│   ├── shared/                 # Shared types/utilities
│   └── config/                 # Shared configuration
└── docs/                       # Documentation
```

## 🧪 Testing

### Running Tests

```bash
# Run all tests
npm run test

# Run specific test suites
npm run test:backend
npm run test:frontend
npm run test:e2e

# Run tests with coverage
npm run test:coverage

# Run tests in watch mode
npm run test:watch
```

### Test Structure

- **Unit Tests**: Test individual functions and components
- **Integration Tests**: Test API endpoints and database operations
- **E2E Tests**: Test complete user workflows with Playwright

### Writing Tests

#### Backend Tests
```typescript
// Example API test
import { createMocks } from 'node-mocks-http'
import handler from '@/pages/api/health'

describe('/api/health', () => {
  it('returns 200 for GET request', async () => {
    const { req, res } = createMocks({ method: 'GET' })
    await handler(req, res)
    expect(res._getStatusCode()).toBe(200)
  })
})
```

#### Frontend Tests
```typescript
// Example component test
import { mount } from '@vue/test-utils'
import HomePage from '@/pages/HomePage.vue'

describe('HomePage', () => {
  it('renders welcome message', () => {
    const wrapper = mount(HomePage)
    expect(wrapper.text()).toContain('Welcome to AI Knowledge Portal')
  })
})
```

## 🗄️ Database

### Prisma Schema

The database schema is defined in `apps/backend/prisma/schema.prisma` and includes:

- **User**: Authentication and user management
- **Article**: Content management
- **FileAttachment**: File storage references
- **Session**: User sessions

### Database Operations

```bash
# Generate Prisma client
npm run db:generate

# Run migrations
npm run db:migrate

# Reset database
npm run db:reset

# Seed database
npm run db:seed

# Open Prisma Studio
npm run db:studio
```

### Environment Variables

Required environment variables for the backend:

```env
# Database
DATABASE_URL="postgresql://user:password@localhost:5432/knowledge_portal"

# Authentication
JWT_SECRET="your-super-secret-jwt-key"
JWT_EXPIRES_IN="15m"

# AWS Configuration
AWS_ACCESS_KEY_ID="your-aws-access-key"
AWS_SECRET_ACCESS_KEY="your-aws-secret-key"
AWS_REGION="us-east-1"
AWS_S3_BUCKET="knowledge-portal-files"

# Redis
REDIS_URL="redis://localhost:6379"
```

## 🐳 Docker Development

### Using Docker Compose

```bash
# Start all services
npm run docker:up

# View logs
npm run docker:logs

# Stop services
npm run docker:down

# Rebuild containers
npm run docker:build
```

### Docker Services

- **postgres**: PostgreSQL database
- **redis**: Redis cache
- **backend**: Next.js API server
- **frontend**: Vue.js development server
- **nginx**: Reverse proxy (production profile)

## 🔧 Code Quality

### Linting

```bash
# Run ESLint
npm run lint

# Fix linting issues
npm run lint:fix
```

### Formatting

```bash
# Format code with Prettier
npm run format

# Check formatting
npm run format:check
```

### TypeScript

```bash
# Type check
npm run type-check

# Build with type checking
npm run build
```

## 🚀 Deployment

### Production Build

```bash
# Build all packages
npm run build

# Build Docker images
npm run docker:build
```

### Environment Configuration

For production deployment, ensure all environment variables are properly configured:

- Database connection string
- JWT secret
- AWS credentials
- Redis connection
- CORS origins

## 🐛 Debugging

### Backend Debugging

- Use `console.log()` for basic debugging
- Enable debug mode with `DEBUG=*` environment variable
- Use Prisma Studio for database inspection

### Frontend Debugging

- Use Vue DevTools for component inspection
- Use browser DevTools for network and console debugging
- Enable debug mode with `VITE_ENABLE_DEBUG=true`

### Docker Debugging

```bash
# View container logs
docker logs ai-knowledge-portal-backend
docker logs ai-knowledge-portal-frontend

# Access container shell
docker exec -it ai-knowledge-portal-backend sh
docker exec -it ai-knowledge-portal-frontend sh
```

## 📚 Additional Resources

- [Vue.js 3 Documentation](https://vuejs.org/)
- [Next.js Documentation](https://nextjs.org/docs)
- [PrimeVue Documentation](https://primevue.org/)
- [Prisma Documentation](https://www.prisma.io/docs)
- [Docker Documentation](https://docs.docker.com/)

## 🤝 Contributing

1. Create a feature branch from `main`
2. Make your changes following the coding standards
3. Write tests for new functionality
4. Ensure all tests pass
5. Submit a pull request

### Code Standards

- Use TypeScript throughout
- Follow ESLint rules
- Use Prettier for formatting
- Write meaningful commit messages
- Include tests for new features 