var image = require("../../../../apis/image.js")
var icon = require("../../../../apis/icon.js")
var config = require("../../../../apis/config.js")
var _scanCode = require("../../../../common/scanCode.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  toUserInfoPage(){
    wx.navigateTo({
      url: '/pages/admin/home/userInfo/userInfo',
    })
  },

  toPassWordChangePage(){
    wx.navigateTo({
      url: '/pages/admin/home/setUp/passwordChange/passwordChange',
    })
  },

  //登出
  logout() {
    wx.request({
      url: config.url.LOGIN_URL + '/logout',
      success(res) {
        app.globalData.userInfo = "",
        app.globalData.token="",
        wx.clearStorageSync();
        wx.reLaunch({//退出到登录页
          url: '/pages/login/login',
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("shezhi") })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({ L: wx.T })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})