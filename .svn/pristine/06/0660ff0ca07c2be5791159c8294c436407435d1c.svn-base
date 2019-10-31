var icon = require("../../../../apis/icon.js")
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
    date: "2018-08-22",
    dataIcon: icon.date,
    statistics: {
      xxx: "1"
    },
    ishasData: true,
    isShow:{
      stateIsShow:true,
      countyIsShow:true,
      townIsShow:true
    }
  },

  bindDataEvent(e) {
    console.log(e);
    var that = this;
    var isTownAdmin = app.isTownAdmin();
    if (isTownAdmin) {
      var epidemicer = e.detail.val;
      if (epidemicer) {
        that.setData({
          ["dog.dogArray"]: [],
          ["dog.length"]: 0,
          ["dog.isOpen"]: true,

          ["dogOwner.dogOwnerArray"]: [],
          ["dogOwner.length"]: 0,
          ["dogOwner.isOpen"]: true,

          ["strayDog.strayDogArray"]: [],
          ["strayDog.length"]: 0,
          ["strayDog.isOpen"]: true,
          ["query.protectId"]: epidemicer.id
        })
      }else{
        that.setData({
          ["dog.dogArray"]: [],
          ["dog.length"]: 0,
          ["dog.isOpen"]: true,

          ["dogOwner.dogOwnerArray"]: [],
          ["dogOwner.length"]: 0,
          ["dogOwner.isOpen"]: true,

          ["strayDog.strayDogArray"]: [],
          ["strayDog.length"]: 0,
          ["strayDog.isOpen"]: true,
          ["query.protectId"]: ""
        })
      }
    } else{
      var org = e.detail.val;
      if (org) {
        that.setData({
          ["dog.dogArray"]: [],
          ["dog.length"]: 0,
          ["dog.isOpen"]: true,

          ["dogOwner.dogOwnerArray"]: [],
          ["dogOwner.length"]: 0,
          ["dogOwner.isOpen"]: true,

          ["strayDog.strayDogArray"]: [],
          ["strayDog.length"]: 0,
          ["strayDog.isOpen"]: true,

          ["query.areaId"]: org.areaId
        })
      }
    }
    var query = that.data.query;
    that.getStatisticsList(query);

  },

  initDefaultDate() {
    var initDefaultDate = dateFormat(new Date, "yyyy-MM");
    this.setData({
      defaultDate: initDefaultDate
    })
  },

  bindMonthEvent(e){
    console.log(e);
    var that = this;
    var month = e.detail.val.value;
    console.log(month);
    if (month){
      that.setData({
        ["dog.dogArray"]: [],
        ["dog.length"]: 0,
        ["dog.isOpen"]: true,

        ["dogOwner.dogOwnerArray"]: [],
        ["dogOwner.length"]: 0,
        ["dogOwner.isOpen"]: true,

        ["strayDog.strayDogArray"]: [],
        ["strayDog.length"]: 0,
        ["strayDog.isOpen"]: true,

        ["query.month"]: month
      })
    }
    var query = that.data.query;
    that.getStatisticsList(query);
  },


  /**
   * 获取数量统计
   */
  getStatisticsList(query) {
    var that = this;
    var url = config.url.BASE_URL + "/dogAndOwner/countInfo";
    var token = app.globalData.token;
    var params = {};
    if(query!=undefined){
      if (query.protectId!=undefined){
        params.protectId = query.protectId;
      }
      if (query.areaId != undefined) {
        params.areaId = query.areaId;
      }
      if (query.month != undefined) {
        params.month = query.month;
      }
    }
    http.httpGet(url,token,params,function(res){
      console.log("xxxxx",res);
      if(JSON.stringify(res) != "{}"){
        that.setData({
          ["dog.dogArray"]: res.dogList,
          ["dog.length"]: res.dogList.length,
          ["dog.isOpen"]: true,

          ["dogOwner.dogOwnerArray"]: res.dogOwnerList,
          ["dogOwner.length"]: res.dogOwnerList.length,
          ["dogOwner.isOpen"]: true,

          ["strayDog.strayDogArray"]: res.strayDogList,
          ["strayDog.length"]: res.strayDogList.length,
          ["strayDog.isOpen"]: true,
        })
      }else{
        that.setData({
          ["dog.dogArray"]: [],
          ["dog.length"]: 0,
          ["dog.isOpen"]: true,

          ["dogOwner.dogOwnerArray"]: [],
          ["dogOwner.length"]: 0,
          ["dogOwner.isOpen"]: true,

          ["strayDog.strayDogArray"]: [],
          ["strayDog.length"]: 0,
          ["strayDog.isOpen"]: true,
        })
      }
    });
  },

  /**
   * type对应table类型
   * 1 = 犬只数量
   * 2 = 犬主数量
   * 3 = 流浪犬处理数量
   */
  openEvent(e){
    var that = this;
    var type = e.currentTarget.dataset.type;
    if (type == 1){
      that.setData({
        ['dog.isOpen']: !that.data.dog.isOpen
      })
    } else if (type == 2){
      that.setData({
        ['dogOwner.isOpen']: !that.data.dogOwner.isOpen
      })
    } else if (type == 3) {
      that.setData({
        ['strayDog.isOpen']: !that.data.strayDog.isOpen
      })
    }
  },


  isShow(){
    var isStateAdmin = app.isStateAdmin();
    var isCountyAdmin = app.isCountyAdmin();
    var isTownAdmin = app.isTownAdmin();
    if (isStateAdmin){
      this.setData({
        ['isShow.stateIsShow']: true,
        ['isShow.countyIsShow']: true,
        ['isShow.townIsShow']: true,
        ['isShow.epidemicerIsShow']: false,
      })
    }
    if (isCountyAdmin){
      this.setData({
        ['isShow.stateIsShow']: false,
        ['isShow.countyIsShow']: true,
        ['isShow.townIsShow']: true,
        ['isShow.epidemicerIsShow']: false,
      })
    }
    if (isTownAdmin) {
      this.setData({
        ['isShow.stateIsShow']: false,
        ['isShow.countyIsShow']: false,
        ['isShow.townIsShow']: true,
        ['isShow.epidemicerIsShow']: true,
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.initDefaultDate();
    var query = {};
    query.month = this.data.defaultDate;
    console.log(query)
    this.getStatisticsList(query);
    this.isShow();
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})