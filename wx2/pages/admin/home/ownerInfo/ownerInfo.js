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
      placeholderText: "姓名/电话号码",
      isShow: false
    },
    ownerInfos: [{
      ownerPic: image.ownerImage,
      ownerId: "6326231000010001",
      name: "东宝",
      sex: 1,
      nation: "藏族",
      createData: "2018-05-21 12:45"
    }],
    maxLength: 6, //默认最大显示6个
    params: {
      index: 0,
      pageSize: 6,
    },
    ishasData: true,
  },

  /**
   * 跳转到犬主详情
   */
  toOwnerPage(e) {
    var index = e.currentTarget.dataset.index;
    var that = this;
    var ownerInfo_index = JSON.stringify(that.data.ownerInfos[index]);
    wx.navigateTo({
      url: '/pages/admin/home/ownerInfo/info/info?ownerInfo_index=' + ownerInfo_index,
    })
  },

  // 根据组织或者防疫员搜索犬主
  searchOwnerByBox(e){
    console.log(e);
    var that = this;
    if (app.globalData.isTownAdmin){
      var epidemicer = e.detail.val; //防疫员 
      if (!epidemicer){
        that.setData({
          ownerInfos: [],
          maxLength: 6,
          params: {
            index: 0,
            pageSize: 6,
          },
          ["query.pro"]: ""
        }); 
      }else{
        that.setData({
          ownerInfos: [],
          maxLength: 6,
          params: {
            index: 0,
            pageSize: 6,
          },
          ["query.pro"]: epidemicer.userId
        });
      }
      var query = that.data.query;
      that.getList(query);
    }else {
      var org = e.detail.val;
      that.setData({
        ownerInfos: [],
        maxLength: 6,
        params: {
          index: 0,
          pageSize: 6,
        },
        ["query.orgId"]: org.id
      });
      var query = that.data.query;
      that.getList(query);
    }
  },

  // 搜索
  searchOwner: function(e) {
    console.log(e);
    var that = this;
    that.setData({
      ownerInfos: [],
      maxLength: 6,
      params: {
        index: 0,
        pageSize: 6,
      },
      ["query.searchKey"]: e.detail.val
    });
    var query = that.data.query;
    that.getList(query);
  },

  /**
   * 跳转到犬主添加
   */
  toOwnerAddPage() {
    wx.navigateTo({
      url: '/pages/home/ownerInfo/add/add',
    })
  },

  /**
   * 获取犬主列表
   */
  getList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/dogOwner/findDogOwnerList";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.searchKey != undefined) {
        params.searchKey = query.searchKey
      }
      if (query.pro != undefined) {
        params.pro = query.pro
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
    }
    http.httpGet(url, token, params, function(res) {
      var ownerList = res.rows;
      console.log(ownerList);
      if (ownerList.length > 0) {
        for (var index in ownerList) {
          if (ownerList[index].birthDay != '' && ownerList[index].birthDay != null) {
            ownerList[index].birthDay = dateFormat(ownerList[index].birthDay, "yyyy-MM-dd")
          }
          if (ownerList[index].createDate != '' && ownerList[index].createDate != null) {
            ownerList[index].createDate = dateFormat(ownerList[index].createDate, "yyyy-MM-dd hh:mm")
          }
        }
        that.setData({
          ownerInfos: that.data.ownerInfos.concat(ownerList),
        })
      }
      typeof fun == "function" && fun(ownerList);
    })

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.setData({
      ownerInfos: [],
      maxLength: 6,
      params: {
        index: 0,
        pageSize: 6
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
      ownerInfos: [],
      maxLength: 6,
      params: {
        index: 0,
        pageSize: 6
      }
    });
    var query = that.data.query;
    that.getList(query, function(ownerList) {
      if (ownerList && ownerList.length > 0) {
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
    this.setData({
      maxLength: this.data.maxLength * 2,
      params: {
        index: this.data.params.index + 6,
        pageSize: 6
      }
    });
    this.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})