import { defineComponent, provide } from 'vue';

import ProductService from './product/product.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('productService', () => new ProductService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
