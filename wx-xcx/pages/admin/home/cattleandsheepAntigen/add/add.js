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
    antibodyInfo: {},
    qualifiedAmount: null,
    testAmount: null,
    qualifiedRate: null
  },

  getqualifiedAmount: function(e) {
    var that = this;
    that.setData({
      qualifiedAmount: e.detail.value
    });
    if (that.data.testAmount && that.data.qualifiedAmount) {
      that.setData({
        qualifiedRate: (that.data.qualifiedAmount / that.data.testAmount * 100).toFixed(2)
      });
    } else {
      that.setData({
        qualifiedRate: ""
      });
    }

  },
  gettestAmount: function(e) {
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
      testAmount: e.detail.value
    });
    if (that.data.testAmount && that.data.qualifiedAmount) {
      that.setData({
        qualifiedRate: (that.data.qualifiedAmount / that.data.testAmount * 100).toFixed(2)
      });
    } else {
      that.setData({
        qualifiedRate: ""
      });
    }
  },

  formSubmit: function(e) {
    var that = this;
    if (that.data.isNotSubmit) {
      wx.showModal({
        title: '提示',
        content: '请正确输入数据参数',
        showCancel: false
      })
      return;
    }
    var antibodyInfo = e.detail.value;
    if (!this.WxValidate.checkForm(antibodyInfo)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    if (antibodyInfo.positiveRate > 100) {
      wx.showModal({
        title: '提示',
        content: '请输入正确的数字',
        showCancel: false
      })
      return;
    };
    //表单提交
    var url = config.url.BASE_URL + '/antibodyDetection/save';
    var token = app.globalData.token;
    http.httpPost(url, token, antibodyInfo, function(res) {
      console.log(res);
      if (res.code == 0) {
        var id = res.id;
        console.log(id);
        //上传照片(待定)
        that.uploadImage(id, imageType.antibodyImage);
        //上传视屏(待定)
        that.uploadVideo(id, imageType.antibodyImage);
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function() {
          wx.navigateBack()
          wx.redirectTo({
            url: '/pages/admin/home/cattleandsheepAntigen/cattleandsheepAntigen',
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

  watch: {
    qualifiedRate: function(newVal, oldVal) {
      console.log(newVal, oldVal);
      if (newVal > 100) {
        wx.showModal({
          title: '提示',
          content: '请输入正确的数字',
          showCancel: false
        })
      }
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.initValidate();
    watch.setWatcher(this);
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
  //报错 
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },


  //验证函数
  initValidate: function() {
    // 验证字段的规则
    var rules = {
      testAmount: {
        required: true,
        digits: true,
      },
      qualifiedAmount: {
        required: true,
        digits: true,
      }


    };
    var messages = {
      testAmount: {
        required: "请填写检测数",
        digits: '检测数填写不正确',
      },
      qualifiedAmount: {
        required: "请填写合格数",
        digits: '合格数填写不正确',
      }

    };
    this.WxValidate = new WxValidate(rules, messages)
  },

})