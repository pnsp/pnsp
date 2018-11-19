import axios from "axios";
import {Message,Loading} from 'element-ui';

var baseUrl = process.env.ROOT_API;

axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.withCredentials = true;
axios.defaults.baseURL = baseUrl;

axios.interceptors.request.use(config=>{
    return config;
},error=>{
    return Promise.reject(error);
});

axios.interceptors.response.use(response=>response,error=>Promise.resolve(error.response));

var toPost = function(url,data,func,errFunc,that){
    axios({
        method: 'post',
        url: url,
        data: data,
        headers:{
            'X-Requested-With':'XMLHttpRequest'
        }
    }).then((res)=>{
        if(res.data.code === '10000'){
            if(func != null){
                func(res);
            }
        }else{
            Message({
                showClose: true,
                message: res.data.msg,
                type: 'error'
            });
            if(res.data.code === '10005'){
                if(that != null){
                    that.$router.push({name: 'login'});
                }
            }
            if(errFunc != null){
                errFunc(res);
            }
        }
    })
}

//loading选项
var options = {
    lock: true,
    text: 'Loading',
    spinner: 'el-icon-loading',
    background: 'rgba(0, 0, 0, 0.7)'
}

var common = {
    //防止按钮频繁点击
    clickTimeOut: function(callback){
        clearTimeout(clickTimeOut);
        var clickTimeOut = setTimeout(()=>{
            return callback
        },1000);
    },
    //数组对象去重,key为需去重的下标
    arrayRepeat: function(array,key){
        var i,j,
            len = array.length;
        for(i=0; i<len; i++){
            for(j=i+1; j<len; j++){
                if(array[i][key] === array[j][key]){
                    array.splice(j,1);
                    len--;
                    j--;
                }
            }
        }
    }
}

export default{
    toPost,
    options,
    baseUrl,
    common
}