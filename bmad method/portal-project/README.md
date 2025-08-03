# AI Knowledge Portal

An internal knowledge sharing platform for AI concepts, guides, and news. Built with modern web technologies to provide a secure, scalable, and user-friendly experience for sharing AI knowledge across the organization.

## 🚀 Features

- **User Authentication**: Secure JWT-based authentication with role-based access control
- **Article Management**: Create, edit, and manage AI knowledge articles
- **File Storage**: AWS S3 integration for document and image storage
- **Search Functionality**: Real-time search across article titles
- **Responsive Design**: Modern UI built with PrimeVue components
- **Role-Based Access**: Admin and User roles with appropriate permissions

## 🛠️ Technology Stack

### Frontend
- **Vue.js 3** with Composition API
- **TypeScript** for type safety
- **PrimeVue** for UI components
- **Pinia** for state management
- **Vite** for build tooling

### Backend
- **Next.js 14** with API routes
- **TypeScript** for type safety
- **Prisma ORM** for database operations
- **PostgreSQL** for data storage
- **JWT** for authentication

### Infrastructure
- **Docker** for containerization
- **Kubernetes** for orchestration
- **AWS S3** for file storage
- **AWS EKS** for deployment

## 📁 Project Structure

```
ai-knowledge-portal/
├── apps/
│   ├── frontend/          # Vue.js 3 + Vite
│   └── backend/           # Next.js + TypeScript
├── packages/
│   ├── shared/            # Shared types/utilities
│   └── config/            # Shared configuration
├── infrastructure/        # Docker, Kubernetes configs
└── docs/                  # Documentation
```

## 🚀 Quick Start

### Prerequisites

- Node.js >= 18.0.0
- npm >= 9.0.0
- Docker >= 20.0.0
- PostgreSQL >= 15.0.0

### Installation

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
   cp .env.example .env
   # Edit .env with your configuration
   ```

4. **Set up database**
   ```bash
   npm run db:migrate
   npm run db:seed
   ```

5. **Start development servers**
   ```bash
   npm run dev
   ```

The application will be available at:
- Frontend: http://localhost:5173
- Backend API: http://localhost:3000

## 🧪 Testing

```bash
# Run all tests
npm run test

# Run frontend tests
npm run test:frontend

# Run backend tests
npm run test:backend

# Run E2E tests
npm run test:e2e
```

## 🐳 Docker

```bash
# Build containers
npm run docker:build

# Start services
npm run docker:up

# View logs
npm run docker:logs

# Stop services
npm run docker:down
```

## 📚 Development

### Available Scripts

- `npm run dev` - Start both frontend and backend in development mode
- `npm run build` - Build all packages for production
- `npm run test` - Run all tests
- `npm run lint` - Run linting across all packages
- `npm run format` - Format code with Prettier

### Code Standards

- **TypeScript** throughout the application
- **ESLint** for code linting
- **Prettier** for code formatting
- **Conventional commits** for commit messages
- **Component-based architecture** for frontend
- **Repository pattern** for backend

## 🔧 Configuration

### Environment Variables

#### Frontend (.env.local)
```
VITE_API_BASE_URL=http://localhost:3000/api
VITE_APP_NAME=AI Knowledge Portal
```

#### Backend (.env)
```
DATABASE_URL=postgresql://user:password@localhost:5432/knowledge_portal
JWT_SECRET=your-super-secret-jwt-key
AWS_ACCESS_KEY_ID=your-aws-access-key
AWS_SECRET_ACCESS_KEY=your-aws-secret-key
AWS_REGION=us-east-1
AWS_S3_BUCKET=knowledge-portal-files
REDIS_URL=redis://localhost:6379
NODE_ENV=development
```

## 📖 Documentation

- [Product Requirements Document](./docs/prd/) - Detailed product specifications
- [Architecture Documentation](./docs/architecture/) - Technical architecture and design
- [Development Stories](./docs/stories/) - Implementation stories and tasks

## 🤝 Contributing

1. Create a feature branch from `main`
2. Make your changes following the coding standards
3. Write tests for new functionality
4. Ensure all tests pass
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

For questions or issues, please contact the development team or create an issue in the repository. 