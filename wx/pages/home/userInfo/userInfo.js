var config = require("../../../apis/config.js")
var dateFormat = require("../../../apis/dateFormat.js")
var http = require("../../../apis/request.js")
var httpSync = require("../../../apis/request_sync.js")

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
    var that = this;
    var userInfo = app.globalData.userInfo;
    var userId = userInfo.userId;
    var url = config.url.BASE_URL + "/protector/wxfindProtectorDetail";
    var token = app.globalData.token;
    var params = {
      userId: userId
    }
    http.httpGet(url, token, params, function(res) {
      console.log(res);
      if(res.status == 200){
        var userInfo = res.data; 
        userInfo.birthDay = dateFormat(userInfo.birthDay, "yyyy-MM-dd");
        that.findUserArea(userInfo.areaId,function(town){
          userInfo.town = town;
          that.setData({
            userInfo: userInfo
          })  
        });
      }
    })
  },


  findUserArea(areaId,fun){
    var that = this;
    var url = config.url.BASE_URL + "/dogOwner/wxfindUserArea";
    var token = app.globalData.token;
    var params = {
      areaId: areaId
    }
    http.httpGet(url,token,params,function(res){
      var town =  that.getTown(res.lists);
      typeof fun == "function" && fun(town)
    })
  },

  getTown(lists) {
    var town = "";
    town = lists[3].name + lists[2].name + lists[1].name + lists[0].name
    return town
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