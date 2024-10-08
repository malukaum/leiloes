import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import { VueMaskDirective } from "v-mask";

Vue.directive("mask", VueMaskDirective);
Vue.config.productionTip = false
Vue.filter('toCurrency', function (value) {
  if (typeof value !== "number") {
    return value;
  }
  var formatter = new Intl.NumberFormat('pt-Br', {
    style: 'currency',
    currency: 'BRL'
  });
  return formatter.format(value);
});

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
