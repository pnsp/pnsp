<template>
  <el-container class="baseMain">

    <left-nav class="leftNav"></left-nav>

    <el-container class="rightView">
      
      <el-header class="nav-top">
        <el-dropdown trigger="click" style="margin-right: 2%;"> 
          <span class="el-dropdown-link select-icon">
            <i class="icon iconfont icon-my"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item class="clearfix">
              <span @click="accountInfo">个人信息</span>
            </el-dropdown-item>
            <el-dropdown-item class="clearfix">
              <span @click="logout">退出</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      
      <el-main id="view">
        <router-view></router-view>
      </el-main>

    </el-container>

  </el-container>
</template>

<script>
import leftNav from '@/components/common/leftNav';
import axios from "axios";
export default {
  name: 'baseMain', 
  data() {
    return {
     
    }
  },
  methods:{
    accountInfo(){
       this.$router.push({name: 'accountInfo'});
    },
    logout(){
      var that = this;
      var func = function(res){
          that.$router.push({name: 'login'});
      }
      this.utils.toPost(this.$url.logout,null,func,null,that);
    }
  },
  components:{
    leftNav
  },
  mounted:function(){
      var bodyHeight = document.body.clientHeight; //body高
      document.getElementById("view").style.minHeight = (bodyHeight - 60) + 'px';
  }
}
</script>

<style scoped>
.baseMain{
  height: 100%;
}
#view{
  height: 100%;
  background-color: #efefef;
}
.nav-top{
  text-align: right; 
  font-size: 14px;
}
.leftNav{
  min-width: 205px;
  float:left;
}
.rightView{
  height:582px;
}
.el-header {
  background-color: #fff;
  line-height: 60px;
}
.select-icon{
  display: block;
}
.select-icon:hover{
  cursor: pointer;
  color: #ffd04b;
}
.clearfix{
  text-align: center;
}
</style>
