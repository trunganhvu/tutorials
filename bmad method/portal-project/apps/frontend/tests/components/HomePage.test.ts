import { describe, it, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import HomePage from '@/pages/HomePage.vue'

describe('HomePage', () => {
  it('renders welcome message', () => {
    const wrapper = mount(HomePage)
    expect(wrapper.text()).toContain('Welcome to AI Knowledge Portal')
  })

  it('renders navigation buttons', () => {
    const wrapper = mount(HomePage)
    expect(wrapper.find('button').exists()).toBe(true)
  })

  it('shows admin button when user is admin', () => {
    const wrapper = mount(HomePage)
    // Mock the auth store to return admin role
    const authStore = useAuthStore()
    authStore.setUser({
      id: '1',
      username: 'admin',
      email: 'admin@example.com',
      role: 'ADMIN',
      createdAt: '2024-01-01',
      updatedAt: '2024-01-01'
    })
    
    expect(wrapper.find('[data-testid="admin-button"]').exists()).toBe(true)
  })
}) 