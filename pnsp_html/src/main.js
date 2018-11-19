// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import app from './app';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import validate from './js/validate';
import utils from './js/utils';
import urlList from './js/urlList';
//import axios from "axios";
//import qs from "qs";

//axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
//axios.defaults.withCredentials = true;

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.prototype.validate = validate;
Vue.prototype.utils = utils;
Vue.prototype.$url = urlList.urlList;
//Vue.prototype.axios = axios;
//Vue.prototype.qs = qs;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { app },
  template: '<app/>'
})
