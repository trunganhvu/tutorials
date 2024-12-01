<template>
    <div class="_body-home">
      <div class="row __banner">
        <div class="col-xl-12">
          <!--begin:: Widgets/Blog-->
          <div class="m-portlet m-portlet--bordered-semi m-portlet--full-height  m-portlet--rounded-force">
            <div class="m-portlet__body">
              <div class="m-widget19">
                <div class="m-widget19__pic m-portlet-fit--top m-portlet-fit--sides" style="min-height: 286px">
                  <img src="http://hi.gocar.my/wp-content/uploads/2018/12/gocar-background-14.jpg" alt="">
                  <div class="col-sm-12 text-center" style="position: absolute; display: block; top: 10%; z-index: 1;">
                    <h2 class="m--font-light">Cải thiện đáng kể</h2>
                    <h4 class="m--font-light" style="padding-top: 20px; font-weight: lighter">Đi chung xe tiện lợi với</h4>
                  </div>
                  <div class="m-widget19__shadow"></div>
                </div>
              </div>
            </div>
          </div>
          <!--end:: Widgets/Blog-->
        </div>
      </div>
    </div>
  </template>
  
  <script>
    export default {
      name: "Home",
      data() {
        
      },
      methods: {
        
      }
    }
  </script>
  
  <style scoped>
    .__banner {
      margin-right: -80px;
      margin-left: -80px;
    }
    .home-description-active {
      border: 2px solid #ffad4e;
    }
    .home-description {
      padding: 20px 20px;
      margin-bottom: 30px;
      font-size: 15px;
      border-radius: 4px;
      line-height: 1.4;
      cursor: pointer;
      transition: .3s all;
    }
    .home-description-inactive {
      border: 2px solid #dadddd;
      color: #9fa7a6;
    }
    .home-description {
      padding: 20px 20px;
      margin-bottom: 30px;
      font-size: 15px;
      border-radius: 4px;
      line-height: 1.4;
      cursor: pointer;
      transition: .3s all;
    }
    .home-image {
      width: 500px;
      height: 450px;
    }
    .home-image-md {
      width: 330px;
      height: 330px;
    }
    .init-hidden {
      display: none;
    }
    .m-portlet .m-portlet__body {
      padding: 0;
    }
    .home-description-part {
      font-size: 16px;
      line-height: 1.9;
    }
  </style>
  