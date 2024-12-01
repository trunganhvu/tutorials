import { createRouter, createWebHistory } from "vue-router";
import Home from "./pages/Home.vue";
import Login from "./pages/Login.vue";
import ListScreen from "./pages/ListScreen.vue";

import { URL_PAGE_LOGIN, URL_PAGE_REGISTER, AUTHORIZATION, DEFAULT_LOGIN } from "./services/variables.js";
import DetailScreen from "./pages/DetailScreen.vue";

// Định nghĩa các route
const routes = [
  {
    path: "/",
    name: "home",
    alias: "/home",
    component: Home,
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    path: '/list',
    name: 'list',
    component: ListScreen,
  },
  {
    path: '/detail',
    name: 'detail',
    component: DetailScreen,
  },
];

// Tạo instance của router
const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Middleware beforeEach: chạy trước khi chuyển route
router.beforeEach((to, from, next) => {
  // Xác định route có yêu cầu đăng nhập hay không
  const publicPages = [URL_PAGE_LOGIN, URL_PAGE_REGISTER];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem(AUTHORIZATION);

  // Chuyển hướng nếu người dùng không đăng nhập
  if (authRequired && !true) {
    console.log('111');
    
    return next(URL_PAGE_LOGIN);
  } else if (!authRequired && loggedIn) {
    return next(DEFAULT_LOGIN);
  }

  // Tiếp tục nếu không có vấn đề
  next();
});

export default router;
