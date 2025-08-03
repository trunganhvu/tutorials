# Technical Assumptions

## Repository Structure: Monorepo
- Single repository containing both frontend and backend code
- Shared configuration and build processes
- Simplified deployment and version management

## Service Architecture
- **Monolithic Backend**: Next.js API routes for all backend functionality
- **Frontend**: Vue.js 3 SPA with Vite build system
- **Database**: PostgreSQL with Prisma ORM
- **File Storage**: AWS S3 integration
- **Authentication**: JWT-based with bcrypt password hashing

## Testing Requirements
- **Unit Testing**: Component and function-level tests
- **Integration Testing**: API endpoint and database integration tests
- **E2E Testing**: Critical user journey testing
- **Manual Testing**: User acceptance testing for new features

## Additional Technical Assumptions and Requests
- TypeScript for type safety throughout the application
- PrimeVue UI components for consistent design
- Pinia for state management in Vue.js
- Docker multi-stage builds for optimization
- Kubernetes manifests for deployment
- AWS EKS for container orchestration
- AWS RDS for PostgreSQL database
- AWS ALB for load balancing 