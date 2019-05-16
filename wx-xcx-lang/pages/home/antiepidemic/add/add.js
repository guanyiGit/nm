var video = require("../../../../apis/video.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var config = require("../../../../apis/config.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var icon = require("../../../../apis/icon.js")
var imageType = require("../../../../apis/imageType.js")
import WxValidate from '../../../../utils/WxValidate.js'
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    scanIcon: icon.scan,
    deviceNo: "",
    'type': {
      typeIndex: 0,
      // typeArray: ["春防", "秋防", "月月投药"]
      typeArray: [wx.T.get("chunfang"), wx.T.get("qiufang"), wx.T.get("yueyuetouyao")]
    },
    drug: {
      drugIndex: 0,
      drugArray: ["比硅酮咀嚼片"]
    },
    antiepidemicDate: "",
    antiepidemicPeriod: {
      antiepidemicPeriodIndex: 0,
      antiepidemicPeriodArray: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
    },
    video: {
      src: video.video1,
    },

  },




  /**
   * 获取扫一扫详情信息
   */
  getInfo(traceId) {
    var that = this;
    var url = config.url.BASE_URL + "/dogInfo/findOne3";
    var token = app.globalData.token;
    var params = {
      traceId: traceId
    }
    http.httpGet(url, token, params, function(res) {
      var dogInfo = res.data.dogInfo;
      dogInfo.birthDay = dateFormat(dogInfo.birthDay, "yyyy-MM-dd");
      that.setData({
        dogInfo: dogInfo,
      })
    })
  },

  //表单提交
  formSubmit: function(e) {
    var that = this;
    var antiepidemic = e.detail.value;
    if (!this.WxValidate.checkForm(antiepidemic)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    console.log(antiepidemic);
    var url = config.url.BASE_URL + '/antiepidemic/save';
    var token = app.globalData.token;
    http.httpPost(url, token, antiepidemic, function (res) {
      var antiepidemicId = res.id;
      if (res.code == 0) {
        //上传照片(待定)
        that.uploadImage(antiepidemicId, imageType.antiepidemicImage);
        //上传视屏(待定)
        that.uploadVideo(antiepidemicId, imageType.antiepidemicImage);
        wx.showToast({
          // title: '添加成功',
          title: wx.T.get("tianjiachenggong"),
          icon: "success",
          duration: 1500
        });
        setTimeout(function () {
          wx.navigateBack({
            delta: 2
          })
        }, 1500)
      }
    })
  },


  /**获取当前时间 */
  getDate: function() {
    var nowData = dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
    this.setData({
      antiepidemicDate: nowData
    })
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getDate();
    this.getDrug();
    this.initValidate();
    var traceId = options.traceId;
    if (traceId) {
      this.setData({
        traceId: traceId
      })
      this.getInfo(traceId);
    }
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
    wx.setNavigationBarTitle({ title: wx.T.get("chakanxiangqing") })

    this.setData({
      'type': {
        typeIndex: 0,
        typeArray: [wx.T.get("chunfang"), wx.T.get("qiufang"), wx.T.get("yueyuetouyao")]
      },
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



  /**
   * 获取防疫药品列表
   */
  getDrug() {
    var that = this;
    var url = config.url.BASE_URL + "/antiepidemic/getDrug";
    var token = app.globalData.token;
    var params = {
      type: 0
    }
    http.httpGet(url, token, params, function(res) {
      that.setData({
        ["drug.drugArray"]: res.data
      })
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

  bindDeviceChange: function(e) {
    var that = this;
    that.setData({
      ['device.deviceIndex']: e.detail.value
    })
  },

  bindTypeChange: function(e) {
    var that = this;
    that.setData({
      ['type.typeIndex']: e.detail.value
    })
  },

  /**
   * 改变时间事件
   */
  dataChangeEvent(e) {
    var that = this;
    var date = e.detail.val;
    that.setData({
      antiepidemicDate: date
    })
  },

  bindAntiepidemicPeriodChange: function(e) {
    var that = this;
    that.setData({
      ['antiepidemicPeriod.antiepidemicPeriodIndex']: e.detail.value
    })
  },

  bindDrugChange: function(e) {
    var that = this;
    that.setData({
      ['drug.drugIndex']: e.detail.value
    })
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
  initValidate: function() {
    // 验证字段的规则
    var rules = {
      traceId: {
        required: true,
      },

    };
    var messages = {
      traceId: {
        // required: "请填写溯源id",
        required: wx.T.get("qingtianxieshuyuanID"),
      }
    };
    this.WxValidate = new WxValidate(rules, messages)
  },


})