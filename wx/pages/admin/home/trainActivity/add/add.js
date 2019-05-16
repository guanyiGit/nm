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
    activityInfo: {},
    activityTime: dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")
  },
  bindDataChange: function (e) {
    this.setData({
      activityTime: e.detail.value
    });
  },

  formSubmit: function (e) {
    var that = this;
    var activityInfo = e.detail.value;
    console.log(activityInfo);
    if (!this.WxValidate.checkForm(activityInfo)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/activityInfo/saveTrainActivity';
    var token = app.globalData.token;
    http.httpPost(url, token, activityInfo, function (res) {
      console.log(res);
      if (res.code == 0) {
        var id = res.id;
        console.log(id);
        //上传照片(待定)
        that.uploadImage(id, imageType.activityImage);
        //上传视屏(待定)
        that.uploadVideo(id, imageType.activityImage);
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function () {
          wx.navigateBack()
          wx.redirectTo({
            url: '/pages/admin/home/trainActivity/trainActivity',
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
    this.setData({

    });

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
      activityAddress: {
        required: true,
      },
      activitySubject: {
        required: true,

      },
      participateAmount: {
        required: true,
        digits: true,
      },
      activityContent: {
        required: true,
      },
      activityTarget: {
        required: true,
      }

    };
    var messages = {
      activityAddress: {
        required: "请填写地点",
      },
      activitySubject: {
        required: "请填写主题",
      },
      participateAmount: {
        required: "请填写参与人数",
        digits: "参与人数填写不正确",
      },
      activityContent: {
        required: "请填写内容",
      },
      activityTarget: {
        required: '请填写培训对象',
      }

    };
    this.WxValidate = new WxValidate(rules, messages)
  },

})