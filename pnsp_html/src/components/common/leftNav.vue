<template>
  <div class="leftNav">
    <el-menu :default-active="activeIndex" 
             class="el-menu-vertical-demo" id="menu"
             @open="handleOpen"
             @close="handleClose"
             background-color="#545c64"
             text-color="#fff"
             active-text-color="#ffd04b"
             @select="handleSelect">

        <div class="logo">
            <span>iexiao</span>
        </div>

        <el-menu-item index="home" class="menu-item">
            <i class="icon iconfont icon-home"></i>
            <span slot="title" style="margin-left:5%;">首页</span>
        </el-menu-item>

        <el-submenu index="" class="menu-item">
            <template slot="title">
                <i class="icon iconfont icon-app"></i>
                <span style="margin-left:5%;">应用中心</span>
            </template>
            <el-menu-item class="menu-item" v-for="(item,i) of appNavList" :key="i" :index="item.name" 
              :disabled="item.disabled==='y'?true:false"><span style="margin-left:5%;">{{item.title}}</span></el-menu-item>
        </el-submenu>

        <el-submenu index="accountSettings" class="menu-item">
            <template slot="title">
                <i class="icon iconfont icon-shezhi"></i>
                <span style="margin-left:5%;">账号设置</span>
            </template>
            <el-menu-item class="menu-item" v-for="(item,i) of userNavList" :key="i" :index="item.name" 
              :disabled="item.disabled==='y'?true:false"><span style="margin-left:5%;">{{item.title}}</span></el-menu-item>
        </el-submenu>
        
    </el-menu>
  </div>
</template>
<script>
export default {
    name:'leftNav', 
    data(){
        return{
            activeIndex: 'home',
            appNavList: [
                {name:'led',title:'智能LED显示屏'}
                // {name:'login',title:'开发中',disabled:"y"}
            ],
            userNavList: [
                {name:'accountInfo',title:'个人信息',disabled:"n"}
            ]
        }
    },
    methods: {
        handleSelect(key, keyPath){
            this.handleRouter(key);
            this.activeIndex = key;
        },
        handleOpen(key, keyPath) {

        },
        handleClose(key, keyPath) {

        },
        handleRouter(name){
            this.$router.push({name: name});
        }
    },
    mounted:function(){
        var bodyHeight = document.body.clientHeight; //body高
        document.getElementById("menu").style.height = bodyHeight + 'px';
    }
}
</script>
<style scoped>
.menu-item{
    text-align: left;
}
.logo{
    height:60px;
    font:italic normal bold 38px 黑体;
    line-height:60px;
    color:#fff;
}
.logo:hover{
    cursor: default;
}
</style>
