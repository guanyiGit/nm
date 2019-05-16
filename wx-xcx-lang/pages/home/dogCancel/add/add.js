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
    },
    dogCancelReason: {
      dogCancelReasonIndex: 0,
      dogCancelReasonArray: []
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
        var msg = "";
        if (res.code == 9002){
          msg = wx.T.get("gaishebeiyibeibangdin")
        } else if (res.code == 9003) {
          msg = wx.T.get("gaishebeiyidiushi")
        } else if (res.code == 9004) {
          msg = wx.T.get("gaishebeiyisunhuai")
        }
        console.log(res);
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

  formSubmit: function(e) {
    var that = this;
    var dogCancel = e.detail.value; 
    dogCancel.traceId = that.data.dog.traceId;
    dogCancel.deviceNo = that.data.dog.deviceNo;
    if (!this.WxValidate.checkForm(dogCancel)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/dogCancel/save';
    var token = app.globalData.token;
    http.httpGet(url, token, dogCancel, function(res) {
      if (res.status == 200) {
        console.log(res)
        //上传照片(待定)
        var dogCancelId = res.data;
        that.uploadImage(dogCancelId, imageType.logoutDogImage);
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

        }, 1500)
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("quanzhizhuxiao") })

    var that = this;
    var dogIndex = JSON.parse(options.dogIndex);
    var isBindDevice = true;
    that.initValidate();
    if (!dogIndex.deviceNo) {
      isBindDevice = false
      that.setData({
        ['deviceDeal.deviceDealArray']: [{
          // name: "暂未绑定项圈",
          name: wx.T.get("zanweibangindxiangquan"),
          value: "-1"
        }]
      })
    }
    console.log(that.data.deviceDeal.deviceDealArray);
    that.initChangeReason(isBindDevice);
    that.setData({
      dog: dogIndex
    })

  },

  /**
   * 初始化更换原因
   */
  initChangeReason(isBindDevice) {
    var that = this;
    var url = config.url.BASE_URL + "/dogCancel/findTSysDict";
    var token = app.globalData.token;
    var params = {};
    http.httpGet(url, token, params, (res) => {
      if (res.status == 200) {
        console.log(res.data);
        var deviceDeal = res.data.deviceDeal;
        var dogCancelReason = res.data.dogCancelReason;
        if (isBindDevice) {
          that.setData({
            ['deviceDeal.deviceDealArray']: deviceDeal,
            ['dogCancelReason.dogCancelReasonArray']: dogCancelReason
          })
        } else {
          that.setData({
            ['dogCancelReason.dogCancelReasonArray']: dogCancelReason
          })
        }
      }
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



  bindReasonChange: function(e) {
    var that = this;
    that.setData({
      ['deviceDeal.deviceDealIndex']: e.detail.value
    })
  },

  bindCancelReason: function(e) {
    var that = this;
    that.setData({
      ['dogCancelReason.dogCancelReasonIndex']: e.detail.value
    })
  },

  uploadImage(id, type) {
    var that = this;
    //上传照片(待定)
    that.selectComponent("#image-upload").uploadImage(id, type);
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
      // deviceNo: {
      //   required: true,
      // },
      // changeReasons: {
      //   required: true,
      // },
    };
    var messages = {
      // deviceNo: {
      //   required: "请填写项圈编号",
      // },
      // changeReasons: {
      //   required: true,
      // },
    };
    this.WxValidate = new WxValidate(rules, messages)
  },

})