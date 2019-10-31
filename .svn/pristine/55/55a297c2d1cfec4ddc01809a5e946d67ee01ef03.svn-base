var image = require("../../../../../apis/image.js")
var formatDate = require("../../../../../apis/dateFormat.js")
var config = require("../../../../../apis/config.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")
var icon = require("../../../../../apis/icon.js")
var imageType = require("../../../../../apis/imageType.js")

import WxValidate from '../../../../../utils/WxValidate.js'

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    breed: {
      breedIndex: 0,
      breedArray: ["拉布拉多", "德牧"]
    },
    age: {
      ageIndex: 0,
      ageArray: ["1岁", "2岁", "3岁"]
    },
    sex: {
      sexIndex: 0,
      sexArray: ["公", "母"]
    },
    dealTime:"2018-05-21 12:45",
  },

  getNowDate() {
    var nowdate = formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    this.setData({
      dealTime: nowdate
    })
  },

  getStratDayNo() {
    var nowdate = formatDate(new Date(), "yyyyMMddHHmmss") + Math.floor(Math.random() * 10);
    this.setData({
      no: nowdate
    })
    return nowdate;
  },

  /**
   * 检查流浪犬编号是否存在
   */
  checkNo(e){
    var url = config.url.BASE_URL + '/strayDog/checkNo';
    var token = app.globalData.token;
    var params = {
      no:e.detail.value
    }
    http.httpGet(url,token,params,function(res){
      if(res.status == 400){
          wx.showModal({
            title: '提示',
            content: res.msg,
          })
          return;
      }
    })
  },

  //表单提交
  formSubmit:function(e){
    var that = this;
    var strayDog = e.detail.value;
    if (!this.WxValidate.checkForm(strayDog)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    };
    console.log(e);
    strayDog.dealTime = that.data.dealTime;
    strayDog.no = that.getStratDayNo();
    var url = config.url.BASE_URL + '/strayDog/save';
    var token = app.globalData.token;
    http.httpGet(url, token, strayDog, function (res) {
      if (res.status == 200) {
        var dogOwnerId = res.data;
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        var strayDogId = res.data;
        that.uploadImage(strayDogId, imageType.strayDogImage);
        setTimeout(function () {
          wx.navigateBack(); 
        }, 1500)
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getNowDate();
    this.getDogBreed();
    this.initValidate();
    this.getStratDayNo();
  },


  dataChangeEvent(e) {
    console.log(e);
    var that = this;
    var date = e.detail.val;
    that.setData({
      dealTime: date
    })
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
  bindBreedChange: function (e) {
    var that = this;
    that.setData({
      ['breed.breedIndex']: e.detail.value
    })
  },

  bindAgeChange: function (e) {
    var that = this;
    that.setData({
      ['age.ageIndex']: e.detail.value
    })
  },

  bindSexChange: function (e) {
    var that = this;
    that.setData({
      ['sex.sexIndex']: e.detail.value
    })
  },

  //获得种类列表()
  getDogBreed() {
    var that = this;
    app.getDogBreed(function (res) {
      that.setData({
        ["breed.breedArray"]: res.data
      })
    })
  },

  /**
  * 监听date改变
  */
  dateChangeEvent: function (e) {
    var that = this;
    console.log(e)
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

  uploadImage(id, type) {
    var that = this;
    //上传照片(待定)
    that.selectComponent("#image-upload").uploadImage(id, type);
  },

  //验证函数
  initValidate: function () {
    // 验证字段的规则
    var rules = {
      processMode:{
        required: true,
      }

    };
    var messages = {
      processMode: {
        required: "请填写处理方式",
      }

    };
    this.WxValidate = new WxValidate(rules, messages)
  },


})