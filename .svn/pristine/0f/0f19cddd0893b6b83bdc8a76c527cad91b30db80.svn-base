var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },


  //预览图片
  previewImage(e) {
    //获取当前图片的下标
    console.log(e);
    //所有图片
    var imageList = this.data.userInfo.imageList;
    var tempFilePaths = [];
    for (var index in imageList) {
      tempFilePaths.push(imageList[index].url)
    }
    wx.previewImage({
      //当前显示图片
      current: e.target.dataset.imageurl,
      //所有图片
      urls: tempFilePaths
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("gerenxinxi") })

    //获取userInfo信息
    var userInfo = app.globalData.userInfo;
    console.log(userInfo);
    this.setData({
      userInfo: userInfo
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

  }
})