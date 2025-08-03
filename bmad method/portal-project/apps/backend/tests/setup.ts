// Mock environment variables for testing
process.env.DATABASE_URL = 'postgresql://test:test@localhost:5432/test_db'
process.env.JWT_SECRET = 'test-secret-key'
process.env.REDIS_URL = 'redis://localhost:6379'

// Global test setup
beforeAll(() => {
  // Setup any global test configuration
})

afterAll(() => {
  // Cleanup any global test configuration
})

// Mock console methods to reduce noise in tests
global.console = {
  ...console,
  log: jest.fn(),
  debug: jest.fn(),
  info: jest.fn(),
  warn: jest.fn(),
  error: jest.fn(),
} 