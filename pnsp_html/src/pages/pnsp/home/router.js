import baseMain from '../baseMain/baseMain';

export default {
    path: '/home',
    name: 'home',
    component: baseMain,
    children:[
      require("./index/router.js").default
    ]
}
