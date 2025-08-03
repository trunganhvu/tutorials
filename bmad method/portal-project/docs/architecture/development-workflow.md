# Development Workflow

## Local Development Setup

**Prerequisites:**
```bash
# Required software
node >= 18.0.0
npm >= 9.0.0
docker >= 20.0.0
docker-compose >= 2.0.0
postgresql >= 15.0.0
```

**Initial Setup:**
```bash
# Clone repository
git clone <repository-url>
cd ai-knowledge-portal

# Install dependencies
npm install

# Setup environment
cp .env.example .env
# Edit .env with your configuration

# Setup database
cd apps/backend
npx prisma migrate dev
npx prisma generate

# Start development servers
npm run dev
```

**Development Commands:**
```bash
# Start all services
npm run dev

# Start frontend only
npm run dev:frontend

# Start backend only
npm run dev:backend

# Run tests
npm run test
npm run test:frontend
npm run test:backend
npm run test:e2e
```

## Environment Configuration

**Required Environment Variables:**
```bash
# Frontend (.env.local)
VITE_API_BASE_URL=http://localhost:3000/api
VITE_APP_NAME=AI Knowledge Portal

# Backend (.env)
DATABASE_URL=postgresql://user:password@localhost:5432/knowledge_portal
JWT_SECRET=your-super-secret-jwt-key
AWS_ACCESS_KEY_ID=your-aws-access-key
AWS_SECRET_ACCESS_KEY=your-aws-secret-key
AWS_REGION=us-east-1
AWS_S3_BUCKET=knowledge-portal-files
REDIS_URL=redis://localhost:6379

# Shared
NODE_ENV=development
``` 