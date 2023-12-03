<template>
  <div class="center_examples">
    <vs-navbar v-model="active" not-line>
      <template #left>
        <a href="https://www.sustech.edu.cn/"><img src="../../assets/logo/NKLogo.png" style="width: 50px; height: 50px"></a>
        <nav class="lang_bar">
          <h5 style="float: left" @click="changeLanguage('zh-CN')" class="lang">中文</h5>
          <h5 style="float: left">|</h5>
          <h5 style="float: left" @click="changeLanguage('en-US')" class="lang">En</h5>
        </nav>
      </template>
      <vs-navbar-item :active="active === 'home'" id="home" @click="handleLink('userHome')">
        {{$t('lang.homePage')}}
      </vs-navbar-item>
      <vs-navbar-item :active="active === 'building'" id="building" @click="handleLink('building')">
        {{$t('lang.building')}}
      </vs-navbar-item>
      <vs-navbar-item :active="active === 'map'" id="map" @click="handleLink('map')">
        {{$t('lang.map')}}
      </vs-navbar-item>
      <vs-navbar-item :active="active === 'bus'" id="bus" @click="handleLink('bus')">
        {{$t('lang.busLine')}}
      </vs-navbar-item>
      <vs-navbar-item :active="active === 'service'" id="service" @click="handleLink('service')">
        {{$t('lang.service')}}
      </vs-navbar-item>
      <vs-navbar-item :active="active === 'reservation'" id="reservation" @click="handleLink('reservation')">
        {{$t('lang.reservation')}}
      </vs-navbar-item>
      <vs-navbar-item>
        <vs-input dark state="dark" v-model="search" :placeholder="$t('lang.search')" v-if="show">
          <template #icon>
            <i class="bx bx-search"></i>
          </template>
        </vs-input>
      </vs-navbar-item>
      <template #right>
        <LoginRegister></LoginRegister>
      </template>
    </vs-navbar>
  </div>
</template>
<script>
import LoginRegister from "@/components/userComponents/LoginRegister.vue";
export default {
  name:'TopBar',
  components: {
    LoginRegister
  },
  data(){
    return{
      active: 'home',
      search: '',
      show:true,
    }
  },
  beforeMount() {
    const currentRoute = this.$route.path;
    var path = currentRoute
    path = path.split("/")
    this.active = path[path.length - 1]
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
    this.handleResize();
  },
  methods:{
    handleResize() {
      const isSmallScreen = window.matchMedia('(max-width: 900px)').matches;
      this.show = !isSmallScreen;
    },
    handleLink(link){
      this.$router.push({name:link, params:{}}).catch(err=>err)
    },
    changeLanguage(lang){
      this.$i18n.locale = lang;
      localStorage.setItem('language',lang)
    },
  },
}
</script>

<style scoped>
.center_examples {
  height: 40px;
}

.lang {
  cursor: pointer;
}
.lang:hover{
  color: royalblue;
}
@media screen and (max-width: 900px) {
  .lang_bar{
    display: none;
  }
}
</style>