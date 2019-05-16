var config = require("../../../../../apis/config.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")
var icon = require("../../../../../apis/icon.js")
var imageType = require("../../../../../apis/imageType.js")
var dateFormat = require("../../../../../apis/dateFormat.js")

import WxValidate from '../../../../../utils/WxValidate.js'
const watch = require("../../../../../utils/watch.js");

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    infectionInfo: {}
  },


  getinfectionAmount: function (e) {
    var that = this;
    that.setData({
      infectionAmount: e.detail.value
    });
    if (that.data.detectionAmount && that.data.infectionAmount) {
      if (parseInt(that.data.infectionAmount) > parseInt(that.data.detectionAmount)) {
        that.setData({
          isNotSubmit:true
        })
        wx.showModal({
          title: '提示',
          content: '感染数必须小于调查数',
          showCancel: false,
        })
        return;
      }
    }
  },
  
  getdetectionAmount: function (e) {
    var that = this;
    if (e.detail.value && e.detail.value == 0) {
      that.setData({
        isNotSubmit: true
      })
      wx.showModal({
        title: '提示',
        content: '请输入有效的数字',
        showCancel: false,
      })
      return;
    }
    that.setData({
      detectionAmount: e.detail.value
    });
    if (that.data.detectionAmount && that.data.infectionAmount) {
      if (parseInt(that.data.infectionAmount) > parseInt(that.data.detectionAmount) ){
        wx.showModal({
          title: '提示',
          content: '感染数必须小于调查数',
          showCancel: false,
        })
        return;
      }
    }
  },

  getharmlessTreatAmount: function (e) {
    var that = this;
    that.setData({
      harmlessTreatAmount: e.detail.value
    });
    if (that.data.detectionAmount && that.data.harmlessTreatAmount) {
      if (parseInt(that.data.harmlessTreatAmount) > parseInt(that.data.detectionAmount) ) {
        that.setData({
          isNotSubmit: true
        })
        wx.showModal({
          title: '提示',
          content: '病变处理数必须小于调查数',
          showCancel: false,
        })
        return;
      }
    }
  },


  formSubmit: function (e) {
    var that = this;
    var infectionInfo = e.detail.value;
    if (!this.WxValidate.checkForm(infectionInfo)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    if(that.data.isNotSubmit){
      wx.showModal({
        title: '提示',
        content: '请正确输入数据参数',
        showCancel:false
      })
      return;
    }
    //表单提交
    var url = config.url.BASE_URL + '/infectionInfo/save';
    var token = app.globalData.token;
    http.httpPost(url, token, infectionInfo, function (res) {
      console.log(res);
      if (res.code == 0) {
        var id = res.id;
        console.log(id);
        //上传照片(待定)
        that.uploadImage(id, imageType.infectionImage);
        //上传视屏(待定)
        that.uploadVideo(id, imageType.infectionImage);
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
      detectionAmount: {
        required: true,
        digits: true,
      },
      infectionAmount: {
        required: true,
        digits: true,
      },
      harmlessTreatAmount: {
        required: true,
        digits: true,
      }

    };
    var messages = {
      detectionAmount: {
        required: "请填写调查数",
        digits: '调查数填写不正确',
      },
      infectionAmount: {
        required: "请填写感染数",
        digits: '感染数填写不正确',
      },
      harmlessTreatAmount: {
        required: "请填写病变处理数",
        digits: "病变处理数填写不正确",
      }

    };
    this.WxValidate = new WxValidate(rules, messages)
  },

})