<template>
  <div class="home">

    <div class="home-cycle">
      <el-carousel class="home-cycle-body" :interval="3000" type="card" height="200px">
        <el-carousel-item v-for="item in homeCycleImageList" :key="item.id">
          <img class="home-cycle-image" 
               :src="(item.url=='' || item.url==null)
               ?require('../../../../assets/' + item.srcName + item.suffix)
               :item.url" 
               alt="" 
               @click="clickCycleImage(item.routerName)">
        </el-carousel-item>
      </el-carousel>
    </div>
   
    <span class="home-title">热门应用</span>

    <el-row class="home-hot">
      <el-col  :span="5">
        <el-card>
          <img src="../../../../assets/1.png" class="home-hot-image">
          <div class="home-hot-title">
            <span>循环图片</span>
          </div>
        </el-card>
      </el-col>
      <el-col  :span="5" :offset="1">
        <el-card>
          <img src="../../../../assets/1.png" class="home-hot-image">
          <div class="home-hot-title">
            <span>循环图片</span>
          </div>
        </el-card>
      </el-col>
      <el-col  :span="5" :offset="1">
        <el-card>
          <img src="../../../../assets/1.png" class="home-hot-image">
          <div class="home-hot-title">
            <span>循环图片</span>
          </div>
        </el-card>
      </el-col>
      <el-col  :span="5" :offset="1">
        <el-card>
          <img src="../../../../assets/1.png" class="home-hot-image">
          <div class="home-hot-title">
            <span>循环图片</span>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: 'home',
  data(){
    return{
      /** 首页顶部循环图 */
      homeCycleImageList : []
    }
  },
  methods:{
    /** 点击首页顶部循环图跳转页面 */
    clickCycleImage(routerName){
      this.$router.push({name: routerName});
    },
    /** 初始化首页顶部循环图 */
    initHomeCycleImage(groupId){
      var that = this;
      var obj = {"groupId":groupId}
      var data = JSON.stringify(obj);
      var func = function(res){
          that.homeCycleImageList = res.data.result;
      }
      this.utils.toPost(this.$url.getImageListByGroupId,data,func,null,that);
    }
  },
  mounted:function(){
    this.initHomeCycleImage("P0001");
  }
}
</script>

<style scoped>
.home-cycle,.home-hot{
  background-color:#fff;
  margin:auto;
	width:100%;
	padding:18px 0px;
	text-align: center;
}
.home-cycle-body{
  width:96%;
  margin:auto;
}
.home-cycle-image{
  width:100%;
}
.home-title{
  color:#999;
  float: left;
  margin-top: 10px;
}
.home-hot{
  margin-top:46px;
  min-height:218px;
  padding-left:50px;
}
.home-hot-title {
  color:#999;
  display: block;
}
.home-hot-image {
  width: 100%;
  display: block;
  float: left;
  margin-bottom: 20px;
}
.home-hot-image:hover,.home-hot-title:hover{
  cursor: pointer;
}
</style>
