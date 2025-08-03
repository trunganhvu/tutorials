import { test, expect } from '@playwright/test'

test.describe('Home Page', () => {
  test('should display welcome message', async ({ page }) => {
    await page.goto('/')
    
    // Check if the welcome message is displayed
    await expect(page.getByText('Welcome to AI Knowledge Portal')).toBeVisible()
  })

  test('should have navigation buttons', async ({ page }) => {
    await page.goto('/')
    
    // Check if the View Articles button is present
    await expect(page.getByRole('button', { name: 'View Articles' })).toBeVisible()
  })

  test('should redirect to login when not authenticated', async ({ page }) => {
    await page.goto('/')
    
    // Since we're not authenticated, we should be redirected to login
    // This test will need to be updated based on the actual authentication flow
    await expect(page).toHaveURL(/.*login.*/)
  })
}) 