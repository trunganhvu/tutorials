# Coding Standards

## Critical Fullstack Rules

- **Type Sharing:** Always define types in packages/shared and import from there
- **API Calls:** Never make direct HTTP calls - use the service layer
- **Environment Variables:** Access only through config objects, never process.env directly
- **Error Handling:** All API routes must use the standard error handler
- **State Updates:** Never mutate state directly - use proper state management patterns
- **Authentication:** Always verify JWT tokens and check user permissions
- **File Uploads:** Validate file types and sizes before processing
- **Database Queries:** Use Prisma ORM for all database operations

## Naming Conventions

| Element | Frontend | Backend | Example |
|---------|----------|---------|---------|
| Components | PascalCase | - | `UserProfile.vue` |
| Hooks | camelCase with 'use' | - | `useAuth.ts` |
| API Routes | - | kebab-case | `/api/user-profile` |
| Database Tables | - | snake_case | `user_profiles` |
| Services | camelCase | camelCase | `userService.ts` |
| Types | PascalCase | PascalCase | `UserProfile` | 