var config = require("../../../apis/config.js")
var dateFormat = require("../../../apis/dateFormat.js")
var http = require("../../../apis/request.js")
var httpSync = require("../../../apis/request_sync.js")
var icon = require("../../../apis/icon.js")
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    component: {
      placeholderText: "录入时间/yyyy-MM-dd",
      isShow:false
    },
    startDate:"开始时间",
    endDate: "结束时间",
    searchIcon: icon.search,
    ishasData:true,
    strayDogArray: [{
      dogPic: "/assets/image/dog/dog.png",
      no: "GDQ201707006214",
      breed: "藏犬",
      age: 3,
      sex: 0,
      hairColor: "白色",
      dealTime: "2018-05-21 12:45"
    }, {
      dogPic: "/assets/image/dog/dog.png",
      no: "GDQ201707006214",
      breed: "藏犬",
      age: 3,
      sex: 0,
      hairColor: "白色",
      dealTime: "2018-05-21 12:45"
    }, ],

    maxLength: 6, //默认最大显示8个
    params: {
      pageNum: 1,
      pageSize: 6,
    }
  },

  /**开始日期change事件 */
  bindStartDateChange: function (e) {
    var that = this;
    that.setData({
      ["startDate"]: e.detail.value
    });
  },

  /**结束日期change事件 */
  bindEndDateChange: function (e) {
    var that = this;
    that.setData({
      ["endDate"]: e.detail.value
    });
  },

  searchStrayDog(e) {
    var that = this;
    var query = {};
    if (that.data.startDate == "开始时间") {
      query.startDate = ""
    } else {
      query.startDate = that.data.startDate + " 00:00"
    }
    if (that.data.endDate == "结束时间") {
      query.endDate = ""
    } else {
      query.endDate = that.data.endDate + " 23:59"
    }
    that.setData({
      strayDogArray: [],
      params: {
        pageNum: 1,
        pageSize: 6,
      },
      query: query
    });
    that.getScanList(query);
  },

  //获取
  getScanList(query, fun) {
    var that = this;
    var url = config.url.BASE_URL + "/strayDog/wxfindAll";
    var params = that.data.params;
    if (query != undefined) {
      if (query.startDate != undefined) {
        params.startDate = query.startDate
      }
      if (query.endDate != undefined) {
        params.endDate = query.endDate
      }
    }
    var token = app.globalData.token;
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      if (!res.data || !res.data.list || res.data.list.length<=0){
        that.setData({
          strayDogArray: [],
          lastPage: res.data.lastPage
        })
        return;
      }
      var strayDogs = res.data.list;
      for (var index in strayDogs) {
        strayDogs[index].dealTime = dateFormat(strayDogs[index].dealTime, "yyyy-MM-dd hh:mm")
      }
      that.setData({
        strayDogArray: res.data.list,
        lastPage: res.data.lastPage
      })
    })
  },


  //获取
  getList(query,fun){
    var that = this;
    var url = config.url.BASE_URL +"/strayDog/wxfindAll";
    var params = that.data.params;
    if (query != undefined) {
      if (query.startDate != undefined) {
        params.startDate = query.startDate
      }
      if (query.endDate != undefined) {
        params.endDate = query.endDate
      }
    }
    var token = app.globalData.token;
    http.httpGet(url, token, params, function(res){
      console.log(res);
      var strayDogs = res.data.list;
      for (var index in strayDogs){
        strayDogs[index].dealTime = dateFormat(strayDogs[index].dealTime,"yyyy-MM-dd hh:mm")
      }
      that.setData({
        strayDogArray: that.data.strayDogArray.concat(res.data.list),
        lastPage: res.data.lastPage
      })
      typeof fun == "function" && fun(strayDogs); 
    })


  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.setData({
      strayDogArray: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6
      },
      isLoad:true
    });
    var query = that.data.query
    that.getList(query, function (strayDogArray) {
      if (!strayDogArray || strayDogArray.length == 0) {
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
    if(that.data.isLoad){
      that.setData({
        isLoad:false
      })
      return;
    }
    that.setData({
      strayDogArray: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6
      }
    });
    var query = that.data.query
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
    var pageNum =  that.data.params.pageNum + 1;
    if (pageNum > that.data.lastPage){
      return;
    }
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        pageNum: that.data.params.pageNum + 1,
        pageSize: 6
      }
    });
    that.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  /**
   * 跳转info页面
   */
  toStrayDogPage(e){
    console.log(e)
    var index = e.currentTarget.dataset.index;
    var strayDog_index = this.data.strayDogArray[index];
    wx.navigateTo({
      url: '/pages/home/strayDog/info/info?strayDog_index=' + JSON.stringify(strayDog_index),
    })
  },

  /**
   * 跳转到流浪犬处理界面
   */
  toStrayDogAddPage(){
      wx.navigateTo({
        url: '/pages/home/strayDog/add/add',
      })
  }
})