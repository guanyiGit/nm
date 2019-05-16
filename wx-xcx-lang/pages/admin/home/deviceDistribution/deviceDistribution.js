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
      placeholderText: "接收单位",
      isShow: false,
      isMaterial:true,
      searchIsShow:false
    },

    ishasData: true,
    isLoad: false,
    deviceDistributionArray:[{
        distributorOrgId:"海晏县动物疫病预防控制中心",
        receiveOrg:"甘子河乡兽医站",
        receiver:"",
        amount:"100",
        distributeDate:"2018-05-21 12:45",
    }],
    maxLength: 6, //默认最大显示8个
    params: {
      offset: 0,
      limit: 6,
    }
  },

  /**
   * 根据组织查询
   */
  orgEvent(e) {
    var that = this;
    var orgId = e.detail.val.id;
    if (!orgId) {
      that.setData({
        deviceDistributionArray: [],
        maxLength: 6,
        params: {
          offset: 0,
          limit: 6,
        },
        ["query.orgId"]: ""
      })
    } else {
      that.setData({
        deviceDistributionArray: [],
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
      deviceDistributionArray: [],
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

  //根据接收单位搜索犬只项圈发放情况
  searchMaterial(e){
    var that = this;
    console.log(e.detail.val);
    that.setData({
      deviceDistributionArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6,
      },
      ["query.string"]: e.detail.val
    })
    var query = that.data.query;
    this.getList(query);
  },


  /**
   * 获取粪便处理列表(根据防疫员id)
   */
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/deviceDistribution/list";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
      if (query.receiver != undefined) {
        params.receiver = query.receiver
      }
      if (query.string != undefined) {
        params.string = query.string
      }
      if (query.startDate != undefined) {
        params.startDate = query.startDate
      }
      if (query.endDate != undefined) {
        params.endDate = query.endDate
      }
    }
    http.httpGet(url, token, params, function (res) {
      console.log("xxx", res);
      var deviceDistributionArray = res.rows;
      for (var index in deviceDistributionArray) {
        var device = deviceDistributionArray[index];
        device.distributeDate = dateFormat(device.distributeDate,"yyyy-MM-dd HH:mm")
        //判断是否有接收单位
        if (device.receiveOrg != '' && device.receiveOrg != null) {
          device.ishasReceiveOrg = true
        }else{
          device.ishasReceiveOrg = false
        }
      }
      that.setData({
        deviceDistributionArray: that.data.deviceDistributionArray.concat(deviceDistributionArray)
      })
      typeof fun == "function" && fun(deviceDistributionArray);
    })

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("quanzhu_xianquan") })

    var that = this;
    that.isTownAdmin();
    // if (that.data.isTownAdmin){
    //   that.setData({
    //     ['component.searchIsShow']:false
    //   })
    // }
    that.setData({
      deviceDistributionArray: [],
      maxLength: 6,
      params: {
        offset: 0,
        limit: 6
      },
      isLoad: true
    });
    var query = that.data.query;
    that.getList(query, function (deviceDistributionArray) {
      if (!deviceDistributionArray || deviceDistributionArray.length == 0) {
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
      deviceDistributionArray: [],
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
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        offset: that.data.params.offset + 6,
        limit: 6
      }
    });
    var query = that.data.query;
    that.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  toDeviceDistributionInfoPage(e){
    console.log(e);
    var index = e.currentTarget.dataset.index;
    var deviceDistribution_index = this.data.deviceDistributionArray[index];
    wx.navigateTo({
      url: '/pages/admin/home/deviceDistribution/info/info?deviceDistribution_index=' + JSON.stringify(deviceDistribution_index),
    })
  },

  toDeviceDistributionAddPage(){
    wx.navigateTo({
      url:'/pages/admin/home/deviceDistribution/add/add',
    })
  }
})