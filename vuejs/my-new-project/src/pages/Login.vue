<template>
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--hor m-login m-login--signin m-login--2 m-login-2--skin-1" id="m_login" >
      <div class="m-grid__item m-grid__item--fluid m-login__wrapper">
        <div class="m-login__container">
          <!-- LOGIN: BEGIN -->
          <div class="m-login__signin" v-if="showFormLogin">
            <div class="m-login__head">
              <h3 class="m-login__title">System</h3>
            </div>
            <form class="m-login__form m-form">
              <div class="form-group m-form__group">
                <input id="user_name" class="form-control m-input" type="text" placeholder="Email" autocomplete="off">
              </div>
              <div class="form-group m-form__group">
                <input id="pass_word" class="form-control m-input m-login__form-input--last" type="password" placeholder="Password">
              </div>
              <div class="form-group m-form__group">
                <p v-if="messageError" class="m--font-danger message-error">{{messageError}}</p>
              </div>
              <div class="row m-login__form-sub">
                <div class="col m--align-right m-login__form-right">
                  <a href="javascript:;" id="m_login_forget_password" class="m-link">Quên mật khẩu ?</a>
                </div>
              </div>
              <div class="m-login__form-action">
                <button type="button" id="button_login_submit" v-on:click="getToken" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air m-login__btn m-login__btn--primary">
                  Đăng nhập
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
    import http from '../services/http-common.js';
    import {DEFAULT_LOGIN, AUTHORIZATION} from '../services/variables.js';
  
    export default {
      name: "login",
      data() {
        return {
          messageError: null,
          userLoginResponse: {
            token: null,
            userInformation: {
              userId: null,
              username: null,
              image: null,
              email: null
            }
          },
          registerInformation: {
            username: null,
            email: null,
            password: null,
            phone: null
          },
          showFormLogin: true,
          previewImage: null,
          formData: new FormData()
        }
      },
      methods: {
        getToken() {
          let self = this;
          let dataUserLogin = {
            email: document.getElementById("user_name").value,
            password: document.getElementById("pass_word").value
          };
          http.post("/authentication/token", dataUserLogin)
              .then(success => {
                self.userLoginResponse = JSON.parse(success.data.metadata);
                localStorage.setItem("userId", self.userLoginResponse.userInformation.userId);
                localStorage.setItem("username", self.userLoginResponse.userInformation.username);
                localStorage.setItem("image", self.userLoginResponse.userInformation.image);
                localStorage.setItem("email", self.userLoginResponse.userInformation.email);
                localStorage.setItem(AUTHORIZATION, self.userLoginResponse.token);
                window.location.href = DEFAULT_LOGIN;
              })
              .catch(error => {
                console.error(error);
                this.messageError = error.response.data.message;
              });
        },
        setDataToFormRequest: function () {
          this.formData.append("username", this.registerInformation.username);
          this.formData.append("email", this.registerInformation.email);
          this.formData.append("password", this.registerInformation.password);
          this.formData.append("phone", this.registerInformation.phone);
        },
        showFormRegister: function () {
          let self = this;
          self.showFormLogin = false;
        },
        onImageChange: function(e) {
          let self = this;
          let files = e.target.files || e.dataTransfer.files;
          if (!files.length) {
            return;
          }
          self.formData.append("image", files[0]);
          self.createImage(files[0]);
        },
        createImage: function(file) {
          let reader = new FileReader();
          let self = this;
          reader.onload = (e) => {
            self.previewImage = e.target.result;
          };
          reader.readAsDataURL(file);
        },
      },
      mounted() {
        let self = this;
        document.addEventListener("keydown", function (event) {
          if (event.key === "Enter") {
            console.log("Enter");
            self.getToken();
          }
        })
      }
    }
  </script>
  
  <style scoped>
    
  </style>
  