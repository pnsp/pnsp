
//var baseUrl = process.env.ROOT_API;
var baseUrl = "http://127.0.0.1:8081";

var urlList = {
    login: baseUrl + "/pnspUserT/login",

    logout: baseUrl + "/pnspUserT/logout.do",

    authImg: baseUrl + "/pnspUserT/getAuthCode?token=1",

    getImageListByGroupId : baseUrl + "/pnspImageT/getImageListByGroupId.do"
    
}

export default{
    urlList
}