import { config } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import PrimeVue from 'primevue/config'

// Configure Vue Test Utils
config.global.plugins = [PrimeVue]

// Setup Pinia for tests
beforeEach(() => {
  setActivePinia(createPinia())
})

// Mock PrimeVue components that might cause issues in tests
config.global.stubs = {
  'Toast': true,
  'ConfirmDialog': true,
  'router-link': true,
  'router-view': true,
} 