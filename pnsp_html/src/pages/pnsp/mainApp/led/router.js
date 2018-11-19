import baseMain from '../../baseMain/baseMain';

export default {
    path: '/led',
    name: 'led',
    component: baseMain,
    children:[
      require("./index/router.js").default,
      require("./cycle/router.js").default,
      require("./video/router.js").default
    ]
}
