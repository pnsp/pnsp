import Vue from 'vue';
import Router from 'vue-router';
import App from '@/App.vue';
import login from 'pages/login/login';

Vue.use(Router)

export default new Router({
  mode:"history",
  routes: [
    {
      path: '/',
      redirect: '/login',
      component: App,
      children: [
        require("../pages/router.js").default
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: login
    }
  ]
})
