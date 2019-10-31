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
      placeholderText: "溯源ID/犬主姓名/手机号/身份证号",
      isShow: false
    },
    ishasData: false,
    dogCancelList: [],
    maxLength: 6, //默认最大显示8个
    params: {
      pageNum: 1,
      pageSize: 6,
    },
  },


  // 根据组织或者防疫员搜索犬尸处理
  searchCancelByBox(e) {
    console.log(e);
    var that = this;
    if (app.globalData.isTownAdmin) {
      var epidemicer = e.detail.val; //防疫员 
      if (!epidemicer) {
        that.setData({
          dogCancelList: [],
          maxLength: 6,
          params: {
            pageNum: 1,
            pageSize: 6,
          },
          ["query.operator"]: ""
        });
      } else {
        that.setData({
          dogCancelList: [],
          maxLength: 6,
          params: {
            pageNum: 1,
            pageSize: 6,
          },
          ["query.operator"]: epidemicer.userId
        });
      }
      var query = that.data.query;
      that.getList(query);
    } else {
      var org = e.detail.val;
      that.setData({
        dogCancelList: [],
        maxLength: 6,
        params: {
          pageNum: 1,
          pageSize: 6,
        },
        ["query.orgId"]: org.id
      });
      var query = that.data.query;
      that.getList(query);
    }
  },


  //获取列表
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/dogCancel/findAll";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.string != undefined) {
        params.string = query.string
      } else if (query.operator != undefined) {
        params.operator = query.operator
      } else if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
    }
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      var data = res.data;
      var dogCancelList = "";
      if (data && data.list.length > 0) {
        dogCancelList = data.list;
        for (var index in dogCancelList) {
          if (dogCancelList[index].canselDate != '' && dogCancelList[index].canselDate != null) {
            dogCancelList[index].canselDate = dateFormat(dogCancelList[index].canselDate, "yyyy-MM-dd HH:mm")
          }
        }
        that.setData({
          dogCancelList: that.data.dogCancelList.concat(dogCancelList),
          lastPage: res.data.lastPage
        })
      }
      typeof fun == "function" && fun(dogCancelList)
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("quanshi_chuli") })
    //溯源ID/项圈编号/犬主姓名/手机号/身份证号码
    this.data.component.placeholderText = wx.T.get("suyuan_ID") + "/" +
      wx.T.get("xiangquanbianhao") + "/" +
      wx.T.get("quanzhu_xingming") + "/" +
      wx.T.get("shoujihao") + "/" +
      wx.T.get("shenfengzhenghaoma");
    this.setData({
      component: this.data.component
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
    var that = this;
    that.setData({
      dogCancelList: [],
      maxLength: 6,
      params: {
        pageNum: 1,
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
   * 跳转到犬只详情
   */
  toDogCancelPage(e) {
    var index = e.currentTarget.dataset.index;
    var that = this;
    var id = that.data.dogCancelList[index].id;
    wx.navigateTo({
      url: '/pages/admin/home/logout/info/info?id=' + id,
    })
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
    this.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  toCorpseAddPage() {
    wx.navigateTo({
      url: '/pages/home/corpse/add/add',
    })
  }

})