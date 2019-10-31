var icon = require("../../../apis/icon.js")
var config = require("../../../apis/config.js")
var dateFormat = require("../../../apis/dateFormat.js")
var http = require("../../../apis/request.js")
var httpSync = require("../../../apis/request_sync.js")
var dateUtils = require("../../../utils/dateUtils.js")

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      date:"2018-08-22",
      dataIcon: icon.date,
      statistics:{
        xxx:"1"
      }
  },


  /**
   * 获取统计信息
   */
  getStatisticsInfo(searMonth){
    var that = this;
    var url = config.url.BASE_URL + "/outer/getAllNum";
    var token = app.globalData.token;
    var params = {};
    if (searMonth!=undefined){
      params.searMonth = searMonth
    };
    http.httpGet(url,token,params,function(res){
      if(res){
        that.setData({
          statistics: res
        })
      }
    })
  },

  searchEvent(){
    var that = this;
    var defaultDate = that.data.defaultDate;
    that.getStatisticsInfo(defaultDate);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("tongjixinxi") })

    this.getStatisticsInfo();
    this.getDefaultDate();
  },

  /**
   * 初始化默认时间(本月)
   */
  getDefaultDate() {
    var initDefaultDate =  dateFormat(new Date(), "yyyy-MM");
    this.setData({
      defaultDate: initDefaultDate
    })
  },


  bindDataChange(e){
    console.log(e.detail.value);
    var that = this;
    var date = e.detail.value;
    that.setData({
      defaultDate:date
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
    this.setData({ L: wx.T })
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