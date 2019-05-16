var video = require("../../../../apis/video.js")
var image = require("../../../../apis/image.js")
var formatDate = require("../../../../apis/dateFormat.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    dogCancel: {
      dogCancelImage: image.dogImage,
      deviceNo: "GDQ201707006214",
      reason: "疾病",  //死亡原因
      CanselDate: "2018-05-21 12:45",
      description: "xxxxxx"
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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