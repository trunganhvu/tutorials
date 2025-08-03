# Unified Project Structure

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