// pages/login/login.js

var image = require("../../apis/image.js")
var icon = require("../../apis/icon.js")
var config = require("../../apis/config.js")
var dateFormat = require("../../apis/dateFormat.js")
var http = require("../../apis/request.js")
var httpSync = require("../../apis/request_sync.js")


import WxValidate from '../../utils/WxValidate.js'


var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    login: {
      imageUrl: image.loginImage,
      username_icon: icon.loginUserName,
      password_icon: icon.loginPassWord
    }
  },

  /**
   * 提交到后台
   */
  formSubmit: function(e) {
    var formdata = e.detail.value;
    var _this  = this;
    //去除(用户名,密码)空格
    formdata.username = formdata.username.replace(/\s+/g, '')
    formdata.password = formdata.password.replace(/\s+/g, '')
    if (!this.WxValidate.checkForm(formdata)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    var url = config.url.LOGIN_URL + "/wx/login";
    var token = "";
    var params = {
      username: formdata.username,
      password: formdata.password,
    }
    wx.request({
      url: config.url.LOGIN_URL + "/wx/login",
      data: params,
      header: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      method: 'POST',
      success: function(res) {
        if (!res.data.code == 0) {
          wx.showModal({
            // title: "失败",
            title: wx.T.get("shibai"),
            content: wx.T.get("yonghuminghuomimacuowu"),
            confirmText: wx.T.get("quedin"),
            showCancel: false
          })
          return;
        }
        //将token添加到app全局变量
        app.globalData.token = res.header.token;
        app.globalData.userInfo = res.data.userInfo;
        wx.setStorageSync("token", res.header.token);
        wx.setStorageSync("userInfo", res.data.userInfo);
        if (app.globalData.userInfo.roles[0].type == 2) {
          wx.reLaunch({
            url: '/pages/index/index',
          })
        } else {
          wx.reLaunch({
            url: '/pages/admin/index/index',
          })
        }
        _this.initLang()
      },
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({
      title: wx.T.get("denglv")
    })

    this.initValidate();
    this.checkdIndex()

  },
  checkdIndex() {
    var that = this;
    app.getUserInfo(function(userInfo) {
      if (!userInfo || userInfo == "") {
        // wx.redirectTo({
        //   url: '/pages/login/login',
        // })
        return;
      } else {
        if (userInfo.roles[0].type == 2) {
          that.setData({
            name: userInfo.name,
            roleName: userInfo.roles[0].roleName
          })
          wx.redirectTo({
            url: '/pages/index/index',
          })
        } else {
          wx.redirectTo({
            url: '/pages/admin/index/index',
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.setData({
      L: wx.T
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  //报错 
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
      title: wx.T.get("tishi"),
      confirmText: wx.T.get("quedin")
    })
  },
  initLang() {
    http.http("/mutliLang/changLangInfo", "GET", null, {
      "LANG": wx.T.locale
    }, true)
      .then(res => {
        console.log(res)
      })
  },

  //验证函数
  initValidate: function() {
    // 验证字段的规则
    var rules = {
      username: {
        required: true,
      },
      password: {
        required: true,
      },
    };
    var messages = {
      username: {
        required: "账号不能为空",
      },
      password: {
        required: "密码不能为空",
      },
    };
    messages = {
      username: {
        // required: "账号不能为空",
        required: wx.T.get("zhanghaobunengweikong"),
      },
      password: {
        // required: "密码不能为空",
        required: wx.T.get("mimabunengweikong"),
      },
    };
    this.WxValidate = new WxValidate(rules, messages)
  },
})