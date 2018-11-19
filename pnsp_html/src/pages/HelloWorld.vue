<template>
 <div class="hello">
    <el-row>
      <el-col :span="7" class="col1" id="lm1">
        <el-tabs>

          <el-tab-pane label="tab1">
            <el-table :data="tableData" border style="width:100%">
              <el-table-column prop="id" label="编号" width="60" align="center">
              </el-table-column>
              <el-table-column prop="name" label="名称" width="150" align="center">
              </el-table-column>
              <el-table-column prop="price" label="价格" width="150" align="center">
              </el-table-column>
              <el-table-column label="操作" fixed="right" align="center">
                <template scope="scope">
                  <el-button type="text" size="small">编辑</el-button>
                  <el-button type="text" size="small">删除</el-button>
                </template>
              </el-table-column>
            </el-table>

            <div class="btnGroup">
              <el-button type="warning">按钮1</el-button>
              <el-button type="danger">按钮2</el-button>
              <el-button type="success">按钮3</el-button>
            </div>
          </el-tab-pane>

          <el-tab-pane label="tab2"></el-tab-pane>

          <el-tab-pane label="tab3"></el-tab-pane>
        </el-tabs>
      </el-col>

      <el-col :span="17" id="lm2">
        <div class="fire">
          <div class="title">热门</div>
          <div class="prodList">
            <ul>
              <!--<li v-for="prod in fire" :key="prod.id">
                <span>{{prod.name}}</span>
                <span class="prodPrice">￥{{prod.price}}</span>
              </li>-->
            </ul>
          </div>
        </div>

        <div class="prodType">
          <el-tabs>

            <el-tab-pane label="跑车">
              <div>
                <ul class="cookList">
                  <li v-for="prod in prodType1" :key="prod.id">
                    <span class="prodImg"><img :src="prod.prodImg" width="100%"/></span>
                    <span class="prodName">{{prod.name}}</span>
                    <span class="prodPrice">￥{{prod.price}}</span>
                  </li>
                </ul>
              </div>
            </el-tab-pane>

            <el-tab-pane label="商务车">

            </el-tab-pane>

            <el-tab-pane label="折扣">

            </el-tab-pane>

            <el-tab-pane label="实用">

            </el-tab-pane>

          </el-tabs>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'hello',
  data(){
    return{
      tableData:[{
        id:"1",
        name:"兰博基尼",
        price:"888888"
      },{
        id:"2",
        name:"雷克萨斯",
        price:"688888"
      },{
        id:"3",
        name:"法拉利",
        price:"988888"
      }],
      fire:[],
      prodType1:[{
        id:"1",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"2",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"3",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"4",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"5",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"6",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"7",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"8",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      },{
        id:"9",
        prodImg:"http://7xjyw1.com1.z0.glb.clouddn.com/pos001.jpg",
        name:"雷克萨斯",
        price:"988888"
      }]
    }
  },
  created:function(){ //钩子，创建时运行
    this.$axios.get("http://jspang.com/DemoApi/oftenGoods.php")
    .then(response=>{
      this.fire = response.data[0].goodsName;
      alert(this.fire);
    })
    .catch(error=>{
      alert("网络错误")
    })
  },
  mounted:function(){
    var lmHeight = document.body.clientHeight; //body高
    document.getElementById('lm1').style.height=lmHeight+'px';
    document.getElementById('lm2').style.height=lmHeight+'px';
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello{
  background-color: #f9fcea;
  height: 100%;
}
.col1{
  background-color: #f9fafc;
  border-right: 1px solid #c0ccda;
}
.btnGroup{
  margin-top: 10px;
}
.title{
  height: 18px;
  border-bottom: 1px solid #d3dce6;
  background-color: #f9fafc;
  padding: 10px;
  text-align: left;
}
.prodList ul li{
  list-style: none;
  float: left;
  border: 1px solid #f9fafc;
  padding: 10px;
  margin: 10px;
  background-color: #fff;
}
.prodPrice{
  color: #58b7ff;
}
.prodType{
  clear: both;
}
.cookList li{
  list-style: none;
  width:23%;
  border:1px solid #E5E9F2;
  height: auot;
  overflow: hidden;
  background-color:#fff;
  padding: 2px;
  float:left;
  margin: 2px;
 
}
.cookList li span{
  display: block;
  float:left;
}
.prodImg{
  width: 40%;
}
.prodName{
  font-size: 18px;
  padding-left: 10px;
  color:brown;

}
.prodPrice{
  font-size: 16px;
  padding-left: 10px;
  padding-top:10px;
}
</style>
