package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.ProductAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static com.mycompany.myapp.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Product;
import com.mycompany.myapp.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProductResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    private static final String ENTITY_API_URL = "/api/products";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductMockMvc;

    private Product product;

    private Product insertedProduct;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createEntity() {
        return new Product().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).price(DEFAULT_PRICE).stock(DEFAULT_STOCK);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createUpdatedEntity() {
        return new Product().name(UPDATED_NAME).description(UPDATED_DESCRIPTION).price(UPDATED_PRICE).stock(UPDATED_STOCK);
    }

    @BeforeEach
    void initTest() {
        product = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedProduct != null) {
            productRepository.delete(insertedProduct);
            insertedProduct = null;
        }
    }

    @Test
    @Transactional
    void createProduct() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Product
        var returnedProduct = om.readValue(
            restProductMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(product)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Product.class
        );

        // Validate the Product in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProductUpdatableFieldsEquals(returnedProduct, getPersistedProduct(returnedProduct));

        insertedProduct = returnedProduct;
    }

    @Test
    @Transactional
    void createProductWithExistingId() throws Exception {
        // Create the Product with an existing ID
        product.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        product.setName(null);

        // Create the Product, which fails.

        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(product)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriceIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        product.setPrice(null);

        // Create the Product, which fails.

        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(product)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStockIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        product.setStock(null);

        // Create the Product, which fails.

        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(product)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProducts() throws Exception {
        // Initialize the database
        insertedProduct = productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(sameNumber(DEFAULT_PRICE))))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)));
    }

    @Test
    @Transactional
    void getProduct() throws Exception {
        // Initialize the database
        insertedProduct = productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc
            .perform(get(ENTITY_API_URL_ID, product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.price").value(sameNumber(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK));
    }

    @Test
    @Transactional
    void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProduct() throws Exception {
        // Initialize the database
        insertedProduct = productRepository.saveAndFlush(product);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).price(UPDATED_PRICE).stock(UPDATED_STOCK);

        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProduct.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProductToMatchAllProperties(updatedProduct);
    }

    @Test
    @Transactional
    void putNonExistingProduct() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        product.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(put(ENTITY_API_URL_ID, product.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProduct() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        product.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(product))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProduct() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        product.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(product)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductWithPatch() throws Exception {
        // Initialize the database
        insertedProduct = productRepository.saveAndFlush(product);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the product using partial update
        Product partialUpdatedProduct = new Product();
        partialUpdatedProduct.setId(product.getId());

        partialUpdatedProduct.price(UPDATED_PRICE);

        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProductUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedProduct, product), getPersistedProduct(product));
    }

    @Test
    @Transactional
    void fullUpdateProductWithPatch() throws Exception {
        // Initialize the database
        insertedProduct = productRepository.saveAndFlush(product);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the product using partial update
        Product partialUpdatedProduct = new Product();
        partialUpdatedProduct.setId(product.getId());

        partialUpdatedProduct.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).price(UPDATED_PRICE).stock(UPDATED_STOCK);

        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProductUpdatableFieldsEquals(partialUpdatedProduct, getPersistedProduct(partialUpdatedProduct));
    }

    @Test
    @Transactional
    void patchNonExistingProduct() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        product.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, product.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(product))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProduct() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        product.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(product))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProduct() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        product.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(product)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Product in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProduct() throws Exception {
        // Initialize the database
        insertedProduct = productRepository.saveAndFlush(product);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the product
        restProductMockMvc
            .perform(delete(ENTITY_API_URL_ID, product.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return productRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Product getPersistedProduct(Product product) {
        return productRepository.findById(product.getId()).orElseThrow();
    }

    protected void assertPersistedProductToMatchAllProperties(Product expectedProduct) {
        assertProductAllPropertiesEquals(expectedProduct, getPersistedProduct(expectedProduct));
    }

    protected void assertPersistedProductToMatchUpdatableProperties(Product expectedProduct) {
        assertProductAllUpdatablePropertiesEquals(expectedProduct, getPersistedProduct(expectedProduct));
    }
}
