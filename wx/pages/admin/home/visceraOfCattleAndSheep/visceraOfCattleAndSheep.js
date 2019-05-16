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
    visceraOfCattleAndSheepArray: [],
    maxLength: 6, //默认最大显示8个
    params: {
      index: 0,
      pageSize: 6,
      orgId1: -1,
    }
  },

  /**
   * 查询牛羊脏器
   */
  orgEvent(e) {
    var that = this;
    console.log(e.detail.val);
    var orgId = e.detail.val.id;
    if (!orgId) {
      that.setData({
        visceraOfCattleAndSheepArray: [],
        maxLength: 6,
        params: {
          index: 0,
          pageSize: 6,
        },
        ["query.orgId"]: ""
      })
    } else {
      that.setData({
        visceraOfCattleAndSheepArray: [],
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
      visceraOfCattleAndSheepArray: [],
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
   * 查询牛羊脏器列表
   */
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/infectionInfo/findInfectionInfoList";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.orgId != undefined) {
        params.orgId1 = query.orgId
      }
      if (query.startDate != undefined) {
        params.startDate = query.startDate
      }
      if (query.endDate != undefined) {
        params.endDate = query.endDate
      }
    }
    http.httpGet(url, token, params, function (res) {
      var data = res.rows;
      for (var index in data) {
        if (data[index].detectionDate != '' && data[index].detectionDate != null) {
          data[index].detectionDate = dateFormat(data[index].detectionDate, "yyyy-MM-dd HH:mm")
        }
      }
      if (data.length > 0) {
        that.setData({
          visceraOfCattleAndSheepArray: that.data.visceraOfCattleAndSheepArray.concat(data),
        })
      }
      typeof fun == "function" && fun(data);
    })
  },

  /**是否是乡级管理员 */
  isCountyAdmin() {
    var role = app.globalData.userInfo.roles[0];
    if(role.type == 5){
       this.setData({
         isCountyAdmin:true
       }) 
    }else{
      this.setData({
        isCountyAdmin: false
      }) 
    }
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.isCountyAdmin();
    that.setData({
      visceraOfCattleAndSheepArray: [],
      maxLength: 6,
      params: {
        index: 0,
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
    var that = this;
    if (that.data.isLoad) {
      that.setData({
        isLoad: false
      })
      return;
    }
    that.setData({
      visceraOfCattleAndSheepArray: [],
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
        index: that.data.params.index + 6,
        pageSize: 6,
      }
    });
    that.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  toVisceraOfCattleAndSheepAddPage() {
    wx.navigateTo({
      url: '/pages/admin/home/visceraOfCattleAndSheep/add/add',
    })
  },
  tovisceraOfCattleAndSheepInfoPage(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var id = JSON.stringify(that.data.visceraOfCattleAndSheepArray[index].id);
    wx.navigateTo({
      url: '/pages/admin/home/visceraOfCattleAndSheep/info/info?id=' + id,
    })
  }

})