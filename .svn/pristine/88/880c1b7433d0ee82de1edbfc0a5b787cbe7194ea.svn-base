var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var icon = require("../../../../apis/icon.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")

import WxValidate from '../../../../utils/WxValidate.js'

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    scanIcon: icon.scan,
    antiepidemicLength: 2,
    antiepidemicArray: [
      {
        drugId: 1,
        antiepidemicDate: 1,
        antiepidemicPeriod: 1,
        deviceNo: "xxx",
        description: "xxx",
        type: 1,
        orgId: 1,
        protector: 1,
        epidemicPic: image.dog1Image,
      },
      {
        drugId: 1,
        antiepidemicDate: 1,
        antiepidemicPeriod: 1,
        deviceNo: "xxx",
        description: "xxx",
        type: 1,
        orgId: 1,
        protector: 1,
        epidemicPic: image.dog1Image,
      }
    ],
  },



  isShowDogDes() {
    var that = this;
    that.setData({
      ["dogInfo.isShowDogDes"]: !that.data.dogInfo.isShowDogDes
    })
  },

  isShowDogOwnerDes() {
    var that = this;
    that.setData({
      ["dogInfo.isShowDogOwnerDes"]: !that.data.dogInfo.isShowDogOwnerDes
    })
  },

  isShowDesForManure(e) {
    console.log(e);
    var that = this;
    var index = e.currentTarget.dataset.index;
    var key = 'manureArray[' + index + '].isShowDes';
    that.setData({
      [key]: !that.data.manureArray[index].isShowDes
    })
  },

  isShowModeDesForManure(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var key = 'manureArray[' + index + '].isShowModeDes';
    that.setData({
      [key]: !that.data.manureArray[index].isShowModeDes
    })
  },

  /**显示防疫信息描述详情 */
  isShowMicDesForMic(e) {
    console.log(e);
    var that = this;
    var index = e.currentTarget.dataset.index;
    var key = 'antiepidemicArray[' + index + '].isShowDes';
    that.setData({
      [key]: !that.data.antiepidemicArray[index].isShowDes
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
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      var antiepidemicArray = res.data.antiepidemic;
      var manureArray = res.data.manureList;
      for (var index in antiepidemicArray) {
        antiepidemicArray[index].antiepidemicDate = dateFormat(antiepidemicArray[index].antiepidemicDate, "yyyy-MM-dd HH:mm")
        antiepidemicArray[index].isShowDes = true;
      }
      for (var index in manureArray) {
        manureArray[index].dealTime = dateFormat(manureArray[index].dealTime, "yyyy-MM-dd HH:mm");
        manureArray[index].isShowDes = true;
        manureArray[index].isShowModeDes = true;
      }
      var dogInfo = res.data.dogInfo;
      dogInfo.createDate = dateFormat(dogInfo.createDate, "yyyy-MM-dd HH:mm");
      dogInfo.birthDay = dateFormat(dogInfo.birthDay, "yyyy-MM-dd");
      dogInfo.dowcreateDate = dateFormat(dogInfo.dowcreateDate, "yyyy-MM-dd HH:mm");
      dogInfo.isShowDogDes = true;
      dogInfo.isShowDogOwnerDes = true;
      //获取防疫长度
      var antiepidemicLength = antiepidemicArray.length;
      if (antiepidemicLength >= 2) {
        antiepidemicLength = 2
      }
      //获取防疫长度
      var manureLength = manureArray.length;
      if (manureLength >= 2) {
        manureLength = 2
      }

      that.setData({
        manureArray: manureArray,
        manureLength: manureLength,

        antiepidemicArray: antiepidemicArray,
        antiepidemicLength: antiepidemicLength,

        dogInfo: dogInfo,
        dogPicList: res.data.dogPicList,
        ownerPicList: res.data.ownerPicList,
      })
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获取获取溯源id
    var traceId = options.traceId;
    this.setData({
      traceId: traceId
    })
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
    this.getInfo(this.data.traceId);
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

  toAntiepidemicAddPage() {
    wx.navigateTo({
      url: '/pages/home/antiepidemic/add/add?deviceNo=' + this.data.deviceNo,
    })
  },

  toManureAddPage() {
    wx.navigateTo({
      url: '/pages/home/manure/add/add?deviceNo=' + this.data.deviceNo,
    })
  },

  toOwnerChangeAddPage() {
    wx.navigateTo({
      url: '/pages/home/ownerChange/add/add?deviceNo=' + this.data.deviceNo,
    })
  },

  toDeviceChangePage() {
    wx.navigateTo({
      url: '/pages/home/neckletChange/add/add?deviceNo=' + this.data.deviceNo,
    })
  },

  toCorpseAddPage() {
    wx.navigateTo({
      url: '/pages/home/corpse/add/add?deviceNo=' + this.data.deviceNo,
    })
  },

  //预览图片
  previewImageDog(e) {
    //获取当前图片的下标
    console.log(e);
    var index = e.target.dataset.index;
    //所有图片
    var imageList = this.data.dogPicList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },

  //预览图片
  previewImageOwner(e) {
    //获取当前图片的下标
    console.log(e);
    var index = e.target.dataset.index;
    //所有图片
    var imageList = this.data.ownerPicList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },


  //预览图片
  previewImageAntiepidemic(e) {
    //获取当前图片的下标
    console.log(e);
    var antiepidemicindex = e.target.dataset.antiepidemicindex;
    var index = e.target.dataset.index;

    var antiepidemic = this.data.antiepidemicArray[antiepidemicindex];
    var imageList = antiepidemic.picList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },

  //预览图片
  previewImageManure(e) {
    //获取当前图片的下标
    console.log(e);
    var manureindex = e.target.dataset.manureindex;
    var index = e.target.dataset.index;
    var manure = this.data.manureArray[manureindex];
    var imageList = manure.urlList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },

  /**
   * 
   */
  scanEvent: function () {
    var that = this;
    wx.scanCode({
      onlyFromCamera: true,
      success: function (res) {
        var deviceNo = res.result;
        that.checkDeviceNo(deviceNo);
      },
    })
  },

  deviceNoBlur(e) {
    var that = this;
    if (e.detail.value) {
      that.checkDeviceNo(e.detail.value);
    }
  },

  //检查deviceNo是否绑定有犬只
  checkDeviceNo(deviceNo) {
    var that = this;
    //表单提交
    var url = config.url.BASE_URL + '/dogInfo/checkDeviceIsUse';
    var token = app.globalData.token;
    var params = {
      deviceNo: deviceNo
    }
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      //如果返回false,则设备未被绑定，提醒用户设备未关联犬只
      if (res.code == 9000) {
        wx.showModal({
          title: '提示',
          content: "该设备不存在",
          showCancel: false,
        })
      } else if (res.code == 9002) {
        that.setData({
          deviceNo: deviceNo,
        })
        wx.showModal({
          title: '提示',
          content: "该设备已被绑定",
          showCancel: false,
        })
      }else{
        that.setData({
          deviceNo: deviceNo,
        })
      }
    })
  },

  bind(){
    var that = this;
    var deviceNo = that.data.deviceNo;
    if (!deviceNo){
      wx.showModal({
        title: '提示',
        content: '请绑定项圈',
        showCancel: false,
      })
      return;
    }
    var traceId = that.data.traceId;
    var deviceRefDog = {
      traceId: traceId,
      deviceNo: deviceNo
    }
    var url = config.url.BASE_URL + "/dogInfo/insertDogDeviceNo"
    var token = app.globalData.token;
    var params = deviceRefDog;
    http.httpGet(url,token,params,function(res){
      console.log(res);
      if (res.status){
        if (res.status == 200){
          wx.showModal({
            title: '提示',
            content: '绑定成功',
            showCancel: false,
          })
          setTimeout(function () {
            wx.navigateBack();
          }, 1500)
        } else if(res.status == 400){
          wx.showModal({
            title: '提示',
            content: '绑定失败',
            showCancel: false,
          })
        }
        
      }
    })
  },

  unbind() {
    wx.navigateBack();
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
      deviceNo: {
        required: true,
      },
    };
    var messages = {
      deviceNo: {
        required: "请绑定项圈",
      },
    };
    this.WxValidate = new WxValidate(rules, messages)
  },
})