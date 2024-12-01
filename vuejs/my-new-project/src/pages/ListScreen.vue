<template>
  <div class="">
    <Tabs v-model="activeTab">
      <TabList>
          <Tab value="0">Header I</Tab>
          <Tab value="1">Header II</Tab>
      </TabList>
      <TabPanels>
          <TabPanel value="0">
            <InputText type="text" 
              v-model="paramsT1.country"  
              placeholder="Search by country..." 
              variant="filled"
                  />
            
            <DataTable :value="productsT1" tableStyle="min-width: 50rem"
              @sort="onSortTab1"
              @row-click="viewDetail">
              <Column field="name" header="Name"></Column>
              <Column field="country" header="Country"></Column>
              <Column field="web_pages" header="Web Pages"></Column>
              <Column field="alpha_two_code" header="Code"></Column>
            </DataTable>
          </TabPanel>
          <TabPanel value="1">
            <InputText type="text" 
              v-model="paramsT2.country"  
              placeholder="Search by country..." 
              variant="filled"
                  />
            <Button
              label="Search"
              icon="pi pi-search"
              unstyled
              @click="getList(2)"
              pt:root="bg-teal-500 hover:bg-teal-700 active:bg-teal-900 cursor-pointer py-2 px-4 rounded-full border-0 flex gap-2"
              pt:label="text-white font-bold text-lg"
              pt:icon="text-white text-xl"
              />
            <DataTable :value="productsT2" tableStyle="min-width: 50rem"
              @sort="onSortTab1"
              @row-click="viewDetail">
              <Column field="name" header="Name"></Column>
              <Column field="country" header="Country"></Column>
              <Column field="web_pages" header="Web Pages"></Column>
              <Column field="alpha_two_code" header="Code"></Column>
            </DataTable>
          </TabPanel>
      </TabPanels>
    </Tabs>
    

  </div>
  
</template>
<script>
import { ref } from "vue";
import { DataTable, Column, InputText, Button }  from 'primevue';
import http from '../services/http-common.js';
import { debounce } from 'lodash';
import Tabs from 'primevue/tabs';
import TabList from 'primevue/tablist';
import Tab from 'primevue/tab';
import TabPanels from 'primevue/tabpanels';
import TabPanel from 'primevue/tabpanel';

const tab1SortField = ref("name");
const tab1SortOrder = ref(1);

export default {
    name: "ListScreen",
    components: {
      DataTable,
      InputText,
      Column,
      Tabs,
      TabList,
      Tab,
      TabPanels,
      TabPanel,
      Button
    },
    data() {
      return {
        activeTab: 0,
        totalRecord: 10,
        productsT1: [],
        productsT2: [],
        paramsT1: {
          country: 'vietnam'
        },
        paramsT2: {
          country: 'Laos'
        },
      }
    },
    methods: {
      getList: function (typeTab) {
        let self = this;
        console.log(typeTab);
        
        const searchObj = (typeTab === 1) ? this.paramsT1 : this.paramsT2;
        console.log(searchObj);
        
        let queryString = Object.keys(searchObj).map(key => key + '=' + searchObj[key]).join('&');
        http.get('/search?' + queryString)
            .then(response => {
              if (response.data.length > 0) {
                if (typeTab === 1) {
                  this.setResultTab1(response);
                } else if (typeTab === 2) {
                  this.setResultTab2(response);
                }
              } else {
                if (typeTab === 1) {
                  self.productsT1 = []
                } else if (typeTab === 2) {
                  self.productsT2 = []
                }
                alert('No results')
                
              }
            })
            .catch(e => {
              console.error(e);
            });
      },
      onSortTab1: (event) => {
        tab1SortField.value = event.sortField;
        tab1SortOrder.value = event.sortOrder;
        this.getList();
      },
      setResultTab1: function(response) {
        this.productsT1 = response.data.map(item => ({
          name: item.name,
          country: item.country,
          web_pages: item.web_pages.join(", "),  // Nối các URL thành chuỗi
          alpha_two_code: item.alpha_two_code
        }));
      },
      setResultTab2: function(response) {
        this.productsT2 = response.data.map(item => ({
          name: item.name,
          country: item.country,
          web_pages: item.web_pages.join(", "),  // Nối các URL thành chuỗi
          alpha_two_code: item.alpha_two_code
        }));
      },
      debouncedSearch: debounce(function() {
        this.getList(1); // Gọi getList sau khi người dùng ngừng gõ
      }, 900),
      viewDetail(rowData) {
        this.$router.push({
          name: 'detail', // Name of router.js
          query: {
            country: rowData.data.country,
            name: rowData.data.name,
            tab: this.activeTab
          }
        });
      },

      setActiveTab() {
        console.log(this.activeTab);
        
        this.activeTab = Number(this.$route.query.tab) || 0;
        console.log(this.activeTab);
      },
    },
    watch: {
      // Theo dõi thay đổi của params.country
      'paramsT1.country': function() {
        this.debouncedSearch(); // Gọi lại hàm getList khi country thay đổi
      },
      'activeTab': function(newVal) {
        console.log('newVal', newVal);
        
        if (newVal === 1) {
          // Call getList for Tab 1 when activeTab switches to 1
          this.getList(2);
        }
      },
    },
    mounted() {
      this.getList(1);
      // this.getList(2);
      
      this.setActiveTab();
      
    }

}
</script>