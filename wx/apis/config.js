var url = {
  //BASE_URL: "https://nm.soholy.cn/biz",
  //BASE_URL: "http://192.168.0.69:8001/biz",
  BASE_URL: "http://192.168.0.14:8001/biz",
  //LOGIN_URL: "https://nm.soholy.cn",
  //LOGIN_URL:"http://192.168.0.69:8001", 
  LOGIN_URL:"http://192.168.0.14:8001", 
  //SMS_URL: "http://192.168.0.14:8889",
}

//未定
var roleNumber = {
  worker: 1
}
 
//防疫药品查询
var drug = {
  antiepidemic:0,  //防疫药品
  treatment:1      //治疗药品
}

//最大添加图片数
var maxImage = 5;

//暴露接口
module.exports = {
  url: url,
  roleNumber: roleNumber,
  maxImage: maxImage,
  drug: drug
};
