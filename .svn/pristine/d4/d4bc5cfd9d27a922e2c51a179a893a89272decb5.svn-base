var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")

var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    neckletChange: {}
  },

  //预览图片
  previewImage(e) {
    //获取当前图片的下标
    console.log(e);
    var index = e.currentTarget.dataset.index;
    //所有图片
    var imageList = this.data.picUrlList;
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
   * 获取粪便处理详情
   */
  getNeckletChangeInfo: function (id) {
    var that = this;
    var url = config.url.BASE_URL + "/dogInfo/wxneckletChange/findOne";
    var token = app.globalData.token;
    var params = {
      Id:id
    };
    http.httpGet(url, token, params, function (res) {
      if (res.status == 200) {
        var neckletChange = res.data;
        neckletChange.createtime = dateFormat(neckletChange.createtime, "yyyy-MM-dd HH:mm:ss")
        that.setData({
          neckletChange: neckletChange
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("chakanxiangqing") })

    var that = this;
    that.getNeckletChangeInfo(options.id);
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