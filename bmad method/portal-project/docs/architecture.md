# AI Knowledge Portal Fullstack Architecture Document

## Introduction

This document outlines the complete fullstack architecture for the AI Knowledge Portal, including backend systems, frontend implementation, and their integration. It serves as the single source of truth for AI-driven development, ensuring consistency across the entire technology stack.

### Starter Template or Existing Project

**Analysis**: Based on the PRD, this is a **greenfield project** with no existing starter template mentioned. The project will be built from scratch using:
- Next.js backend with TypeScript and Prisma
- Vue.js 3 frontend with Vite, PrimeVue, and Pinia
- PostgreSQL database
- AWS infrastructure with Docker and Kubernetes

**Decision**: N/A - Greenfield project

### Change Log

| Date | Version | Description | Author |
|------|---------|-------------|--------|
| 2024-01-01 | 1.0 | Initial architecture creation | Architect Agent |

## High Level Architecture

### Technical Summary

The AI Knowledge Portal will be built as a **modern full-stack web application** using a **monolithic backend architecture** with Next.js API routes and a **single-page application (SPA) frontend** with Vue.js 3. The system will be deployed on **AWS infrastructure** using **Docker containerization** and **Kubernetes orchestration** for scalability and maintainability.

**Key Integration Points**: The frontend will communicate with the backend through **RESTful API endpoints**, with **JWT-based authentication** and **role-based access control**. File storage will be handled through **AWS S3 integration** with presigned URLs for secure access.

**Infrastructure Platform**: AWS with EKS for container orchestration, RDS for PostgreSQL database, S3 for file storage, and ALB for load balancing. This architecture achieves the PRD goals by providing a scalable, secure, and maintainable platform for internal AI knowledge sharing.

### Platform and Infrastructure Choice

**Platform:** AWS Full Stack  
**Key Services:** EKS, RDS, S3, ALB, CloudWatch  
**Deployment Host and Regions:** AWS EKS, Multi-region capable  

### Repository Structure

**Structure:** Monorepo  
**Monorepo Tool:** npm workspaces (lightweight, no additional tooling required)  
**Package Organization:** apps/ for applications, packages/ for shared code  

### High Level Architecture Diagram

```mermaid
graph TB
    subgraph "Users"
        U[Web Browser]
    end
    
    subgraph "Frontend"
        VUE[Vue.js 3 SPA]
        PV[PrimeVue Components]
        PINIA[Pinia State]
    end
    
    subgraph "Backend"
        NEXT[Next.js API Routes]
        AUTH[JWT Authentication]
        RBAC[Role-Based Access Control]
    end
    
    subgraph "Database"
        PG[(PostgreSQL)]
        PRISMA[Prisma ORM]
    end
    
    subgraph "File Storage"
        S3[AWS S3]
        PRESIGNED[Presigned URLs]
    end
    
    subgraph "Infrastructure"
        EKS[AWS EKS]
        ALB[Application Load Balancer]
        RDS[AWS RDS]
        CW[CloudWatch]
    end
    
    U --> ALB
    ALB --> VUE
    VUE --> NEXT
    NEXT --> AUTH
    NEXT --> RBAC
    NEXT --> PRISMA
    PRISMA --> PG
    NEXT --> S3
    S3 --> PRESIGNED
    VUE --> PRESIGNED
    
    EKS --> ALB
    EKS --> NEXT
    RDS --> PG
    CW --> EKS
```

### Architectural Patterns

- **Monolithic Backend Architecture:** Next.js API routes for all backend functionality - *Rationale:* Simplifies development and deployment for the team size, provides good performance for the expected load
- **Component-Based Frontend:** Vue.js 3 components with PrimeVue library - *Rationale:* Enables reusable UI components, maintains consistency, and supports the responsive design requirements
- **Repository Pattern:** Prisma ORM for data access abstraction - *Rationale:* Provides type-safe database operations and enables easy testing and future migrations
- **JWT Authentication Pattern:** Token-based authentication with role-based access control - *Rationale:* Stateless authentication suitable for containerized deployment, supports the admin/user role requirements
- **API Gateway Pattern:** Next.js API routes as single entry point - *Rationale:* Centralized authentication, rate limiting, and monitoring for all API calls

## Tech Stack

### Technology Stack Table

| Category | Technology | Version | Purpose | Rationale |
|----------|------------|---------|---------|-----------|
| Frontend Language | TypeScript | 5.x | Type safety and developer experience | Provides compile-time error checking and better IDE support |
| Frontend Framework | Vue.js 3 | 3.4+ | Reactive UI framework | Composition API provides better TypeScript support and modern development patterns |
| UI Component Library | PrimeVue | 3.x | Professional UI components | Matches PRD requirements, provides comprehensive component library |
| State Management | Pinia | 2.x | Centralized state management | Modern Vue 3 state management, TypeScript support, dev tools |
| Backend Language | TypeScript | 5.x | Type safety and consistency | Shared language across stack, better error handling |
| Backend Framework | Next.js | 14.x | Full-stack React framework | API routes, SSR capabilities, excellent developer experience |
| API Style | REST | - | HTTP-based API design | Simple, well-understood, good tooling support |
| Database | PostgreSQL | 15+ | Relational database | ACID compliance, excellent performance, rich feature set |
| Cache | Redis | 7.x | Session and data caching | Fast in-memory storage for sessions and frequently accessed data |
| File Storage | AWS S3 | - | Object storage | Scalable, secure, cost-effective file storage |
| Authentication | JWT + bcrypt | - | Token-based authentication | Stateless, secure, suitable for containerized deployment |
| Frontend Testing | Vitest | 1.x | Unit and component testing | Fast, Vue.js optimized testing framework |
| Backend Testing | Jest | 29.x | API and unit testing | Comprehensive testing framework with good mocking capabilities |
| E2E Testing | Playwright | 1.x | End-to-end testing | Cross-browser testing, excellent debugging tools |
| Build Tool | Vite | 5.x | Frontend build tool | Fast development server, optimized production builds |
| Bundler | Webpack (Next.js) | - | Backend bundling | Integrated with Next.js, handles server-side code |
| IaC Tool | Terraform | 1.x | Infrastructure as Code | Declarative infrastructure management, AWS provider |
| CI/CD | GitHub Actions | - | Automated deployment | Integrated with GitHub, supports Docker and Kubernetes |
| Monitoring | CloudWatch | - | AWS monitoring | Native AWS integration, comprehensive metrics |
| Logging | Winston | 3.x | Application logging | Structured logging, multiple transports |
| CSS Framework | PrimeVue CSS | - | Component styling | Integrated with PrimeVue components |

## Data Models

### User Model

**Purpose:** Represents user accounts with authentication and role-based access control

**Key Attributes:**
- `id`: UUID - Unique identifier for the user
- `username`: string - Unique username for login
- `email`: string - User's email address (unique)
- `passwordHash`: string - Bcrypt hashed password
- `role`: enum - User role (ADMIN, USER)
- `createdAt`: DateTime - Account creation timestamp
- `updatedAt`: DateTime - Last update timestamp

**TypeScript Interface:**
```typescript
interface User {
  id: string;
  username: string;
  email: string;
  passwordHash: string;
  role: 'ADMIN' | 'USER';
  createdAt: Date;
  updatedAt: Date;
}
```

**Relationships:**
- One-to-many with Articles (as author)
- One-to-many with Sessions (for JWT management)

### Article Model

**Purpose:** Represents knowledge content with rich text and file attachments

**Key Attributes:**
- `id`: UUID - Unique identifier for the article
- `title`: string - Article title (searchable)
- `content`: string - Rich text content (HTML)
- `slug`: string - SEO-friendly URL slug
- `status`: enum - Publication status (DRAFT, PUBLISHED)
- `authorId`: UUID - Reference to author user
- `createdAt`: DateTime - Creation timestamp
- `updatedAt`: DateTime - Last update timestamp

**TypeScript Interface:**
```typescript
interface Article {
  id: string;
  title: string;
  content: string;
  slug: string;
  status: 'DRAFT' | 'PUBLISHED';
  authorId: string;
  createdAt: Date;
  updatedAt: Date;
  author?: User;
  attachments?: FileAttachment[];
}
```

**Relationships:**
- Many-to-one with User (author)
- One-to-many with FileAttachment

### FileAttachment Model

**Purpose:** Represents files uploaded to AWS S3 and attached to articles

**Key Attributes:**
- `id`: UUID - Unique identifier for the file
- `filename`: string - Original filename
- `s3Key`: string - S3 object key
- `mimeType`: string - File MIME type
- `fileSize`: number - File size in bytes
- `articleId`: UUID - Reference to parent article
- `uploadedAt`: DateTime - Upload timestamp

**TypeScript Interface:**
```typescript
interface FileAttachment {
  id: string;
  filename: string;
  s3Key: string;
  mimeType: string;
  fileSize: number;
  articleId: string;
  uploadedAt: Date;
  article?: Article;
}
```

**Relationships:**
- Many-to-one with Article

## Database Schema

### PostgreSQL Schema

```sql
-- Users table
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER' CHECK (role IN ('ADMIN', 'USER')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Articles table
CREATE TABLE articles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    slug VARCHAR(255) UNIQUE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT' CHECK (status IN ('DRAFT', 'PUBLISHED')),
    author_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- File attachments table
CREATE TABLE file_attachments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    filename VARCHAR(255) NOT NULL,
    s3_key VARCHAR(500) NOT NULL,
    mime_type VARCHAR(100) NOT NULL,
    file_size BIGINT NOT NULL,
    article_id UUID NOT NULL REFERENCES articles(id) ON DELETE CASCADE,
    uploaded_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Sessions table for JWT management
CREATE TABLE sessions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    token_hash VARCHAR(255) NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for performance
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_articles_title ON articles USING gin(to_tsvector('english', title));
CREATE INDEX idx_articles_status ON articles(status);
CREATE INDEX idx_articles_author_id ON articles(author_id);
CREATE INDEX idx_articles_created_at ON articles(created_at DESC);
CREATE INDEX idx_file_attachments_article_id ON file_attachments(article_id);
CREATE INDEX idx_sessions_user_id ON sessions(user_id);
CREATE INDEX idx_sessions_expires_at ON sessions(expires_at);

-- Full-text search index for article titles
CREATE INDEX idx_articles_title_search ON articles USING gin(to_tsvector('english', title));

-- Triggers for updated_at timestamps
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_users_updated_at BEFORE UPDATE ON users
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_articles_updated_at BEFORE UPDATE ON articles
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
```

## Unified Project Structure

```
ai-knowledge-portal/
├── .github/                    # CI/CD workflows
│   └── workflows/
│       ├── ci.yaml
│       └── deploy.yaml
├── apps/                       # Application packages
│   ├── frontend/               # Vue.js 3 frontend
│   │   ├── src/
│   │   │   ├── components/     # UI components
│   │   │   │   ├── common/
│   │   │   │   ├── auth/
│   │   │   │   ├── articles/
│   │   │   │   ├── admin/
│   │   │   │   └── ui/
│   │   │   ├── pages/          # Page components/routes
│   │   │   ├── stores/         # Pinia state management
│   │   │   ├── services/       # API client services
│   │   │   ├── utils/          # Frontend utilities
│   │   │   └── types/          # TypeScript types
│   │   ├── public/             # Static assets
│   │   ├── tests/              # Frontend tests
│   │   └── package.json
│   └── backend/                # Next.js backend
│       ├── src/
│       │   ├── pages/
│       │   │   └── api/        # API routes
│       │   ├── lib/            # Backend utilities
│       │   ├── services/       # Business logic
│       │   ├── middleware/     # API middleware
│       │   └── types/          # TypeScript types
│       ├── prisma/             # Database schema
│       ├── tests/              # Backend tests
│       └── package.json
├── packages/                   # Shared packages
│   ├── shared/                 # Shared types/utilities
│   │   ├── src/
│   │   │   ├── types/          # TypeScript interfaces
│   │   │   ├── constants/      # Shared constants
│   │   │   └── utils/          # Shared utilities
│   │   └── package.json
│   └── config/                 # Shared configuration
│       ├── eslint/
│       ├── typescript/
│       └── jest/
├── infrastructure/             # IaC definitions
│   ├── terraform/
│   ├── kubernetes/
│   └── docker/
├── scripts/                    # Build/deploy scripts
├── docs/                       # Documentation
│   ├── prd.md
│   └── architecture.md
├── .env.example                # Environment template
├── package.json                # Root package.json
├── package-lock.json           # Lock file
└── README.md
```

## Development Workflow

### Local Development Setup

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

### Environment Configuration

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

## Security and Performance

### Security Requirements

**Frontend Security:**
- CSP Headers: Strict Content Security Policy
- XSS Prevention: Input sanitization and output encoding
- Secure Storage: HTTP-only cookies for JWT tokens

**Backend Security:**
- Input Validation: Zod schema validation
- Rate Limiting: 100 requests per minute per IP
- CORS Policy: Restricted to frontend domain

**Authentication Security:**
- Token Storage: HTTP-only cookies with secure flag
- Session Management: JWT with short expiration (15 minutes)
- Password Policy: Minimum 8 characters, complexity requirements

### Performance Optimization

**Frontend Performance:**
- Bundle Size Target: < 500KB gzipped
- Loading Strategy: Code splitting and lazy loading
- Caching Strategy: Service worker for static assets

**Backend Performance:**
- Response Time Target: < 200ms for API calls
- Database Optimization: Proper indexing and query optimization
- Caching Strategy: Redis for session and frequently accessed data

## Coding Standards

### Critical Fullstack Rules

- **Type Sharing:** Always define types in packages/shared and import from there
- **API Calls:** Never make direct HTTP calls - use the service layer
- **Environment Variables:** Access only through config objects, never process.env directly
- **Error Handling:** All API routes must use the standard error handler
- **State Updates:** Never mutate state directly - use proper state management patterns
- **Authentication:** Always verify JWT tokens and check user permissions
- **File Uploads:** Validate file types and sizes before processing
- **Database Queries:** Use Prisma ORM for all database operations

### Naming Conventions

| Element | Frontend | Backend | Example |
|---------|----------|---------|---------|
| Components | PascalCase | - | `UserProfile.vue` |
| Hooks | camelCase with 'use' | - | `useAuth.ts` |
| API Routes | - | kebab-case | `/api/user-profile` |
| Database Tables | - | snake_case | `user_profiles` |
| Services | camelCase | camelCase | `userService.ts` |
| Types | PascalCase | PascalCase | `UserProfile` |

## Next Steps

### Implementation Priority

1. **Phase 1:** Foundation & Authentication
   - Project setup and infrastructure
   - User authentication system
   - Basic user management

2. **Phase 2:** Article Management System
   - Article CRUD operations
   - Rich text editor integration
   - File upload functionality

3. **Phase 3:** Search & User Experience
   - Search functionality
   - UI/UX optimization
   - Performance improvements

4. **Phase 4:** AWS Integration & Deployment
   - S3 file storage integration
   - Docker containerization
   - Kubernetes deployment

### Development Guidelines

- Follow the established coding standards and architectural patterns
- Implement comprehensive testing at all levels
- Use TypeScript for type safety throughout the application
- Maintain security best practices for authentication and file handling
- Monitor performance and optimize based on metrics
- Document all API changes and architectural decisions

This architecture provides a solid foundation for building a scalable, secure, and maintainable AI knowledge portal that meets all the requirements specified in the PRD. 