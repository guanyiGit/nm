var icon = require("../../../../apis/icon.js")
var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var dateUtils = require("../../../../utils/dateUtils.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    ishasData: true,
    component: {
      isMaterialStatistics: true
    }
  },


  /**
   * 初始化默认时间
   */
  getDefaultDate() {
    var initDefaultDate = dateUtils.getPreMonthOfChinese(dateFormat(new Date, "yyyy-MM-dd"));
    this.setData({
      defaultDate: initDefaultDate
    })
  },

  bindDataEvent(e) {
    console.log(e);
    var that = this;
    var isTownAdmin = app.isTownAdmin();
    if (isTownAdmin) {
      debugger
      var epidemicer = e.detail.val;
      if (epidemicer) {
        that.setData({
          ["statistics.statisticsArray"]: [],
          ["query.protectId"]: epidemicer.id
        })
      } else {
        that.setData({
          ["statistics.statisticsArray"]: [],
          ["query.protectId"]: ""
        })
      }
    } else {
      var org = e.detail.val;
      console.log(org)
      if (org) {
        that.setData({
          ["statistics.statisticsArray"]: [],
          ["query.orgId"]: org.id
        })
      }
    }
    var query = that.data.query;
    that.getStatisticsList(query);

  },

  bindMonthEvent(e) {
    console.log(e);
    var that = this;
    var month = e.detail.val.value;
    console.log(month);
    if (month) {
      that.setData({
        ["statistics.statisticsArray"]: [],
        ["query.month"]: month
      })
    }
    var query = that.data.query;
    that.getStatisticsList(query);
  },

  /**
   * 获取数量统计
   */
  getStatisticsList(query) {
    var that = this;
    var url = config.url.BASE_URL + "/goodsCount/countInfo";
    var token = app.globalData.token;
    var params = {};
    if (query != undefined) {
      if (query.protectId != undefined) {
        params.protectId = query.protectId;
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId;
      }
      if (query.month != undefined) {
        params.month = query.month;
      }
    }
    http.httpGet(url, token, params, function(res) {
      console.log("ssss", res);
      if (JSON.stringify(res) != "{}") {
        if (res.list && res.list.length > 0) {
           that.setData({
             ['statistics.statisticsArray']: res.list,
             currentYear: res.currentYear,
             currentMonth: res.currentMonth
           }) 
        }
      }
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getDefaultDate();
    this.getStatisticsList();
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