<template>
  <div class="video">

    <el-container class="video-top">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ name: 'led' }">智能LED显示屏</el-breadcrumb-item>
        <el-breadcrumb-item>视频播放</el-breadcrumb-item>
      </el-breadcrumb>
    </el-container>

    <el-container class="video-body">
      <el-tabs class="video-tabs" v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="正在使用" name="first">正在使用</el-tab-pane>
        <el-tab-pane label="我的案例" name="second">我的案例</el-tab-pane>
        <el-tab-pane label="新建案例" name="third">新建案例</el-tab-pane>
      </el-tabs>
    </el-container>

  </div>
</template>

<script>
export default {
  name: 'video',
  data(){
    return{
      activeName:"first"
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
}
</script>

<style scoped>
.video{
  box-shadow:1px 1px 1px 1px #ccc;
  min-height:582px;
  background-color:#fff;
  margin:auto;
	width:100%;
	padding:18px 0px;
	text-align: center;
}
.video-top,.video-body{
  margin-left: 1%;
}
.video-body{
  margin-top: 18px;
  width:98%;
}
.video-tabs{
  width:100%;
}
</style>
