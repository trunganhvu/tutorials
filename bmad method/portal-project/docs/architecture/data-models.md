# Data Models

## User Model

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

## Article Model

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

## FileAttachment Model

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