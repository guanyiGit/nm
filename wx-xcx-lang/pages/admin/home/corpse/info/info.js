// pages/home/corpse/info/info.js
var image = require("../../../../../apis/image.js")
var video = require("../../../../../apis/video.js")
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
    corpse: {
      corpseImage: image.corpseImage,
      deviceId: "GDQ201707006214",
      causeOfDeath: "疾病",  //死亡原因
      processingMethod: "填埋",
      methodDescription: " ",
      dealTime: "2018-05-21 12:45",
      operator: "王军",
      corpseVideo: video.video1,
      description: "xxxxxx"
    }
  },

  //预览图片
  previewImage(e) {
    //获取当前图片的下标
    console.log(e);
    var index = e.target.dataset.index;
    //所有图片
    var imageList = this.data.imageList;
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

  getInfo(id) {
    var that = this;
    var url = config.url.BASE_URL + "/corpseDisposal/wxGetCorpseDetail";
    var token = app.globalData.token;
    var params = {
      id: id
    }
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      var corpse = res.data.corpse
      if (corpse) {
        corpse.dealTime = dateFormat(corpse.dealTime, "yyyy-MM-dd HH:mm");

      }
      that.setData({
        corpse: corpse,
        imageList: res.data.imageList,
        videoList: res.data.videoList
      })
    })

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var corpse_index = JSON.parse(options.corpse_index);
    this.getInfo(corpse_index.id);
    this.setData({
      corpse: corpse_index
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
    wx.setNavigationBarTitle({ title: this.data.L.get("chakanxiangqing") })
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