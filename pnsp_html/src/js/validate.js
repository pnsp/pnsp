var checkPhone = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('手机号码不能为空'));
    }else{
        var reg = /^((13[0-9]{1})|(14[5|7]{1})|(15([0-3]|[5-9]){1})|(18[0,5-9]{1}))\d{8}$/;
        if (!reg.test(value)) {
            callback(new Error('手机号码格式错误'));
        }else{
            callback();
        }
    }
};
var checkPassword = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('密码不能为空'));
    }else if(value.length < 6){
        callback(new Error('密码长度不能小于6位'));
    }else if(value.length > 18){
        callback(new Error('密码长度不能大于18位'));
    }else{
        callback();
    }
};
var checkAuthCode = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('验证码不能为空'));
    }else{
        callback();
    }
};
export default{
    checkPhone,
    checkPassword,
    checkAuthCode
}