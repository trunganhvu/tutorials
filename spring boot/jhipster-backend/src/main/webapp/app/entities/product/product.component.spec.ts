import { vitest } from 'vitest';
import { type MountingOptions, shallowMount } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Product from './product.vue';
import ProductService from './product.service';
import AlertService from '@/shared/alert/alert.service';

type ProductComponentType = InstanceType<typeof Product>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Product Management Component', () => {
    let productServiceStub: SinonStubbedInstance<ProductService>;
    let mountOptions: MountingOptions<ProductComponentType>['global'];

    beforeEach(() => {
      productServiceStub = sinon.createStubInstance<ProductService>(ProductService);
      productServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          productService: () => productServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        productServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Product, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(productServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.products[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProductComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Product, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        productServiceStub.retrieve.reset();
        productServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        productServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProduct();
        await comp.$nextTick(); // clear components

        // THEN
        expect(productServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(productServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
