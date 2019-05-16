var config = require("../../../../../apis/config.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")
var icon = require("../../../../../apis/icon.js")
var imageType = require("../../../../../apis/imageType.js")
var dateFormat = require("../../../../../apis/dateFormat.js")
var app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    anatomyInfo: {},
    picList: [],
    videoList: []
  },



  //预览图片
  previewImage(e) {
    //获取当前图片的下标
    var index = e.target.dataset.index;
    //所有图片
    var picList = this.data.picList;
    var tempFilePaths = [];
    for (var i in picList) {
      tempFilePaths.push(picList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },

  /**
   * 解剖详情
   */
  getAnatomyInfo(anatomyId) {
    var that = this;
    var url = config.url.BASE_URL + "/dogAnatomy/getDogAnatomyDetail2";
    var token = app.globalData.token;
    var params = {
      id: anatomyId
    };
    http.httpGet(url, token, params, function (res) {
      var data = res.data.detail;
      if (data != null) {
        if (data.testDate != '' && data.testDate != null) {
          data.testDate = dateFormat(data.testDate, "yyyy-MM-dd HH:mm")
        }
        that.setData({
          anatomyInfo: data,
          picList: res.data.imageList,
          videoList: res.data.videoList,
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var anatomyId = JSON.parse(options.anatomyId);
    this.getAnatomyInfo(anatomyId);
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

  }
})