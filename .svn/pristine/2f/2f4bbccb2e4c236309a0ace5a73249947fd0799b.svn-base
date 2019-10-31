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
      placeholderText: "溯源id",
      isDetection: true
    },
    ishasData: true,
    isLoad: false,
    antigenArray: [],
    maxLength: 6, //默认最大显示8个
    params: {
      index: 0,
      pageSize: 6,
    }
  },


  /**
   * 根据组织查询犬粪抗原
   * 
   */
  orgEvent(e) {
    var that = this;
    console.log(e.detail.val);
    var orgId = e.detail.val.id;
    if (!orgId) {
      that.setData({
        antigenArray: [],
        maxLength: 6,
        params: {
          index: 0,
          pageSize: 6,
        },
        ["query.orgId"]: ""
      })
    } else {
      that.setData({
        antigenArray: [],
        maxLength: 6,
        params: {
          index: 0,
          pageSize: 6,
        },
        ["query.orgId"]: e.detail.val.id
      })
    }
    var query = that.data.query;
    this.getList(query);
  },
  
  /**
   * 根据发放时间查询
   */
  dateEvent(e) {
    var that = this;
    console.log(e.detail.val);
    that.setData({
      antigenArray: [],
      maxLength: 6,
      params: {
        index: 0,
        pageSize: 6,
      },
      ["query.startDate"]: e.detail.val.startDate,
      ["query.endDate"]: e.detail.val.endDate
    })
    var query = that.data.query;
    this.getList(query);
  },



  /**
   * 查询犬粪抗原列表
   */
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/manureAntigen/findManureAntigenList";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined ) {
      if (query.orgId != undefined){
        params.orgId1 = query.orgId
      }
      if (query.startDate != undefined) {
        params.startDate = query.startDate
      }
      if (query.endDate != undefined) {
        params.endDate = query.endDate
      }
    }
    http.httpGet(url, token, params, function(res) {
      console.log(res)
      var data = res.rows;
      if (data.length > 0) {
        for (var index in data) {
          if (data[index].testDate != '' && data[index].testDate != null) {
            data[index].testDate = dateFormat(data[index].testDate, "yyyy-MM-dd HH:mm")
          }
        }
        that.setData({
          antigenArray: that.data.antigenArray.concat(data),
        })
      }
      typeof fun == "function" && fun(data);
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("quanfenkangyuan_jiance") })

    var that = this;
    that.setData({
      antigenArray: [],
      maxLength: 6,
      params: {
        index: 0,
        pageSize: 6,
      },
      isLoad: true
    });
    var query = that.data.query;
    that.getList(query, function(data) {
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
    this.setData({ L: wx.T })
    var that = this;
    if (that.data.isLoad) {
      that.setData({
        isLoad: false
      })
      return;
    }
    that.setData({
      antigenArray: [],
      maxLength: 6,
      params: {
        index: 0,
        pageSize: 6,
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
    var query = that.data.query;
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        index: that.data.params.index + 6,
        pageSize: 6,
      }
    });
    that.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  toAntigenAddPage() {
    wx.navigateTo({
      url: '/pages/admin/home/antigen/add/add',
    })
  },
  toAntigenInfoPage(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var antigen_index = JSON.stringify(that.data.antigenArray[index].id);
    wx.navigateTo({
      url: '/pages/admin/home/antigen/info/info?antigenId=' + antigen_index,
    })
  }

})