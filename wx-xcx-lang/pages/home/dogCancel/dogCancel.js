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
      placeholderText: "溯源ID/犬主姓名/手机号/身份证号",
      isShow:false
    },
    neckletChange: [{
      oldNeckletNo: "GDQ201707006214",
      newNeckletNo: "GDQ201707006214",
      createtime: "2018-05-21 12:45",
    }, {
      oldNeckletNo: "GDQ201707006214",
      newNeckletNo: "GDQ201707006214",
      createtime: "2018-05-21 12:45",
    }],
    dogCancelList: [],
    maxLength: 6, //默认最大显示6个
    params: {
      pageNum: 1,
      pageSize: 6,
    },
    ishasData:true
  },

  /**
   * 搜索查询
   */
  searchDog(e) {
    var that = this;
    that.setData({
      dogCancelList: [],
      params: {
        pageNum: 1,
        pageSize: 6,
      },
      ["query.string"]: e.detail.val
    });
    var query = that.data.query;
    this.getList(query);
  },


  scanSearchDog(e) {
    var that = this;
    that.setData({
      dogCancelList: [],
      params: {
        pageNum: 1,
        pageSize: 6,
      },
      ["query.string"]: e.detail.val
    });
    var query = that.data.query;
    this.getList(query);
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
    wx.setNavigationBarTitle({ title: wx.T.get("quanzhizhuxiao") })
    //"溯源ID/犬主姓名/手机号/身份证号"
    this.data.component.placeholderText = wx.T.get("suyuan_ID") + "/" +
      wx.T.get("quanzhu_xingming") + "/" +
      wx.T.get("shoujihao") + "/" +
      wx.T.get("shenfengzhenghaoma");
    this.setData({
      component: this.data.component
    })
    
    var that = this;
    that.setData({
      dogCancelList: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6,
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
      dogCancelList: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6,
      },
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

  /**
  * 跳转到犬只详情
  */
  toDogCancelPage(e) {
    var index = e.currentTarget.dataset.index;
    var that = this;
    var id = that.data.dogCancelList[index].id;
    wx.navigateTo({
      url: '/pages/home/dogCancel/info/info?id=' + id,
    })
  },

  /**
   * 跳转到犬只添加
   */
  toDogUtilPage(e) {
    console.log(e);
    var type = e.currentTarget.dataset.type;
    wx.navigateTo({
      url: '/pages/home/dogInfo/dogUtil/dogUtil?type=' + type,
    })
  },
})