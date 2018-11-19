import pnsp from './pnsp';

export default {
    path: '/pnsp',
    name: 'pnsp',
    component: pnsp,
    children: [
      require("./pnsp/home/router.js").default,
      require("./pnsp/mainApp/led/router.js").default,
      require("./pnsp/accountSetting/router.js").default
    ]
}
