import { PrismaClient } from '@prisma/client';
import bcrypt from 'bcryptjs';

const prisma = new PrismaClient();

async function main() {
  console.log('🌱 Starting database seeding...');

  // Create initial admin user
  const adminPassword = 'admin123'; // Change this in production
  const hashedPassword = await bcrypt.hash(adminPassword, 12);

  const adminUser = await prisma.user.upsert({
    where: { email: 'admin@ai-knowledge-portal.com' },
    update: {},
    create: {
      username: 'admin',
      email: 'admin@ai-knowledge-portal.com',
      passwordHash: hashedPassword,
      role: 'ADMIN',
    },
  });

  console.log('✅ Admin user created:', adminUser.email);
  console.log('📝 Admin password:', adminPassword);

  // Create sample article
  const sampleArticle = await prisma.article.upsert({
    where: { slug: 'welcome-to-ai-knowledge-portal' },
    update: {},
    create: {
      title: 'Welcome to AI Knowledge Portal',
      content: `
        <h1>Welcome to AI Knowledge Portal</h1>
        <p>This is your central hub for AI knowledge, guides, and insights.</p>
        <p>As an admin, you can:</p>
        <ul>
          <li>Create and edit articles</li>
          <li>Manage user accounts</li>
          <li>Upload files and resources</li>
          <li>Monitor system usage</li>
        </ul>
        <p>Get started by creating your first article!</p>
      `,
      slug: 'welcome-to-ai-knowledge-portal',
      status: 'PUBLISHED',
      authorId: adminUser.id,
    },
  });

  console.log('✅ Sample article created:', sampleArticle.title);

  console.log('🎉 Database seeding completed!');
}

main()
  .catch((e) => {
    console.error('❌ Error during seeding:', e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  }); 