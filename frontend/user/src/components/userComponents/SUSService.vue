<template>
  <div class="back">
    <vs-table
        v-model="selected"
    >
      <template #header>
        <div class="header-container">
          <vs-input v-model="search" border placeholder="Search"/>
        </div>
      </template>
      <template #thead>
        <vs-tr>
          <vs-th>
            <vs-checkbox
                :indeterminate="selected.length === products.length" v-model="allCheck"
                @change="selected = $vs.checkAll(selected, products)"
            />
          </vs-th>
          <vs-th sort @click="products = $vs.sortData($event ,products, 'productId')">
            {{ $t('lang.orderID') }}
          </vs-th>
          <vs-th sort @click="products = $vs.sortData($event ,products, 'subject')">
            {{ $t('lang.commodity') }}
          </vs-th>
          <vs-th sort @click="products = $vs.sortData($event ,products, 'body')">
            {{ $t('lang.commodityDescription')}}
          </vs-th>
          <vs-th sort @click="products = $vs.sortData($event ,products, 'shop')">
            {{ $t('lang.shop')}}
          </vs-th>
          <vs-th sort @click="products = $vs.sortData($event ,products, 'amount')">
            {{ $t('lang.price') }}
          </vs-th>
          <vs-th>
            {{ $t('lang.image')}}
          </vs-th>

          <vs-th>
            {{ $t('lang.buy')}}
          </vs-th>

        </vs-tr>
      </template>
      <template #tbody>
        <vs-tr
            :key="i"
            v-for="(tr, i) in $vs.getPage($vs.getSearch(products, search), page, max)"
            :data="tr"
            :is-selected="!!selected.includes(tr)"
            not-click-selected
            open-expand-only-td
        >
          <vs-td checkbox>
            <vs-checkbox :val="tr" v-model="selected" />
          </vs-td>
          <vs-td>
            {{ tr.productId}}
          </vs-td>
          <vs-td>
            {{ tr.subject }}
          </vs-td>
          <vs-td>
            {{ tr.body }}
          </vs-td>

          <vs-td>
            {{tr.shop}}
          </vs-td>

          <vs-td>
            {{tr.amount}}
          </vs-td>
          <vs-td>
            <img :src="tr.imageUrl" alt="" width="100px">
          </vs-td>

          <vs-td>
            <el-button @click="buyProduct(tr.productId)">
              {{ $t('lang.buy')}}
            </el-button>
          </vs-td>
        </vs-tr>
      </template>
      <template #footer>
        <vs-pagination v-model="page" :length="$vs.getLength($vs.getSearch(products, search), max)" />
      </template>
    </vs-table>

  </div>
</template>
<script>
import axios from "axios";

export default {
  data:() => ({
    editActive: false,
    edit: null,
    editProp: '',
    search: '',
    allCheck: false,
    page: 1,
    max: 4,
    active: false,
    selected: [],
    products: [
    ]
  }),
  mounted() {
    this.fetchProducts()
  },
  methods:{
    buyProduct(id) {
      axios.get(`http://localhost:8082/payOrder?productId=${id}`).then(response => {
        console.log(response.data.url)
      }).catch(function (error) {
          })
          .finally(function () {
          });
    },
    fetchProducts() {
      this.$http.get('public/product/all').then(res => {
        this.products = res.data;
        console.log(res.data)
      }).catch(err => {
      });
    },
  }
}
</script>



<style scoped>


.back {

  max-width: 100%;
}

</style>