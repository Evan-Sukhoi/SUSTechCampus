<template>
  <div class="back">
    <div class="box">
      <vs-table>
        <template #thead>
          <vs-tr>
            <vs-th>
              {{ $t('lang.commodity') }}
            </vs-th>
            <vs-th>
              {{ $t('lang.price') }}
            </vs-th>
            <vs-th>
              {{ $t('lang.orderID') }}
            </vs-th>
          </vs-tr>
        </template>
        <template #tbody>
          <vs-tr
              :key="i"
              v-for="(tr, i) in users"
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

            <template #expand>
              <div class="con-content">
                <div>
                  <vs-avatar style="float: left" size="100">
                    <img :src="tr.photo" alt="">
                  </vs-avatar>
                  <p style="float: left; margin-left: 10px">
                    {{ tr.name }}
                  </p>
                </div>
                <div>
                  <vs-button flat icon class="button" @click="buySomething">
                    {{ $t('lang.buy') }}
                  </vs-button>
                </div>
              </div>
            </template>
          </vs-tr>
        </template>
      </vs-table>
    </div>
    <vs-dialog width="300px" not-center v-model="active">
      <template #header>
        <h4 class="not-margin">
          Welcome to <b>Vuesax</b>
        </h4>
      </template>
      <div class="con-content">
        <vs-avatar size="300" square><img src="../../assets/pad(canDelete)/photo/buy.png"></vs-avatar>
      </div>
      <template #footer>
        <div class="con-footer">
          <vs-button @click="active=false" dark transparent class="button">
            {{$t('lang.cancel')}}
          </vs-button>
          <vs-button @click="ok" transparent class="button">
            {{$t('lang.ok')}}
          </vs-button>
        </div>
      </template>
    </vs-dialog>
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
    max: 3,
    active: false,
    selected: [],
    users: [
      {
        "id": 1,
        "name": "校园卡卡套",
        "price": "10￥",
        "photo": require("../../assets/pad(canDelete)/photo/img_1.png"),
      },
      {
        "id": 2,
        "name": "纪念衬衫",
        "price": "25￥",
        "photo": require("../../assets/pad(canDelete)/photo/img_2.png"),
      },
      {
        "id": 3,
        "name": "南科大徽章",
        "price": "5￥",
        "photo": require("../../assets/pad(canDelete)/photo/img_3.png"),
      },
    ]
  }),
  methods:{
    buySomething(){
      this.active = true
    },
    ok(){
      this.active = false
      this.$vs.notification({
        position:'top-center',
        color:'success',
        duration: '6000',
        progress: 'auto',
        title: this.$t('lang.attention'),
        text: this.$t('lang.getCommodity')
      })
    },
    fetchProducts() {

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