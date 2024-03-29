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
    if(that.data.deviceNo){
      manureDisposal.deviceNo = that.data.deviceNo
    }
    console.log(manureDisposal);
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
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function() {
          wx.navigateBack()
        }, 1500);
        
      } else {
        wx.showModal({
          title: '失败',
          content: '添加失败，请稍后再试！！',
          showCancel: true,
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
          title: '提示',
          content: '该溯源Id不存在',
          showCancel: false,
        })
      } else if (res.code == 9001) {
        that.setData({
          deviceNo: deviceNo,
        })
        that.getInfo(deviceNo);
      } else {
        wx.showModal({
          title: '提示',
          content: '该设备未绑定任何犬只',
          showCancel: false,
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
  getInfo(deviceNo) {
    var that = this;
    var url = config.url.BASE_URL + "/dogInfo/findOne3";
    var token = app.globalData.token;
    var params = {
      deviceNo: deviceNo
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
    var deviceNo = options.deviceNo;
    if (deviceNo) {
      this.setData({
        deviceNo: deviceNo,
        ishasDevice:true
      })
      this.getInfo(deviceNo);
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
    })
  },


  //验证函数
  initValidate: function() {
    // 验证字段的规则
    var rules = {
      deviceNo: {
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
      deviceNo: {
        required: "请填写溯源id",
      },
      processMode: {
        required: "请填写处理方法",
      },
      methodDes: {
        required: "请填写方法说明",
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