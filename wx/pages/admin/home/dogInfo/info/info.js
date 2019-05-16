var config = require("../../../../../apis/config.js")
var dateFormat = require("../../../../../apis/dateFormat.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dogInfo: {}
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
    http.httpGet(url, token, params, function (res) {
      var dogInfo = res.data.dogInfo;
      dogInfo.createDate = dateFormat(dogInfo.createDate, "yyyy-MM-dd hh:mm");
      that.setData({
        dogInfo: dogInfo,
        dogPicList: res.data.dogPicList
      })
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;  
    var dogInfo_index = JSON.parse(options.dogInfo_index);
    that.getInfo(dogInfo_index.deviceNo);
  },

  //预览图片(未完整)
  previewImage(e) {
    //获取当前图片的下标
    var index = e.currentTarget.dataset.index;
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

  toDogEditPage(e){
    wx.navigateTo({
      url: '/pages/home/dogInfo/edit/edit',
    })
  }
})