// pages/admin/home/material/material.js
var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    component:{
      placeholderText:'物资名称',
      isShow:false,
      isMaterial:true,
    },
    materialArray: [],
    ishasData: true,
    maxLength: 6, //默认最大显示8个
    isTownAdmin:false,
    params: {
      offset: 0,
      limit: 6,
    }
  },

  /**
   * 根据组织查询
   */
  orgEvent(e){
    var that = this;
    console.log(e.detail.val);
    var orgId = e.detail.val.id;
    if (!orgId){
      that.setData({
        materialArray: [],
        maxLength: 6,
        params: {
          offset: 0,
          limit: 6,
        },
        ["query.orgId"]: ""
      })
    }else{
      that.setData({
        materialArray: [],
        maxLength: 6,
        params: {
          offset: 0,
          limit: 6,
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
      materialArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6,
      },
      ["query.startDate"]: e.detail.val.startDate,
      ["query.endDate"]: e.detail.val.endDate
    })
    var query = that.data.query;
    this.getList(query);
  },

  /**
     * 获取防护物质列表
     */
  searchMaterial(e) {
    var that = this;
    that.setData({
      materialArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6,
      },
      ['query.goodsName']: e.detail.val,
    });
    var query = that.data.query;
    this.getList(query);
  },


  /**
 * 获取防护物质列表
 */
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/goodsInfo/list";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined){
      if (query.goodsName != undefined) {
        params.goodsName = query.goodsName
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
      if (query.receiver != undefined) {
        params.receiver = query.receiver
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
        if (data[index].distributeDate != '' && data[index].distributeDate != null) {
          data[index].distributeDate = dateFormat(data[index].distributeDate, "yyyy-MM-dd HH:mm")
        }
      }
      if (data.length > 0) {
        that.setData({
          materialArray: that.data.materialArray.concat(data),
        })
      }
      typeof fun == "function" && fun(data);
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("fanghuwuzi") })
    //物资名称
    this.data.component.placeholderText = wx.T.get("wuzimingcheng");
    this.setData({
      component: this.data.component
    })



    var that = this;
    that.isTownAdmin();
    that.setData({
      materialArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
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

  isTownAdmin() {
    var that = this;
    var isTownAdmin = app.isTownAdmin();
    that.setData({
      isTownAdmin: isTownAdmin
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
    if (that.data.isLoad) {
      that.setData({
        isLoad: false
      })
      return;
    }
    that.setData({
      materialArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
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
        limit: 6
      }
    });
    that.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  toMaterialInfoPage(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var material_index = JSON.stringify(that.data.materialArray[index]);
    wx.navigateTo({
      url: '/pages/admin/home/material/info/info?material_index=' + material_index,
    })
  },

   /**
   * 跳转到添加
   */
  toMaterialAddPage() {
    wx.navigateTo({
      url: '/pages/admin/home/material/add/add',
    })
  },

})