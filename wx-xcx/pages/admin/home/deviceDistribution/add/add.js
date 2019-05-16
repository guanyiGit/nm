var config = require("../../../../../apis/config.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")
var icon = require("../../../../../apis/icon.js")
var imageType = require("../../../../../apis/imageType.js")
var dateFormat = require("../../../../../apis/dateFormat.js")

import WxValidate from '../../../../../utils/WxValidate.js'

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isTownAdmin:true,
    org:{
      orgIndex:0,
      orgArray:[]
    },
    protector:{
      protectorIndex:0,
      protectorArray:[]
    }
  },

  formSubmit: function (e) {
    var that = this;
    var deviceDistribution = e.detail.value;
    console.log(e.detail)
    if (!this.WxValidate.checkForm(deviceDistribution)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/deviceDistribution/save';
    var token = app.globalData.token;
    http.httpPost(url, token, deviceDistribution, function (res) {
      if (res.code == 0) {
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function () {
          wx.navigateBack()
        }, 1500)
      }
    })
  },

  isTownAdmin(){
    var that = this;
    var isTownAdmin =  app.isTownAdmin();
    that.setData({
      isTownAdmin: isTownAdmin
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.initValidate();
    this.getlowerLevelOrg();
    this.getProtectors();
    this.isTownAdmin();
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

  /**
   * 
   */
  bindOrgChange(e){
    var that = this;
    that.setData({
      ['org.orgIndex']: e.detail.value
    })
  },

  bindProtectorChange(e) {
    var that = this;
    that.setData({
      ['protector.protectorIndex']: e.detail.value
    })
  },
  //报错 
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },


  //验证函数
  initValidate: function () {
    // 验证字段的规则
    var rules = {
      amount: {
        required: true,
        digits: true,
      },
    };
    var messages = {
      amount: {
        required: "请填写溯源id",
        digits: "请输入正确的数量",
      },
    };
    this.WxValidate = new WxValidate(rules, messages)
  },
  
  /**
  * 只返回本组织的直接下级组织
  */
  getlowerLevelOrg() {
    var that = this;
    var url = config.url.BASE_URL + "/orgInfo/initOrgSelect4";
    var token = app.globalData.token;
    var params = {};
    http.httpGet(url, token, params, function (res) {
      if (res && res.length >= 0) {
        that.setData({
          ['org.orgArray']: res
        })
      }
    })
  },

  getProtectors() {
    var that = this;
    var url = config.url.BASE_URL + "/protector/initProtectorSel2";
    var token = app.globalData.token;
    var params = {};
    http.httpGet(url, token, params, function (res) {
      if (res && res.length >= 0) {
        that.setData({
          ['protector.protectorArray']: res
        })
      }
    })
  },

})