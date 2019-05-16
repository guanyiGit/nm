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
    activityInfo: {},
    picList:null
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
   * 宣传活动详情
   */
  getActivityInfo(id) {
    var that = this;
    var url = config.url.BASE_URL + "/activityInfo/detail2";
    var token = app.globalData.token;
    var params = {
      id: id
    };
    http.httpGet(url, token, params, function (res) {
      var data = res.data;
      if (data != null) {
        if (data.activityTime != '' && data.activityTime != null) {
          data.activityTime = dateFormat(data.activityTime, "yyyy-MM-dd HH:mm")
        }
        that.setData({
          activityInfo: data,
          picList: data.picUrlList
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var id = JSON.parse(options.id);
    this.getActivityInfo(id);
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