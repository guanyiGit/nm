var config = require("../../../../apis/config.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var icon = require("../../../../apis/icon.js")
var imageType = require("../../../../apis/imageType.js")

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    scanIcon: icon.scan,
  },


  scanEvent: function () {
    var that = this;
    wx.scanCode({
      onlyFromCamera: true,
      success: function (res) {
        var deviceNo = res.result;
        that.checkDevice(deviceNo);
      },
    })
  },

  deviceNoBlur(e) {
    var that = this;
    if (e.detail.value) {
      that.checkDevice(e.detail.value);
    }
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
    http.httpGet(url, token, params, function (res) {
      //如果返回false,则设备未被绑定，提醒用户设备未关联犬只
      if (res.code == 0) {
        that.setData({
          deviceNo: deviceNo,
        })
      } else {
        var msg = "";
        if (res.code == 9002) {
          msg = wx.T.get("gaishebeiyibeibangdin")
        } else if (res.code == 9003) {
          msg = wx.T.get("gaishebeiyidiushi")
        } else if (res.code == 9004) {
          msg = wx.T.get("gaishebeiyisunhuai")
        }
        wx.showModal({
          // title: '提示',
          title: wx.T.get("tishi"),
          content: msg,
          showCancel: false,
          confirmText: wx.T.get("quedin"),
        })
      }
    })
  },

  bind() {
    var that = this;
    var deviceNo = that.data.deviceNo;
    var traceId = that.data.traceId;
    var deviceRefDog = {
      traceId: traceId,
      deviceNo: deviceNo
    }
    var url = config.url.BASE_URL + "/dogInfo/insertDogDeviceNo"
    var token = app.globalData.token;
    var params = deviceRefDog;
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      if (res.status) {
        if (res.status == 200) {
          wx.showModal({
            // title: '提示',
            title: wx.T.get("tishi"),
            // content: '绑定成功',
            content: wx.T.get("bingdinchenggong"),
            showCancel: false,
            confirmText: wx.T.get("quedin"),
          })
        } else if (res.status == 400) {
          wx.showModal({
            // title: '提示',
            title: wx.T.get("tishi"),
            // content: '绑定失败',
            content: wx.T.get("bingdinshibai"),
            showCancel: false,
            confirmText: wx.T.get("quedin"),
          })
        }
        setTimeout(function () {
          wx.navigateBack();
        }, 1500)
      }
    })
  },

  unbind(){
    wx.navigateBack();
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("bangdinxiangquan") })
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