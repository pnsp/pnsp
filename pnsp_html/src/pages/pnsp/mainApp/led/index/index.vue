<template>
  <div class="led">
    <el-row>
      <el-col :span="23">
        <el-col :span="3" :offset="1">
          <el-card class="app-card">
            <img class="app-image" src="../../../../../assets/advertising-screen-cover.png" alt="" @click="linkToItem('cycle')">
            <div>
              <span class="app-title" @click="linkToItem('cycle')">循环广告</span>
            </div>
          </el-card>
        </el-col>
        <el-col :span="3" :offset="1">
          <el-card class="app-card">
            <img class="app-image" src="../../../../../assets/advertising-screen-cover.png" alt="" @click="linkToItem('video')">
            <div>
              <span class="app-title" @click="linkToItem('video')">视频播放</span>
            </div>
          </el-card>
        </el-col>
        <el-col :span="3" :offset="1">
          <el-card class="stay-tuned">
            <img class="stay-tuned-image" src="../../../../../assets/stay-tuned-cover.png" alt="">
          </el-card>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'led',
  data(){
    return{
      
    }
  },
  methods:{
    linkToItem(item){
      this.$router.push({name:item});
    }
  }
}
</script>

<style scoped>
.led{
  box-shadow:1px 1px 1px 1px #ccc;
  min-height:582px;
  background-color:#fff;
  margin:auto;
	width:100%;
	padding:38px 0px;
	text-align: center;
}
.app-card:hover{
  box-shadow:1px 4px 4px 1px #ccc;
}
.stay-tuned{
  border:0px;
  box-shadow:0px 0px 0px 0px #fff;
}
.app-title{
  font-size:20px;
  font-weight: bold;
}
.stay-tuned:hover{
  cursor: default;
}
.stay-tuned-image{
  width:100%;
}
.app-image{
  width:100%;
  display: block;
  margin-bottom: 12px;
}
.app-image:hover{
  cursor: pointer;
}
.app-title:hover{
  cursor: pointer;
  color:#ffd04b;
}
</style>
