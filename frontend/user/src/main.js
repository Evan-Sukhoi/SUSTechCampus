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
import VueAMap from 'vue-amap';





Vue.config.productionTip = false
Vue.use(Element)
Vue.use(VueSax)
Vue.use(VueI18n)
Vue.use(VueI18n);
Vue.use(VueAMap);

VueAMap.initAMapApiLoader({
  key: 'd647a0c3b5f5de68bc23204f7365fc97',
  plugin: ['AMap.Driving', 'AMap.Walking',],
  v: '1.4.15'
});

let lang = localStorage.getItem('language') || 'en-US';
const i18n = new VueI18n({
  locale: lang,    // 语言标识
  messages: {
    'zh-CN': require('./lang/zh'),   // 通过require引入中文语言包
    'en-US': require('./lang/en')    // 通过require引入英文语言包
  }
})

new Vue({
  router,
  i18n,
  render: h => h(App)
}).$mount('#app')
