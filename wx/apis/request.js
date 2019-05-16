/**
 * name: api.js
 * description: request处理基础类
 * author: 彭雨
 * date: 2018-5-19
 */
var config = require("config.js"); //引入配置文件文件

function Get(url,token, params, cb) {
  wx.showLoading({
    title: '加载中',
    mask: true
  })
  wx.request({
    url: url,
    data: params,
    header: {
      "Content-Type": "application/x-www-form-urlencoded", //跨域请求
      "token": token
    },
    method: 'GET',
    dataType: 'json',
    success: (res) => {
      wx.hideLoading();
      if (res.header.statusCode == 401){
          wx.clearStorageSync();
          wx.showModal({
            title: '登录超时',
            content: '登录超时,请重新登录',
            showCancel:false,
            success:function(res){
                if(res.confirm){
                  wx.navigateTo({
                    url: '/pages/login/login',
                  })
                }
            }
          })
          return;
      }
      return  typeof cb == "function" && cb(res.data);
    },
    fail: (err) => {
      return typeof cb == "function" && cb(false);
      console.log("get 请求:" + config.BASE_URL);
      console.log(err)
    },
    complete:(res) =>{
      wx.hideLoading();
    }
  })
};
function Post(url, token, params, cb) {
  wx.showLoading({
    title: '加载中',
    mask: true
  })
  wx.request({
    url: url,
    data: params,
    header: {
      "Content-Type": "application/x-www-form-urlencoded",//表单请求
      "token": token
    },
    method: 'POST',
    success: (res) => {
      if (res.header.statusCode == 401) {
        wx.clearStorageSync();
        wx.showModal({
          title: '登录超时',
          content: '登录超时,请重新登录',
          showCancel: false,
          success: function (res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '/pages/login/login',
              })
            }
          }
        })
        return;
      }
      return  typeof cb == "function" && cb(res.data, "");
    },
    fail: (err) => {
      return  typeof cb == "function" && cb(null, err.errMsg);
      console.log("post 请求:" + config.BASE_URL);
      console.log(err);
    },
    complete: (res) => {
      wx.hideLoading();
    }
  });
};

function JSONPost(url, token, params, cb) {
  wx.showLoading({
    title: '加载中',
    mask: true
  })
  wx.request({
    url: url,
    data: params,
    header: {
      "Content-Type": "application/json;charset=UTF-8",//表单请求
      "token": token
    },
    method: 'POST',
    dataType: 'json',
    success: (res) => {
      if (res.header.statusCode == 401) {
        wx.clearStorageSync();
        wx.showModal({
          title: '登录超时',
          content: '登录超时,请重新登录',
          showCancel: false,
          success: function (res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '/pages/login/login',
              })
            }
          }
        })
        return;
      }
      return typeof cb == "function" && cb(res.data, "");
    },
    fail: (err) => {
      return typeof cb == "function" && cb(null, err.errMsg);
      console.log("post 请求:" + config.BASE_URL);
      console.log(err);
    },
    complete: (res) => {
      wx.hideLoading();
    }
  });
};

//暴露接口
module.exports = {
  httpGet: Get,
  httpPost: Post,
  JSONPost: JSONPost
}
