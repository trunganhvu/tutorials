<template>
    <div>
      <Button label="Back" @click="goBack" class="p-mb-4" />
      <div v-if="detail">
        <h2>{{ detail.name }}</h2>
        <p><strong>Country:</strong> {{ detail.country }}</p>
        <p><strong>Alpha Two Code:</strong> {{ detail.alpha_two_code }}</p>
        <p><strong>Domains:</strong> {{ detail.domains.join(', ') }}</p>
        <p><strong>Web Pages:</strong> {{ detail.web_pages.join(', ') }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import http from '../services/http-common.js';
  
  export default {
    name: 'DetailScreen',
    data() {
      return {
        detail: null
      };
    },
    methods: {
      goBack() {
        this.$router.go(-1); // Quay lại màn hình trước
      },
      fetchDetail() {
        const { country, name } = this.$route.query;
        http.get(`/search?country=${country}&name=${name}`)
          .then(response => {
            if (response.data.length > 0) {
              this.detail = response.data[0];
            }
          })
          .catch(error => {
            console.error(error);
          });
      }
    },
    mounted() {
      this.fetchDetail();
    }
  };
  </script>
  