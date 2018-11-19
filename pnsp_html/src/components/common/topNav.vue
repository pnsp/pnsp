<template>
  <div class="topNav">
    <el-menu :default-active="activeIndex" 
             mode="horizontal" 
             class="el-menu-demo"
             @select="handleSelect">

      <el-menu-item v-for="(item,i) of mainNavList" :key="i" :index="item.name" 
              :disabled="item.disabled==='y'?true:false">{{item.title}}</el-menu-item>

      <el-button class="register-btn" round plain @click="handleRouter('register')">注册</el-button>
      <el-button class="login-btn" round plain @click="handleRouter('login')">登录</el-button>
    </el-menu>
  </div>
</template>
<script>
export default {
  name:'topNav', 
  data() {
    return {
      activeIndex: 'home',
      mainNavList: [
        {name:'home',title:'首页'},
        {name:'register',title:'投影服务',disabled:"y"},
        {name:'team',title:'团队介绍'},
        {name:'register',title:'申请加盟',disabled:"y"},
        {name:'help',title:'帮助中心'}
      ]
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      this.handleRouter(key);
      this.activeIndex = key;
    },
    //你需要接受路由的参数再跳转
    handleRouter(name){
      this.$router.push({name: name});
    }
  }
}
</script>
<style scoped>
.el-menu-demo{
  padding-left: 20%;
}
.login-btn,.register-btn{
    margin-top:10px;
    float: right;
}
.register-btn{
    margin-right: 5%;
}
.login-btn{
    margin-right: 1%;
}
</style>
