var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    component: {
      placeholderText: "主题",
      isShow:false
    },
    isChoose:false,
    ishasData: true,
    isLoad: false,
    activityArray: [],
    maxLength: 6, //默认最大显示8个
    params: {
      offset: 0,
      limit: 6,
      activityType: 0 
    }
  },

  dateEvent(e){
    var that = this;
   
    var startDate = e.detail.val.startDate;
    var endDate = e.detail.val.endDate;
    that.setData({
      activityArray: [],
      params: {
        offset: 0,
        limit: 6,
        activityType: 0
      },
      ["query.startDate"]: startDate,
      ["query.endDate"]: endDate,
    });
    var query = that.data.query;
    this.getList(query);
  },

  orgEvent(e){
    var that = this;
    var org = e.detail.val;
    var orgId = "";
    if (org){
      orgId = org.id
    };
    that.setData({
      activityArray: [],
      params: {
        offset: 0,
        limit: 6,
        activityType: 0
      },
      ["query.orgId"]: orgId
    });
    var query = that.data.query;
    this.getList(query);
  },

  /**
   * 查询宣传活动
   */
  searchActivity(e) {
    var that = this;
    that.setData({
      activityArray: [],
      params: {
        offset: 0,
        limit: 6,
        activityType: 0 
      },
      ["query.activitySubject"]: e.detail.val
    });
    var query = that.data.query;
    this.getList(query);
  },


  /**
   * 查询宣传活动列表
   */
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/activityInfo/list";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.activitySubject != undefined){
        params.activitySubject = query.activitySubject
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
      if (query.startDate != undefined) {
        params.startDate = query.startDate
      }
      if (query.endDate != undefined) {
        params.endDate = query.endDate
      }
    }
    http.httpGet(url, token, params, function (res) {
      console.log(res)
      var data = res.rows;
      for (var index in data) {
        if (data[index].activityTime != '' && data[index].activityTime != null) {
          data[index].activityTime = dateFormat(data[index].activityTime, "yyyy-MM-dd HH:mm")
        }
      }
      if (data.length > 0) {
        that.setData({
          activityArray: that.data.activityArray.concat(data),
        })
      }
      typeof fun == "function" && fun(data);
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("xuanchuan_huodong") })
    //宣传活动
    this.data.component.placeholderText = wx.T.get("xuanchuan_huodong");
    this.setData({
      component: this.data.component
    })

    var that = this;
    that.setData({
      activityArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6,
        activityType: 0 
      },
      isLoad: true
    });
    var query = that.data.query;
    that.getList(query, function (data) {
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
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({ L: wx.T })
    var that = this;
    if (that.data.isLoad) {
      that.setData({
        isLoad: false
      })
      return;
    }
    that.setData({
      activityArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6,
        activityType: 0
      }
    });
    var query = that.data.query;
    that.getList(query, function (data) {
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
    var that = this;
    var query = that.data.query;
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        offset: that.data.params.offset + 6,
        limit: 6,
        activityType: 0 
      }
    });
    that.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  toActivityAddPage() {
    wx.navigateTo({
      url: '/pages/admin/home/publicityActivity/add/add',
    })
  },
  toActivityInfoPage(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var id = JSON.stringify(that.data.activityArray[index].id);
    wx.navigateTo({
      url: '/pages/admin/home/publicityActivity/info/info?id=' + id,
    })
  }

})