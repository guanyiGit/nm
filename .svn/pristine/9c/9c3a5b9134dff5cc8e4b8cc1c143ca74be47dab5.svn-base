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
    anatomyInfo: {},
    positiveAmount: null,
    testAmount: null,
    positiveRate: null
  },

  getpositiveAmount: function (e) {
    var that = this;
    that.setData({
      positiveAmount: e.detail.value
    });
    console.log(that.data.testAmount);
    console.log(that.data.positiveAmount);
    if (that.data.testAmount && that.data.positiveAmount) {
      that.setData({
        positiveRate: (that.data.positiveAmount / that.data.testAmount * 100).toFixed(2)
      });
    }else{
      that.setData({
        positiveRate: ""
      });
    }
  },

  gettestAmount: function (e) {
    var that = this;
    that.setData({
      testAmount: e.detail.value
    });
    console.log(e.detail.value);
    if (that.data.testAmount && that.data.positiveAmount) {
      that.setData({
        positiveRate: (that.data.positiveAmount / that.data.testAmount * 100).toFixed(2)
      });
    }else{
      that.setData({
        positiveRate: ""
      });
    }
  },


  formSubmit: function (e) {
    var that = this;
    var anatomyInfo = e.detail.value;
    if (!this.WxValidate.checkForm(anatomyInfo)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/dogAnatomy/save';
    var token = app.globalData.token;
    http.httpPost(url, token, anatomyInfo, function (res) {
      console.log(res);
      if (res.code == 0) {
        var id = res.id;
        console.log(id);
        //上传照片(待定)
        that.uploadImage(id, imageType.anatomyImage);
        //上传视屏(待定)
        that.uploadVideo(id, imageType.anatomyImage);
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function () {
          wx.navigateBack()
          wx.redirectTo({
            url: '/pages/admin/home/anatomy/anatomy',
          })
        }, 1500)
      }
    })
  },

  uploadImage(id, type) {
    var that = this;
    //上传照片(待定)
    that.selectComponent("#image-upload").uploadImage(id, type);
  },

  uploadVideo(id, type) {
    var that = this;
    //上传照片(待定)
    that.selectComponent("#video-upload").uploadVideo(id, type);
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.initValidate();
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
      testAmount: {
        required: true,
        digits: true,
      },
      positiveAmount: {
        required: true,
        digits: true,
      },
      positiveRate: {
        required: true,
      }

    };
    var messages = {
      testAmount: {
        required: "请填写检测数",
        digits: '检测数填写不正确',
      },
      positiveAmount: {
        required: "请填写死阳性数",
        digits: '阳性数填写不正确',
      },
      positiveRate: {
        required: "请填写阳性率",
        digits: "阳性率填写不正确",
      }

    };
    this.WxValidate = new WxValidate(rules, messages)
  },

})