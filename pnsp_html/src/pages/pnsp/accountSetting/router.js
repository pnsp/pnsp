import baseMain from '../baseMain/baseMain';

export default {
    path: '/accountSetting',
    name: 'accountSetting',
    component: baseMain,
    children:[
      require("./accountInfo/router.js").default
    ]
}
