var config = require("../../../../../apis/config.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")
var icon = require("../../../../../apis/icon.js")
var imageType = require("../../../../../apis/imageType.js")
var dateFormat = require("../../../../../apis/dateFormat.js")

import WxValidate from '../../../../../utils/WxValidate.js'
const watch = require("../../../../../utils/watch.js");

const {
  $Toast
} = require('../../../../../dist/base/index');

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    infectionInfo: {}
  },

  /**ganranshubixuxiaoyutiaochashu */
  getinfectionAmount: function (e) {
    var that = this;
    that.setData({
      infectionAmount: e.detail.value
    });
    if (that.data.detectionAmount && that.data.infectionAmount) {
      if (parseInt(that.data.infectionAmount) > parseInt(that.data.detectionAmount)) {
        $Toast({
          type: "error",
          content: wx.T.get("ganranshubixuxiaoyutiaochashu"),
          duration: 2,
          mask: false
        })
        return;
      }
    } 
  },
  
  getdetectionAmount: function (e) {
    var that = this;
    that.setData({
      detectionAmount: e.detail.value
    })
    if (that.data.detectionAmount && that.data.infectionAmount) {
      if (parseInt(that.data.infectionAmount) > parseInt(that.data.detectionAmount)) {
        $Toast({
          type: "error",
          content: wx.T.get("ganranshubixuxiaoyutiaochashu"),
          duration: 2,
          mask: false
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
        $Toast({
          type: "error",
          content: wx.T.get("ganranshubixuxiaoyutiaochashu"),
          duration: 2,
          mask: false
        })
        return;
      }
    }
  },

  checkNumber: function (e) {
    var value = e.detail.value;
    var reg = new RegExp("^[0-9]*$");
    console.log(!reg.test(value));
    if (value && !reg.test(value)) {
      $Toast({
        type: "error",
        content: wx.T.get("qingshuruyouxiaoshuzi"),
        duration: 2,
        mask: false
      });
      var flagAmount = "";
      var index = e.currentTarget.dataset.index
      if (index == 1 ){
        flagAmount = "detectionAmount"
      } else if (index == 2){
        flagAmount = "infectionAmount"
      } else if (index == 3){
        flagAmount = "harmlessTreatAmount"
      }
      this.setData({
        [flagAmount]: ""
      })
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
          // title: '添加成功',
          title: wx.T.get("tianjiachenggong"),
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
    wx.setNavigationBarTitle({ title: wx.T.get("xinzengniuyangzangqichuli") })
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
      title: wx.T.get("tishi"),
      confirmText: wx.T.get("quedin")
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
    // var messages = {
    //   detectionAmount: {
    //     required: "请填写调查数",
    //     digits: '调查数填写不正确',
    //   },
    //   infectionAmount: {
    //     required: "请填写感染数",
    //     digits: '感染数填写不正确',
    //   },
    //   harmlessTreatAmount: {
    //     required: "请填写病变处理数",
    //     digits: "病变处理数填写不正确",
    //   }

    // };
    var  messages = {
      detectionAmount: {
        required: wx.T.get("qingtianxiedianchashu"),
        digits: wx.T.get("dianchashutianxiebuzhengque"),
      },
      infectionAmount: {
        required: wx.T.get("qingtianxieganranshu"),
        digits: wx.T.get("ganranshutianxiebuzhengque"),
      },
      harmlessTreatAmount: {
        required: wx.T.get("qingtianxiebingbianchulishu"),
        digits: wx.T.get("bingbianshutianxiebuzhengque"),
      }
    };
    this.WxValidate = new WxValidate(rules, messages)
  },

})