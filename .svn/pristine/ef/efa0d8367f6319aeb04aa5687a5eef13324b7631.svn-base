var video = require("../../../../apis/video.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var config = require("../../../../apis/config.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var icon = require("../../../../apis/icon.js")
var imageType = require("../../../../apis/imageType.js")
var QR_code = require("../../../../apis/QR_code.js")
import WxValidate from '../../../../utils/WxValidate.js'
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    scanIcon: icon.scan,
    neckletChange: {
      oldNeckletNo: "张三",
      newNeckletNo: "李四",
      changeReasons: "好友赠送",
      createtime: "2018-05-21 12:45",
      description: "xxxxxx"
    },
    deviceDeal: {
      deviceDealIndex: 0,
      deviceDealArray: []
    }
  },


  deviceNoBlur(e) {


    var that = this;
    var deviceNo = e.detail.value;
    if (deviceNo != undefined && deviceNo == '') {
      return;
    }
    that.checkDevice(e.detail.value);
  },

  //检查deviceNo是否绑定有犬只
  checkDevice(deviceNo) {
    var that = this;
    //表单提交
    var url = config.url.BASE_URL + '/dogInfo/checkDeviceIsUse';
    var token = app.globalData.token;
    var params = {
      deviceNo: deviceNo
    }
    http.httpGet(url, token, params, function(res) {
      //如果返回false,则设备未被绑定，提醒用户设备未关联犬只
      if (res.code == 0) {
        that.setData({
          deviceNo: deviceNo,
        })
      } else {
        var msg = res.msg;
        wx.showModal({
          title: '提示',
          content: msg,
          showCancel: false,
        })
      }
    })
  },

  scanEvent: function() {
    QR_code.scan(true)
      .then(imei => {
        this.checkDevice(imei);
      })


    // var that = this;
    // wx.scanCode({
    //   onlyFromCamera: true,
    //   success: function(res) {
    //     var deviceNo = res.result;//设备号
    //     if (deviceNo != undefined && deviceNo == '') {
    //       return;
    //     }
    //     var deviceNoIndex = deviceNo.indexOf("&&")
    //     console.log(">" + deviceNoIndex)
    //     if (deviceNoIndex != -1) {
    //       deviceNo = deviceNo.substring(0, deviceNoIndex);
    //       console.log(">>" + deviceNoIndex)
    //     }
    //     console.log("设备号：" + deviceNo)
    //     that.checkDevice(deviceNo);

    //     //var deviceNo = res.result;
    //     //that.checkDevice(deviceNo);
    //   },
    // })
    // var that = this;
    // wx.scanCode({
    //   onlyFromCamera: true,
    //   success: function(res) {
    //     var deviceNo = res.result;
    //     that.checkDevice(deviceNo);
    //   },
    // })
  },

  formSubmit: function(e) {
    var that = this;
    var neckletChange = e.detail.value;
    console.log("form:" + JSON.stringify(e.detail));
    neckletChange.traceId = that.data.dog.traceId;
    neckletChange.oldNeckletNo = that.data.dog.deviceNo;
    neckletChange.newNeckletNo = that.data.deviceNo;
    console.log(neckletChange);
    if (!this.WxValidate.checkForm(neckletChange)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/dogInfo/nectletChange/save';
    var token = app.globalData.token;
    http.httpGet(url, token, neckletChange, function(res) {
      if (res.status == 200) {
        wx.showToast({
          title: '更换成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function() {
          wx.navigateBack({
            delta: 2
          })
        }, 1500)
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.initChangeReason();
    that.initValidate();
    var dogIndex = JSON.parse(options.dogIndex);
    console.log(dogIndex)
    that.setData({
      dog: dogIndex
    })

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
   * 初始化更换原因
   */
  initChangeReason() {
    var that = this;
    var url = config.url.BASE_URL + "/dogCancel/findTSysDict";
    var token = app.globalData.token;
    var params = {};
    http.httpGet(url, token, params, (res) => {
      console.log(res);
      if (res.status == 200) {
        var deviceDeal = res.data.deviceDeal;
        that.setData({
          ['deviceDeal.deviceDealArray']: deviceDeal
        })
      }
    })
  },

  bindReasonChange: function(e) {
    var that = this;
    that.setData({
      ['deviceDeal.deviceDealIndex']: e.detail.value
    })
    console.log("deviceDealIndex", that.data.deviceDeal.deviceDealIndex);
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
      newNeckletNo: {
        required: true,
      },
      changeReasons: {
        required: true,
      },
    };
    var messages = {
      newNeckletNo: {
        required: "请填写新项圈编号",
      },
      changeReasons: {
        required: true,
      },
    };
    this.WxValidate = new WxValidate(rules, messages)
  },

})