<template>
  <div id="app" class="m-grid m-grid--hor m-grid--root m-page">
    <!-- PAGES: BEGIN -->
    <!-- <login-page v-if="isPageLogin"></login-page> -->
    <header-user-app v-if="isUserPage"
        v-on:showQuickSidebar="showQuickSidebar = $event">
    </header-user-app>
    <body-user-app v-if="isUserPage"></body-user-app>

    <!-- <keep-alive>
      <router-view v-if="isUserPage"></router-view>
    </keep-alive> -->

    <!-- <router-view v-slot="{ Component }">
      <keep-alive>
        <component :is="Component" />
      </keep-alive>
    </router-view> -->
    <footer-user-app v-if="isUserPage"></footer-user-app>

    <!-- begin::Scroll Top -->
    <div id="m_scroll_top" class="m-scroll-top">
      <i class="la la-arrow-up"></i>
    </div>
    <!-- end::Scroll Top -->

  </div>
</template>

<script>
  import HeaderUser from './components/layouts/user/Header';
  import BodyUser from './components/layouts/user/Body';
  import FooterUser from './components/layouts/user/Footer';
  // import QuickSidebar from './components/layouts/QuickSidebar';
  // import Chat from './components/layouts/Chat';

  // import PageLogin from './pages/Login.vue';
  // import PageNotFound from './pages/404.vue';

  export default {
    name: "app",
    components: {
      'header-user-app': HeaderUser,
      'body-user-app': BodyUser,
      'footer-user-app': FooterUser
    },
    data() {
      return {
        isPageLogin: this.$route.path.includes('login'),
        isPageNotFound: this.$route.path.includes('page-not-found'),
        isUserPage: !this.$route.path.includes('login') && !this.$route.path.includes('page-not-found'),
        modal: {
          title: 'title - update ',
          content: 'content - update',
          url: 'localhost',
          method: 'POST'
        },
        showQuickSidebar: false
      }
    },
    methods: {
    },
    mounted() {
    }
  };
</script>

<style scoped>
</style>
