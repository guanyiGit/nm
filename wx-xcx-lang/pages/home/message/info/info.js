var icon = require("../../../../apis/config.js")
var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var msgType = require("../../../../apis/msgType.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    message: {
      title: "请及时对犬只进行防疫！",
      content: "有犬只防疫时间已超期，请及时喂药！：犬只溯源号：20186...",
      createDate: "06-18 12:23",
      isRead: true,
      readIcon: "",
    }
  },

  /**
   * 改变信息状态
   */
  changeMessageStatus(id,status){
    var that = this;
    var url = config.url.BASE_URL +"/homePage/wxRead";
    var token = app.globalData.token;
    var params = {
      id:id,
      status: status
    }
    http.httpGet(url, token,params,function(res){
      if (res.code==0){
        console.log("标记为已读")
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("chakanxiangqing") })

    var that =this;
    var message_index = JSON.parse(options.message_index);
    if (message_index.status == 0){
      that.changeMessageStatus(message_index.id, message_index.status);
    }
    that.setData({
      message:message_index
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