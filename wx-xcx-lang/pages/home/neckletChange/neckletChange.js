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
    component: {
      placeholderText: "溯源ID"
    },
    neckletChange: [],
    ishasData: true,
    nectletChangeList: [],
    maxLength: 6, //默认最大显示6个
    params: {
      pageNum: 1,
      pageSize: 6,
    }
  },

  scanSearch(){

  },

  search(e){
    var that = this;
    that.setData({
      nectletChangeList: [],
      params: {
        pageNum: 1,
        pageSize: 6,
      },
      ['query.string']: e.detail.val
    })
    var query = that.data.query;
    this.getList(query);
  },

  //获取列表
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/dogInfo/nectletChange/findAll";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.string != undefined) {
        params.string = query.string
      }
    }
    http.httpGet(url, token, params, function(res) {
      console.log("mmm", res);
      var data = res.data;
      var nectletChangeList;
      if (data && data.list.length > 0) {
        nectletChangeList = data.list;
        for (var index in nectletChangeList) {
          if (nectletChangeList[index].createDate != '' && nectletChangeList[index].createDate != null) {
            nectletChangeList[index].createDate = dateFormat(nectletChangeList[index].createDate, "yyyy-MM-dd")
          }
        }
        that.setData({
          nectletChangeList: that.data.nectletChangeList.concat(nectletChangeList),
          lastPage: res.data.lastPage
        })
      }
      typeof fun == "function" && fun(nectletChangeList)
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("xiangquangenghuan") })
    //溯源ID
    this.data.component.placeholderText = wx.T.get("suyuan_ID");
    this.setData({
      component: this.data.component
    })

    var that = this;
    that.setData({
      nectletChangeList: [],
      maxLength: 6,
      params: {
        pageNum: 1,
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
      nectletChangeList: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6,
      },
    });
    var query = that.data.query;
    that.getList(query, function(data) {
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
    var pageNum = that.data.params.pageNum + 1;
    if (pageNum > that.data.lastPage) {
      return;
    }
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        pageNum: that.data.params.pageNum + 1,
        pageSize: 6
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
   * 跳转到项圈更换详情
   */
  toNeckletChangePage(e) {
    var index = e.currentTarget.dataset.index;
    var that = this;
    var id = that.data.nectletChangeList[index].id;
    wx.navigateTo({
      url: '/pages/home/neckletChange/info/info?id=' + id,
    })
  },

  /**
   * 跳转到犬只添加
   */
  toDogUtilPage(e) {
    console.log(e);
    var type = e.currentTarget.dataset.type;
    var sign = true;
    wx.navigateTo({
      url: '/pages/home/dogInfo/dogUtil/dogUtil?type=' + type + '&sign=' + sign,
    })
  },

})