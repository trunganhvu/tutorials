<template>
    <!-- BEGIN: Subheader -->
    <div class="m-subheader ">
      <div class="d-flex align-items-center">
        <div class="mr-auto" v-if="titlePage">
          <h3 class="m-subheader__title m-subheader__title--separator">{{titlePage}}</h3>
          <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
            <li class="m-nav__item m-nav__item--home">
              <a href="#" class="m-nav__link m-nav__link--icon">
                <i class="m-nav__link-icon la la-home"></i>
              </a>
            </li>
            <li class="m-nav__separator">-</li>
            <li class="m-nav__item">
              <a href="" class="m-nav__link">
                <span class="m-nav__link-text">Hỗ trợ</span>
              </a>
            </li>
            <li class="m-nav__separator">-</li>
            <li class="m-nav__item">
              <a href="" class="m-nav__link">
                <span class="m-nav__link-text">Đi chung xe</span>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- END: Subheader -->
  </template>
  
  <script>
    export default {
      name: "SubHeader",
      data() {
        return {
          titlePage: null
        }
      },
      mounted() {
        let self = this;
        if (self.$route.path.includes('/home')) {
          self.titlePage = 'Trang chủ';
        } else if (self.$route.path.includes('/user/register')) {
          self.titlePage = 'Tạo tài khoản'
        }
      }
    }
  </script>
  
  <style scoped>
  
  </style>
  