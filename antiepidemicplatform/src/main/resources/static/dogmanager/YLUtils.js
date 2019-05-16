//常用检验
//订单提交页-验证邮政编码
function is_postcode(postcode) {
 if ( postcode == "") {
  return false;
 } else {
  if (! /^[0-9][0-9]{5}$/.test(postcode)) {
   return false;
  }
 }
 return true;
}
//订单提交页-验证手机号
function is_mobile(mobile) {
  if( mobile == "") {
  return false;
  } else {
  if( ! /^0{0,1}(13[0-9]|15[0-9]|18[0-9]|14[0-9])[0-9]{8}$/.test(mobile) ) {
  return false;
  }
  return true;
 }
}
//订单提交页-验证email的合法性
function is_email(email) {
 if ( email == "") {
  return false;
 } else {
	 if (! /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)) {
   return false;
  }
 }
 return true;
}
//非负整数
function isNonnegativeInteger(num){
	var reg = /(^[1-9]+\d*$)|(^0$)/;
	return reg.test(num);
}


//日期格式转化
function timeStamp2String(time) {
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1)
			: datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime
			.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	return year + "-" + month + "-" + date+" "+hour+":"+minute;
}


//计算两个日期天数差的函数，通用
function DateDiff(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式
  var aDate, oDate1, oDate2, iDays;
  aDate = sDate1.split("-");
  oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式
  aDate = sDate2.split("-");
  oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
  iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数

  return iDays;  //返回相差天数
}

//验证身份证  
function isCardNo(card) {   
var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;   
return pattern.test(card);  
} 

//校验年龄
function isAge(age){
	var regAge = /^\d{1,2}$/;
	return regAge.test(age);
}
//校验体重
function isWeight(weight){
	//var weightReg = /^(0(\.\d{1}){0,1}|[1-8]\d{1,3}(\.\d{1}){0,1}|9\d{1,2}(\.\d{1}){0,1}|999(\.0){0,1}|.{0})$/;
	var weightReg = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{0,1})?$/;
	return weightReg.test(weight);
}

function getdogAge(birthday){
    var newDate = new Date().getTime() - birthday;
    // 向下取整  例如 10岁 20天 会计算成 10岁
    // 如果要向上取整 计算成11岁，把floor替换成 ceil
    var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 /365);
    return age;
}


