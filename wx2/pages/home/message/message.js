var icon = require("../../../apis/icon.js")
var image = require("../../../apis/image.js")
var config = require("../../../apis/config.js")
var dateFormat = require("../../../apis/dateFormat.js")
var http = require("../../../apis/request.js")
var httpSync = require("../../../apis/request_sync.js")
var msgType = require("../../../apis/msgType.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    component: {
      current: "1"
    },
    messageArray: [],
    ishasData: true,
    maxLength: 6,
    params: {
      offset: 0,
      limit: 6
    }
  },

  handleChange({ detail }) {
    this.setData({
      messageArray: [],
      ['component.current']: detail.key,
      params: {
        offset: 0,
        limit: 6
      }
    });
    this.getList();
  },

  /**
   * 获取消息列表详情
   * 消息类型：1 == 未读
   *          2 == 已读
   */
  getList(fun) {
    var that = this;
    var key = that.data.component.current;
    var url = "";
    if (key == 1) {
      //获取未读消息
      url = config.url.BASE_URL + "/homePage/getUnreadMsgs";
    } else if (key == 2) {
      //获取已读消息
      url = config.url.BASE_URL + "/homePage/getReadedMsgs";
    }
    var token = app.globalData.token;
    var params = that.data.params;
    http.httpGet(url, token, params, function(res) {
      console.log(res.rows);
      if (res.rows && res.rows.length > 0) {
        for (var index in res.rows) {
          if (res.rows[index].type == msgType.fence) {
            res.rows[index].typeIcon = icon.fence;
          } else if (res.rows[index].type == msgType.elelow) {
            res.rows[index].typeIcon = icon.elelow;
          } else {
            res.rows[index].typeIcon = icon.remind;
          }
          res.rows[index].createDate = dateFormat(res.rows[index].createDate, "yyyy-MM-dd hh:ss")
          if (res.rows[index].status == 0) {
            res.rows[index].readIcon = icon.unread
          } else {
            res.rows[index].readIcon = icon.read
          }
        }
        that.setData({
          messageArray: that.data.messageArray.concat(res.rows),
        })
        typeof fun == "function" && fun(res.rows);
      }
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.setData({
      messageArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
      },
      isLoad: true
    });
    that.getList(function(data) {
      if (!data || data.length == 0) {
        that.setData({
          ishasData: false
        })
        return;
      }
    });
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
    var that = this;
    if (that.data.isLoad) {
      that.setData({
        isLoad: false
      })
      return;
    }
    that.setData({
      messageArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
      }
    });
    that.getList(function(data) {
      if (data && data.length > 0) {
        that.setData({
          ishasData: true
        })
        return;
      }
    });
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
    var that = this;
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        offset: that.data.params.offset + 6,
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
      url: '/pages/home/message/info/info?message_index=' + JSON.stringify(message_index),
    })

  }
})