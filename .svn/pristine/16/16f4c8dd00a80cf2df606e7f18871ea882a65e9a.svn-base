var config = require("../../../../apis/config.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var icon = require("../../../../apis/icon.js")
var imageType = require("../../../../apis/imageType.js")
var dateFormat = require("../../../../apis/dateFormat.js")

import WxValidate from '../../../../utils/WxValidate.js'

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ishasDevice: false,
    scanIcon: icon.scan,
    corpse: {
      deviceNo: "GDQ201707006214",
      causeOfDeath: "疾病", //死亡原因
      processingMethod: "填埋",
      methodDescription: "填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋",
      dealTime: "2018-05-21 12:45",
      date: "",
      time: "",
      operator: "王军",
      description: "xxxxxx"
    },
    //尸体处理
    corpsehandle: {
      corpsehandleIndex: 0,
      corpsehandleArray: []
    },
    //死亡处理
    causeOfDeath: {
      causeOfDeathIndex: 0,
      causeOfDeathArray: []
    },

    deviceDeal: {
      deviceDealIndex: 0,
      deviceDealArray: []
    }
  },

  //效验溯源id是否合法
  checkDeviceNo(e){
    console.log(e);
    var deviceNo = e.detail.value;
    if (deviceNo != undefined && deviceNo == ''){
        return;
    }
    this.checkDevice(deviceNo);
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

  getNowDate() {
    var nowdate = dateFormat(new Date(), "yyyy-MM-dd");
    this.setData({
      ["corpse.dealTime"]: nowdate
    })
  },

  bindCauseOfDeathChange(e) {
    this.setData({
      "causeOfDeath.causeOfDeathIndex": e.detail.value
    })
  },

  bindCorpsehandleChange(e) {
    this.setData({
      "corpsehandle.corpsehandleIndex": e.detail.value
    })
  },

  bindDataChange(e) {
    this.setData({
      "corpse.date": e.detail.value
    })
  },

  bindTimeChange(e) {
    this.setData({
      "corpse.time": e.detail.value
    })
  },

  formSubmit: function(e) {
    var that = this;
    debugger
    var corpseDisposal = e.detail.value;
    if (that.data.deviceNo) {
      corpseDisposal.deviceId = that.data.deviceNo
    }
    //如果没有绑定项圈，则默认项圈处理为4
    if (!corpseDisposal.deviceDealWay){
      corpseDisposal.deviceDealWay = 4;
    }
    if (!this.WxValidate.checkForm(corpseDisposal)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/corpseDisposal/save';
    var token = app.globalData.token;
    http.httpPost(url, token, corpseDisposal, function(res) {
      console.log("corpseAdd", res);
      if (res.code == 0) {
        var id = res.id;
        console.log(id);
        //上传照片(待定)
        that.uploadImage(id, imageType.corpseImage);
        //上传视屏(待定)
        that.uploadVideo(id, imageType.corpseImage);
        wx.showToast({
          title: '添加成功',
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
      if (!res.data) {
        return;
      }
      var dogInfo = res.data.dogInfo;

      dogInfo.birthDay = dateFormat(dogInfo.birthDay, "yyyy-MM-dd");
      that.setData({
        dogInfo: dogInfo,
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


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getNowDate();
    this.initValidate();
    this.getCauseOfDeath();
    this.getProcessingMethod();
    var dogIndex = JSON.parse(options.dogIndex);
    var traceId = dogIndex.traceId;
    if (!dogIndex.deviceNo){
      var deviceDeal = [{
        name:"未绑定项圈"
      }]
      this.setData({
        ['deviceDeal.deviceDealArray']: deviceDeal
      })
    }else {
      this.initChangeReason();
    }
    if (traceId) {
      this.setData({
        traceId: traceId,
        ishasDevice: true
      })
    }
    this.getInfo(traceId);
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

  /**
   * 初始化更换原因
   */
  initChangeReason() {
    var that = this;
    var url = config.url.BASE_URL + "/dogCancel/findTSysDict";
    var token = app.globalData.token;
    var params = {};
    http.httpGet(url, token, params, (res) => {
      if (res.status == 200) {
        console.log(res);
        var deviceDeal = res.data.deviceDeal;
        that.setData({
          ['deviceDeal.deviceDealArray']: deviceDeal
        })
      }
    })
  },

  //验证函数
  initValidate: function() {
    // 验证字段的规则
    var rules = {
      traceId: {
        required: true,
      },
      causeOfDeath: {
        required: true,
      },
      methodDescription: {
        required: true,
      }

    };
    var messages = {
      traceId: {
        required: "请填写溯源ID",
      },
      causeOfDeath: {
        required: "请填写死亡原因",
      },
      methodDescription: {
        required: "请填写方法说明",
      }
    };
    this.WxValidate = new WxValidate(rules, messages)
  },

  /**
   * 初始化尸体处理方式下拉()
   */
  getProcessingMethod: function(res) {
    var that = this;
    var url = config.url.BASE_URL + "/manureDisposal/initSelectData";
    var token = app.globalData.token;
    var params = {
      type: "corpse_handle"
    }
    http.httpGet(url, token, params, function(res) {
      console.log(res);
      that.setData({
        ["corpsehandle.corpsehandleArray"]: res
      })
    })
  },

  /**
   * 初始化死亡原因下拉
   */
  getCauseOfDeath: function(res) {
    var that = this;
    var url = config.url.BASE_URL + "/manureDisposal/initSelectData";
    var token = app.globalData.token;
    var params = {
      type: "casuse_of_death"
    }
    http.httpGet(url, token, params, function(res) {
      console.log(res);
      that.setData({
        ["causeOfDeath.causeOfDeathArray"]: res
      })
    })

  }

})