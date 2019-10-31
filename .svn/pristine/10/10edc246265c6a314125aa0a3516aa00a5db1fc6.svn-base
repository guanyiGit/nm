var video = require("../../../../apis/video.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var config = require("../../../../apis/config.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var icon = require("../../../../apis/icon.js")
var imageType = require("../../../../apis/imageType.js")
import WxValidate from '../../../../utils/WxValidate.js'

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    ishasDevice:false,
    scanIcon: icon.scan,
    manureHandle: {
      manureHandleIndex: 0,
      manureHandleArray: []
    }
  },

  /**
   * form表单提交
   */
  formSubmit(e) {
    var that = this;
    var manureDisposal = e.detail.value;
    if (!this.WxValidate.checkForm(manureDisposal)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/manureDisposal/save';
    manureDisposal.dealTime = dateFormat(that.data.dealTime, "yyyy-MM-dd");
    var token = app.globalData.token;
    http.httpPost(url, token, manureDisposal, function(res) {
      console.log(res);
      if (res.code == 0) {
        var manureDisposalId = res.id;
        //上传照片(待定)
        that.uploadImage(manureDisposalId, imageType.manureImage);
        //上传照片(待定)
        that.uploadVideo(manureDisposalId, imageType.manureImage);
        wx.showToast({
          // title: '添加成功',
          title: wx.T.get("tianjiachenggong"),
          icon: "success",
          duration: 1500
        });
        setTimeout(function() {
          wx.navigateBack({
            delta: 2
          })
        }, 1500);
        
      } else {
        wx.showModal({
          // title: '失败',
          title: wx.T.get("shibai"),
          // content: '添加失败，请稍后再试！！',
          content: wx.T.get("tianjiashibai") + "," + wx.T.get("qingshaohouzaishi")+"!!",
          showCancel: true,
          confirmText: wx.T.get("quedin"),
          cancelText: wx.T.get("cancel"),
        })
        return;
      }
    })
  },

  deviceNoBlur(e) {
    var that = this;
    var deviceNo = e.detail.value;
    if (deviceNo != undefined && deviceNo == '') {
      return;
    }
    that.checkDevice(e.detail.value);
    that.getInfo(e.detail.value);
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
      console.log(res);
      //如果返回false,则设备未被绑定，提醒用户设备未关联犬只
      if (res.code == 9000) {
        wx.showModal({
          // title: '提示',
          title: wx.T.get("tishi"),
          // content: '该溯源Id不存在',
          content: wx.T.get("gaisuyuanidbucunzai"),
          confirmText: wx.T.get("quedin"),
          showCancel: false,
        })
      } else if (res.code == 9001) {
        that.setData({
          deviceNo: deviceNo,
        })
        that.getInfo(deviceNo);
      } else {
        wx.showModal({
          // title: '提示',
          title: wx.T.get("tishi"),
          // content: '该设备未绑定任何犬只',
          content: wx.T.get("gaishebeiweibangdinquanzhi"),
          showCancel: false,
          confirmText: wx.T.get("quedin"),
        })
      }
    })
  },

  scanEvent: function() {
    var that = this;
    wx.scanCode({
      onlyFromCamera: true,
      success: function(res) {
        var deviceNo = res.result;
        that.checkDevice(deviceNo);
      },
    })
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


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.initValidate();
    this.getProcessingMethod();
    var traceId = options.traceId;
    if (traceId) {
      this.setData({
        traceId: traceId,
        ishasDevice:true
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
   * 监听date改变
   */
  bindDataChange: function(e) {
    var that = this;
    that.setData({
      dealTime: e.detail.val
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
      processMode: {
        required: true,
      },
      methodDes: {
        required: true,
      }
    };
    var messages = {
      traceId: {
        required: "请填写溯源id",
      },
      processMode: {
        required: "请填写处理方法",
      },
      methodDes: {
        required: "请填写方法说明",
      }
    };
    messages = {
      traceId: {
        required: wx.T.get("qingtianxieshuyuanID"),
      },
      processMode: {
        required: wx.T.get("qingtianxiechulifangfa"),
      },
      methodDes: {
        required: wx.T.get("qingtianxiefangfashuoming"),
      }
    };
    this.WxValidate = new WxValidate(rules, messages)
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
   * 初始化粪便处理方式下拉()
   */
  getProcessingMethod: function(res) {
    var that = this;
    var url = config.url.BASE_URL + "/manureDisposal/initSelectData";
    var token = app.globalData.token;
    var params = {
      type: "process_mode"
    }
    http.httpGet(url, token, params, function(res) {
      console.log(res);
      that.setData({
        ["manureHandle.manureHandleArray"]: res
      })
    })
  },

  bindManureHandleChange(e) {
    this.setData({
      "manureHandle.manureHandleIndex": e.detail.value
    })
  },

})