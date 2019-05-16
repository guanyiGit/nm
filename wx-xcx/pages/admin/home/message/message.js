var icon = require("../../../../apis/icon.js")
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
    messageArray: [
    //   {
    //   typeIcon: icon.remind,
    //   title: "请及时对犬只进行防疫！",
    //   content: "有犬只防疫时间已超期，请及时喂药！：犬只溯源号：20186...",
    //   createDate: "06-18 12:23",
    //   isRead: true,
    //   readIcon: "",
    // }, {
    //   typeIcon: icon.remind,
    //   title: "请及时对犬只进行防疫！",
    //   content: "有犬只防疫时间已超期，请及时喂药！：犬只溯源号：20186...",
    //   createDate: "06-18 12:23",
    //   isRead: false,
    //   readIcon: "",
    // }
    ],
    ishasData: true,
    maxLength: 6,
    params: {
      offset: 0,
      limit: 6
    }
  },

  /**
   * 获取消息列表详情
   */
  getList() {
    var that = this;
    var url = config.url.BASE_URL + "/homePage/getMsgList";
    var token = app.globalData.token;
    var params = that.data.params;
    params.id = app.globalData.userInfo.userId;
    http.httpGet(url, token, params, function(res) {
      if (res&&res.length>0) {
        for (var index in res) {
          if (res[index].type == msgType.fence) {
            res[index].typeIcon = icon.fence;
          } else if (res[index].type == msgType.elelow) {
            res[index].typeIcon = icon.elelow;
          } else {
            res[index].typeIcon = icon.remind;
          }
          res[index].createDate = dateFormat(res[index].createDate, "MM-dd hh:ss")
          if (res[index].status == 0) {
            res[index].readIcon = icon.unread
          } else {
            res[index].readIcon = icon.read
          }
        }
        that.setData({
          messageArray: that.data.messageArray.concat(res),
        })
      }else{
        that.setData({
          ishasData: false
        })
        return;
      }
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

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
    this.setData({
      messageArray:[],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
      }
    });
    this.getList();
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
    this.setData({
      maxLength: this.data.maxLength * 2,
      params: {
        offset: this.data.params.offset + 6,
        limit: 6
      }
    });
    this.getList();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  /**
   * 跳转到message详情
   */
  toMessagePage(e) {
    console.log(e);
    var index = e.currentTarget.dataset.index;
    var message_index = this.data.messageArray[index];
    wx.navigateTo({
      url: '/pages/admin/home/message/info/info?message_index=' + JSON.stringify(message_index),
    })

  }
})