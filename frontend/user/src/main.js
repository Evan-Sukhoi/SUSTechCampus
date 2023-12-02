import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueSax from 'vuesax'
import 'vuesax/dist/vuesax.css'
import 'boxicons'
import 'boxicons/css/boxicons.css'
import VueI18n from 'vue-i18n'
import store from "@/store";
import locale from 'element-ui/lib/locale';
import langEn from 'element-ui/lib/locale/lang/en';
import langCn from 'element-ui/lib/locale/lang/zh-CN';
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8081"


Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(Element)
Vue.use(VueSax)
Vue.use(VueI18n)
Vue.use(VueI18n);




let lang = localStorage.getItem('language') || 'en-US';
const i18n = new VueI18n({
  locale: lang,    // 语言标识
  messages: {
    'zh-CN': {...langCn, ...require('./lang/zh')},   // 通过require引入中文语言包
    'en-US': {...langEn, ...require('./lang/en')}    // 通过require引入英文语言包
  }
})
locale.i18n((key, value) => i18n.t(key, value));
new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')

