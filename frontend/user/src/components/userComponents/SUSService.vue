<template>
  <div class="back">
    <div class="box">
      <vs-table>
        <template #thead>
          <vs-tr>
            <vs-th>
              {{ $t('lang.orderID') }}
            </vs-th>
            <vs-th>
              {{ $t('lang.commodity') }}
            </vs-th>
            <vs-th>
              {{ $t('lang.commodityDescription')}}
            </vs-th>
            <vs-th>
              {{ $t('lang.shop')}}
            </vs-th>
            <vs-th>
              {{ $t('lang.price') }}
            </vs-th>
            <vs-th>
              {{ $t('lang.image')}}
            </vs-th>

          </vs-tr>
        </template>
        <template #tbody>
          <vs-tr
              :key="i"
              v-for="(tr, i) in products"
          >
            <vs-td>
              {{ tr.name }}
            </vs-td>
            <vs-td>
              {{ tr.price }}
            </vs-td>
            <vs-td>
              {{ tr.id }}
            </vs-td>

            <vs-td>

            </vs-td>

            <vs-td>

            </vs-td>
            <vs-td>

            </vs-td>

          </vs-tr>
        </template>
      </vs-table>
    </div>
<!--    <vs-dialog width="300px" not-center v-model="active">-->
<!--      <template #header>-->
<!--        <h4 class="not-margin">-->
<!--          Welcome to <b>Vuesax</b>-->
<!--        </h4>-->
<!--      </template>-->
<!--      <div class="con-content">-->
<!--        <vs-avatar size="300" square><img src="../../assets/pad(canDelete)/photo/buy.png"></vs-avatar>-->
<!--      </div>-->
<!--      <template #footer>-->
<!--        <div class="con-footer">-->
<!--          <vs-button @click="active=false" dark transparent class="button">-->
<!--            {{$t('lang.cancel')}}-->
<!--          </vs-button>-->
<!--          <vs-button @click="ok" transparent class="button">-->
<!--            {{$t('lang.ok')}}-->
<!--          </vs-button>-->
<!--        </div>-->
<!--      </template>-->
<!--    </vs-dialog>-->
  </div>
</template>
<script>
export default {
  data:() => ({
    editActive: false,
    edit: null,
    editProp: '',
    search: '',
    allCheck: false,
    page: 1,
    max: 5,
    active: false,
    selected: [],
    products: [
    ]
  }),
  methods:{
    buySomething(){
      this.active = true
    },
    // ok(){
    //   this.active = false
    //   this.$vs.notification({
    //     position:'top-center',
    //     color:'success',
    //     duration: '6000',
    //     progress: 'auto',
    //     title: this.$t('lang.attention'),
    //     text: this.$t('lang.getCommodity')
    //   })
    // },
    fetchProducts() {
      this.$http.get('public/product/all')
    },
  }
}
</script>



<style scoped>
.box{
  background-color: #ffffff;
  padding: 50px;
  margin-left: 300px;
  margin-right: 300px;
  border-radius: 10px;
}
.button{
  float: right;
  width: 50px;
}
</style>