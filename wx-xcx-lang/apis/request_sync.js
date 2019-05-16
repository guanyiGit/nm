/**
 * name: api.js
 * description: request处理基础类
 * author: 彭雨
 * date: 2018-5-19
 */
var config = require("config.js"); //引入配置文件文件
var LANG = {
  "LANG": wx.getStorageSync("lang_code"),
  "UID": wx.getStorageSync("userInfo").userId
}


function Get(url, params) {
  wx.showNavigationBarLoading(); //顶部显示londing效果
  return new Promise((resolve, reject) => {
    wx.request({
      url: config.BASE_URL + url,
      data: params,
      header: {
        "Content-Type": "application/x-www-form-urlencoded" ,//跨域请求
        "LANG": LANG.LANG,
        "UID": LANG.UID,
        "token": wx.getStorageSync("token")
      },
      method: 'GET',
      dataType: 'json',
      success: res => {
        resolve(res.data);
        wx.hideNavigationBarLoading(); //顶部隐藏loading效果 
      },
      fail: (err) => {
        reject(err.data);
        wx.hideNavigationBarLoading();
      }
    })
  })

};

function Post(url, params) {
  wx.showNavigationBarLoading(); //顶部显示londing效果
  return new Promise((resolve, reject) => {
    wx.request({
      url: config.BASE_URL + url,
      data: params,
      header: {
        "Content-Type": "application/x-www-form-urlencoded", //跨域请求
        "LANG": LANG.LANG,
        "UID": LANG.UID,
        "token": wx.getStorageSync("token")
      },
      method: 'POST',
      dataType: 'json',
      success: (res) => {
        resolve(res.data);
        wx.hideNavigationBarLoading(); //顶部隐藏loading效果 
      },
      fail: (err) => {
        resolve(err.data);
        console.log("post 请求:" + config.BASE_URL);
        console.log(err);
      }
    });
  })
};
//暴露接口
module.exports = {
  httpGet: Get,
  httpPost: Post
}