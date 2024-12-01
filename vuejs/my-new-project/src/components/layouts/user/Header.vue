<template>
    <!-- begin::Header -->
    <header id="m_header" class="m-grid__item m-header " m-minimize="minimize" m-minimize-mobile="minimize" m-minimize-offset="200" m-minimize-mobile-offset="200">
      <div class="m-container m-container--fluid m-container--full-height">
        <div class="m-stack m-stack--ver m-stack--desktop  m-header__wrapper">
  
          <div class="m-stack__item m-stack__item--middle m-stack__item--left m-header-head" id="m_header_nav">
            <div class="m-stack m-stack--ver m-stack--desktop">
              <div class="m-stack__item m-stack__item--fluid">
                <!-- BEGIN: Horizontal Menu -->
                <button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id="m_aside_header_menu_mobile_close_btn"><i class="la la-close"></i></button>
                <div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark">
                  <ul class="m-menu__nav  m-menu__nav--submenu-arrow ">
                    <!-- Menu Home: BEGIN -->
                    <li class="m-menu__item  m-menu__item--submenu m-menu__item--rel">
                      <a href="/home" class="m-menu__link" title="Non functional dummy link">
                        <span class="m-menu__item-here"></span>
                        <i class="m-menu__link-icon flaticon-home"></i>
                        <span class="m-menu__link-text">Trang chủ</span>
                      </a>
                    </li>
                  </ul>
                </div>
                <!-- END: Horizontal Menu -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- end::Header -->
  </template>
  
  <script>
    import { URL_AVATAR } from '../../../services/variables.js';
  
    export default {
      name: "Header",
      data() {
        return {
          username: localStorage.getItem("username"),
          email: localStorage.getItem("email"),
          avatar: URL_AVATAR + localStorage.getItem("image"),
          unread: null,
          notifications: []
        }
      },
      methods: {
        logout() {
          localStorage.clear();
          window.location.href = '/login';
        },
        showQuickSidebar: function() {
          let self = this;
          self.$emit('showQuickSidebar', true);
        }
        
      },
      mounted() {
      }
    }
  </script>
  
  <style scoped>
    .m-list-timeline m-list-timeline--skin-light {
      height: 100%;
      max-height: 300px;
    }
    .m-list-timeline__items {
      height: inherit;
    }
    .m-list-timeline__items .m-list-timeline__item .m-list-timeline__time {
      width: 110px;
    }
  </style>
  