import { createApp } from 'vue'
import App from './App.vue'
import router from "./router";
// import "../node_modules/handsontable/dist/handsontable.full.css";
// import "../node_modules/handsontable/dist/handsontable.full.js";
import PrimeVue from 'primevue/config';
// import Aura from '@primevue/themes/aura';


export const eventBus = createApp({});
const app = createApp(App);
app.use(router);
app.use(PrimeVue);
// app.use(PrimeVue, {
//     theme: {
//         preset: Aura
//     }
// });
app.mount("#app");
