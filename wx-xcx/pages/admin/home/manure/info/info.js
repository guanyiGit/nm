var image = require("../../../../../apis/image.js")
var config = require("../../../../../apis/config.js")
var dateFormat = require("../../../../../apis/dateFormat.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")

var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    manure: {
      manureImage: "/assets/image/dog/dog.png",
      title: "新建粪便处理池进行无害化处理",
      period: "12个月",  //处理周期
      dealTime: "2018-05-21 12:45",
      processMode: "新建粪便处理池进行无害化处理",
      methodDes: "新建粪便处理池进行无害化处理新建粪便处理池进行无害化处理",
      operator: "王军",
      manureVideo: "",
      description: "xxxxxx"
    }
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
  getManureInfo: function (id) {
    var that = this;
    //var id = that.data.manure.id;
    var url = config.url.BASE_URL + "/manureDisposal/wxDetail/" + id;
    var token = app.globalData.token;
    var params = {};
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      if (res.status == 200) {
        var manure = res.data.manureDisposal;
        manure.dealTime = dateFormat(manure.dealTime, "yyyy-MM-dd HH:mm:ss")
        that.setData({
          manure: manure,
          picUrlList: res.data.picUrlList,
          videoUrlList: res.data.videoUrlList,
          protecterName: res.data.protecterName
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    console.log(options);
    var manure_index = JSON.parse(options.manure_index);
    that.getManureInfo(manure_index.id);
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