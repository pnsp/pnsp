<template>
  <div class="login" id="loginBody">
    <el-card shadow="never" class="login-card" id="login-card">
      <el-form :model="loginForm" 
               :rules="loginRules" 
               ref="loginForm" 
               @keyup.enter.native="loginSubmit('loginForm')"
               >
        <el-form-item><span style="font-size:28px;cursor:default;">欢迎登陆</span></el-form-item>
        <el-form-item prop="phone">
          <el-input id="phone" type="text" v-model="loginForm.phone" placeholder="请输入手机号码" maxlength="11" auto-complete="off" autofocus>
            <i slot="prefix" class="icon iconfont icon-shouji"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" maxlength="18" auto-complete="off">
            <i slot="prefix" class="icon iconfont icon-mima"></i>
            <!--<router-link slot="suffix" to="/register" class="link2" tabindex="-1">忘记密码？</router-link>-->
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-col :span="16">
            <el-form-item prop="authCode">
              <el-input type="text" v-model="loginForm.authCode" maxlength="6" placeholder="请输入验证码，点击图片可更换" auto-complete="off">
                <i slot="prefix" class="icon iconfont icon-yanzheng"></i>
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="7" style="float:right;">
            <el-form-item>
              <div style="height:38px;" @click="handleAuthImg"><img :src="authImg" class="authImg"></div>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loginSubmit('loginForm')" style="width:100%;" tabindex="-1">登录</el-button>
          <br>
          <!--<span>没有账号？<router-link to="/register" class="link1" tabindex="-1">免费注册</router-link></span>-->
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'login',
  data(){
    return{
      loginForm: {
        phone: '',
        password: '',
        authCode: ''
      },
      loginRules: {
        phone: [
          { validator: this.validate.checkPhone, trigger: 'blur' }
        ],
        password: [
          { validator: this.validate.checkPassword, trigger: 'blur' }
        ],
        authCode: [
          { validator: this.validate.checkAuthCode, trigger: 'blur' }
        ]
      },
      authImg : ""
    }
  },
  methods:{
    loginSubmit(loginForm) {
      var that = this;
       this.$refs[loginForm].validate((valid) => {
        if (!valid) {
          Message({
            showClose: true,
            message: '格式错误！',
            type: 'error'
          });
          return;
        }
      });
      this.$loading(this.utils.options);
      var func = function(res){
          that.$router.push({name: 'home'});
      }
      var errFunc = function(res){
          //document.getElementById('phone').focus();
          that.handleAuthImg();
      }
      this.utils.toPost(this.$url.login,this.loginForm,func,errFunc,that);
      //that.$router.push({name: 'home'});
      this.$loading(this.utils.options).close();
    },
    handleAuthImg(){
      this.authImg = this.$url.authImg + Math.random();
    }
  },
  mounted:function(){
    var bodyHeight = document.body.clientHeight; //body高
    document.getElementById('loginBody').style.height=bodyHeight+'px';
    document.getElementById('login-card').style.height=bodyHeight*0.46+'px';
    this.handleAuthImg();
  }
}
</script>

<style scoped>
.login{
  background-image: url('../../assets/login-background.png');
  -moz-background-size:100% 100%;  
  background-size:100% 100%; 
}
.login-card{
  width:24%;
  margin: auto;
  position: absolute;
  top: 0; left: 0; bottom: 0; right: 0;
  min-height: 353px;
}
.link1{
  color: blue;
  text-decoration: none;
}
.link2{
  color: #666;
  text-decoration: none;
}
.link2:hover{
  cursor: pointer;
  color: blue;
}
.authImg{
  width:100%;
  height:100%;
}
</style>
