import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './index.css'                 // Tailwind
import PrimeVue from 'primevue/config'
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import Button from 'primevue/button'
import InputText from 'primevue/inputtext'
import Aura from '@primeuix/themes/aura'

// PrimeVue CSS: theme + core + icons
// import 'primevue/resources/themes/saga-blue/theme.css' // bạn có thể chọn theme khác
// import 'primevue/resources/primevue.min.css'
// import 'primeicons/primeicons.css'
// import 'primeflex/primeflex.css' // nếu dùng primeflex
const app = createApp(App)
app.use(router)
app.use(PrimeVue, {
    theme: {
        preset: Aura
    }
});

app.component('DataTable', DataTable)
app.component('Column', Column)
app.component('PButton', Button)
app.component('InputText', InputText)

if (import.meta.env.DEV) {
  // only start MSW in dev — file src/mocks/browser.js sẽ export worker
  import('./mocks/browser').then(({ worker }) => {
    worker.start({ onUnhandledRequest: 'bypass' })
  })
}

app.mount('#app')
