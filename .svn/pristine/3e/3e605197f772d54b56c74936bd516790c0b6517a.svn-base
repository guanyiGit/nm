// pages/home/ownerInfo/info/info.js
var config = require("../../../../../apis/config.js")
var dateFormat = require("../../../../../apis/dateFormat.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")

var app = getApp();


Page({

  /**
   * 页面的初始数据
   */
  data: {
    ownerInfo: {},
    dogInfos: [],
    ishasDog: true
  },

  //预览图片
  previewImage(e) {
    //获取当前图片的下标
    console.log(e);
    //所有图片
    var index = e.currentTarget.dataset.index;
    var imageList = this.data.ownerInfo.imageList;
    console.log(imageList);
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

  toDogPage(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var traceId = that.data.dogInfos[index].traceId;
    wx.navigateTo({
      url: '/pages/admin/home/petInfo/petInfo?traceId=' + traceId,
    })
  },

  getTown(lists) {
    var town = "";
    town = lists[3].name + lists[2].name + lists[1].name + lists[0].name
    return town
  },

  getDogOwnerInfo(id) {
    var that = this;
    var url = config.url.BASE_URL + "/dogOwner/wxfindDogOwnerDetail";
    var params = {
      id: id
    };
    var token = app.globalData.token;
    http.httpGet(url, token, params, function (res) {
      console.log("xxx", res);
      res.dogOwnerVO.birthDay = dateFormat(res.dogOwnerVO.birthDay, "yyyy-MM-dd")
      res.dogOwnerVO.createDate = dateFormat(res.dogOwnerVO.createDate, "yyyy-MM-dd HH:mm")
      var town = "";
      if (res.lists) {
        res.dogOwnerVO.town = that.getTown(res.lists);
      }
      that.setData({
        ownerInfo: res.dogOwnerVO
      })
      if (!res.dogList || res.dogList.length <= 0) {
        that.setData({
          ishasDog: false
        })
      } else {
        var doginfos = res.dogList
        for (var index in doginfos) {
          doginfos[index].createDate = dateFormat(doginfos[index].createDate, "yyyy-MM-dd HH:mm")
        }
        that.setData({
          dogInfos: doginfos
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("quanzhuxiangqing") })

    var that = this;
    console.log(options)
    var ownerInfo_index = JSON.parse(options.ownerInfo_index);
    that.getDogOwnerInfo(ownerInfo_index.id);
    that.setData({
      ownerInfo: ownerInfo_index
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