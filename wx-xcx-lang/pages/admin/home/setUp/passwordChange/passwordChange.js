var interval = null //倒计时函数
var config = require("../../../../../apis/config.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")
var icon = require("../../../../../apis/icon.js")
var imageType = require("../../../../../apis/imageType.js")

import WxValidate from '../../../../../utils/WxValidate.js'

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    countdown: {
      time: "点击获取",
      currentTime: 60,
      disabled: false
    },
    passwordChange: {
      oldPassword: "",
      newPassword: "",
      checkPassword: "",
      code: ""
    },
    focus:true

  },

  formSubmit(e){
    var that = this;
    var passwordChange = e.detail.value;
    console.log(e.detail)
    if (!this.WxValidate.checkForm(passwordChange)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    var url = config.url.BASE_URL + "/userInfo/resetPwd";
    var token = app.globalData.token;
    var userDO = app.globalData.userInfo;
    var userVO = passwordChange;
    var userVO = {
      "userDO.userId": userDO.userId,
      pwdOld: passwordChange.pwdOld,
      pwdNew: passwordChange.pwdNew
    }
    //var params = JSON.stringify(userVO);  
    var params = userVO;
    console.log("value  ", params);
    http.httpPost(url, token, params,function(res){
      console.log("res:" + JSON.stringify(res));

      //如果修改成功，返回登录页面重新登录
      if(res.code == 0){
        wx.clearStorageSync();
        wx.showModal({
          // title: '提示',
          title: wx.T.get("tishi"),
          // content: '更改密码成功,请重新登录',
          content: wx.T.get("genghuanmimachengg") + "," + wx.T.get("qingchongxdenglv"),
          showCancel: false,
          confirmText: wx.T.get("quedin"),
          success: function (res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '/pages/login/login',
              })
            }
          }
        })
      }else{
        wx.showModal({
          // title: '提示',
          title: wx.T.get("tishi"),
          // content: '输入的旧密码有误！',
          content: wx.T.get("shurudejiumimayouwu"),
          showCancel: false,
          confirmText: wx.T.get("quedin"),
        })
      }
    })
  },


  bindNewPassword(e) {
    this.setData({
      ["passwordChange.newPassword"]: e.detail.value.replace(/\s+/g, '')
    })
  },

  CheckNewPassword(e) {
    var that = this;
    var newPassword = that.data.passwordChange.newPassword;
    var checkPassword = e.detail.value.replace(/\s+/g, '');
    if (newPassword == checkPassword) {
      return true;
    } else if (!checkPassword){
      return false;
    }else {
      wx.showModal({
        // title: '提示',
        title: wx.T.get("tishi"),
        // content: '两次输入密码不一致,请重新输入',
        content: wx.T.get("liangcimimabuyizhi") + "," + wx.T.get("qingchongxinshuru"),
        showCancel: false,
        confirmText: wx.T.get("quedin"),
        success: function (res) {
          if (res.confirm) {
            return false;
          }
        }
      })
    }
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("miamashezhi") })

    this.initValidate();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.setData({ L: wx.T })
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


  //验证函数
  initValidate: function () {
    // 验证字段的规则
    var rules = {
      pwdOld: {
        required: true,
      },
      pwdNew: {
        required: true,
      },
      checkPassword: {
        required: true,
        equalTo:"pwdNew"
      },
    };
    var messages = {
      pwdOld: {
        required: "旧密码不能为空",
      },
      pwdNew: {
        required: "新密码不能为空",
      },
      checkPassword: {
        required: "确认密码不能为空",
        equalTo:""
      },
    };
    messages = {
      pwdOld: {
        required: wx.T.get("jiumimabunengweikong"),
      },
      pwdNew: {
        required: wx.T.get("xinmimabunengweikong"),
      },
      checkPassword: {
        required: wx.T.get("querenmimabunengweikong"),
        equalTo: wx.T.get("liangcimimabuyizhi") + "," + wx.T.get("qingchongxinshuru")
      },
    };
    this.WxValidate = new WxValidate(rules, messages)
  },

  // getCode() {
  //   var that = this;
  //   var currentTime = that.data.countdown.currentTime;
  //   interval = setInterval(function() {
  //     currentTime--;
  //     that.setData({
  //       ["countdown.time"]: currentTime + 's后请重试',

  //     })
  //     if (currentTime <= 0) {
  //       clearInterval(interval)
  //       that.setData({
  //         ["countdown.time"]: '点击获取',
  //         ["countdown.currentTime"]: 60,
  //         ["countdown.disabled"]: false
  //       })
  //     }
  //   }, 1000)
  // },

  // //获取验证码
  // getVerificationCode() {
  //   var that = this;
  //   that.setData({
  //     ["countdown.disabled"]: true
  //   })
  //   that.getCode();
  // },

})