var icon = require("../../../../apis/icon.js")
var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var dateUtils = require("../../../../utils/dateUtils.js")

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
    isShow: {
      stateIsShow: true,
      countyIsShow: true,
      townIsShow: true
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
          ["antieTimes.antieTimesArray"]: [],
          ["antieTimes.length"]: 0,
          ["antieTimes.isOpen"]: false,
          ["query.protectId"]: epidemicer.id
        })
      } else {
        that.setData({
          ["antieTimes.antieTimesArray"]: [],
          ["antieTimes.length"]: 0,
          ["antieTimes.isOpen"]: false,
          ["query.protectId"]: ""
        })
      }
    } else {
      var org = e.detail.val;
      if (org) {
        that.setData({
          ["antieTimes.antieTimesArray"]: [],
          ["antieTimes.length"]: 0,
          ["antieTimes.isOpen"]: false,
          ["query.areaId"]: org.areaId
        })
      }
    }
    var query = that.data.query;
    that.getStatisticsList(query);

  },

  bindMonthEvent(e) {
    console.log(e);
    var that = this;
    var month = e.detail.val.value;
    if (month) {
      console.log(month);
      that.setData({
        ["antieTimes.antieTimesArray"]: [],
        ["antieTimes.length"]: 0,
        ["antieTimes.isOpen"]: true,
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
    var url = config.url.BASE_URL + "/antieCount/countInfo";
    var token = app.globalData.token;
    var params = {};
    if (query != undefined) {
      if (query.protectId != undefined) {
        params.protectId = query.protectId;
      }
      if (query.areaId != undefined) {
        params.areaId = query.areaId;
      }
      if (query.month != undefined) {
        params.month = query.month;
      }
    }
    http.httpGet(url, token, params, function (res) {
      
      if (JSON.stringify(res) != "{}") {
        if (res.antieTimesList && JSON.stringify(res.antieTimesList) != "{}") {
          res.antieTimesList.forEach((item,index) => {
            if (item.stateName){
              item.areaId = item.stateId
            } else if (item.countyName){
              item.areaId = item.countyId
            } else if (item.villageName) {
              item.areaId = item.villageId
            }
          })
          that.setData({
            ["antieTimes.antieTimesArray"]: res.antieTimesList,
            ["antieTimes.length"]: res.antieTimesList.length,
            ["antieTimes.isOpen"]: true,
          })
          console.log("xxx", that.data.antieTimes.antieTimesArray);
        }
      } else {
        that.setData({
          ["antieTimes.antieTimesArray"]: [],
          ["antieTimes.length"]: 0,
          ["antieTimes.isOpen"]: true,
        })
      }
    });
  },

  /**
   * type对应table类型
   * 1 = 防疫次数
   * 2 = 犬尸处理次数
   * 3 = 犬粪次数
   * 4 = 犬粪抗原检测次数
   * 5 = 犬只解剖次数
   * 6 = 牛羊抗体检测次数
   * 7 = 牛羊脏器处理次数
   */
  openEvent(e) {
    var that = this;
    var type = e.currentTarget.dataset.type;
    if (type == 1) {
      that.setData({
        ['antieTimes.isOpen']: !that.data.antieTimes.isOpen
      })
    } 
  },


  isShow() {
    var isStateAdmin = app.isStateAdmin();
    var isCountyAdmin = app.isCountyAdmin();
    var isTownAdmin = app.isTownAdmin();
    if (isStateAdmin) {
      this.setData({
        ['isShow.stateIsShow']: true,  //州
        ['isShow.countyIsShow']: true, //县
        ['isShow.townIsShow']: true,   //乡
        ['isShow.epidemicerIsShow']: false, //防疫员
        ['isShow.epidemicIsShow']: true, //防疫次数
        ['isShow.corpseIsShow']: true,  //犬尸处理次数
        ['isShow.manureIsShow']: true,  //犬粪次数
        ['isShow.manureAntigenIsShow']: true,  //犬粪抗原检测次数
        ['isShow.anatomyIsShow']: true,  //犬只解剖次数
        ['isShow.CASAntigenIsShow']: true,  //牛羊抗体检测次数
        ['isShow.CASVisceraIsShow']: true,  //牛羊脏器处理次数
      })
    }
    if (isCountyAdmin) {
      this.setData({
        ['isShow.stateIsShow']: false,  //州
        ['isShow.countyIsShow']: true, //县
        ['isShow.townIsShow']: true,   //乡
        ['isShow.epidemicerIsShow']: false, //防疫员
        ['isShow.epidemicIsShow']: true, //防疫次数
        ['isShow.corpseIsShow']: true,  //犬尸处理次数
        ['isShow.manureIsShow']: true,  //犬粪次数
        ['isShow.manureAntigenIsShow']: true,  //犬粪抗原检测次数
        ['isShow.anatomyIsShow']: true,  //犬只解剖次数
        ['isShow.CASAntigenIsShow']: true,  //牛羊抗体检测次数
        ['isShow.CASVisceraIsShow']: true,  //牛羊脏器处理次数
      })
    }
    if (isTownAdmin) {
      this.setData({
        ['isShow.stateIsShow']: false,  //州
        ['isShow.countyIsShow']: false, //县
        ['isShow.townIsShow']: true,   //乡
        ['isShow.epidemicerIsShow']: true, //防疫员
        ['isShow.epidemicIsShow']: true, //防疫次数
        ['isShow.corpseIsShow']: true,  //犬尸处理次数
        ['isShow.manureIsShow']: true,  //犬粪次数
        ['isShow.manureAntigenIsShow']: false,  //犬粪抗原检测次数
        ['isShow.anatomyIsShow']: false,  //犬只解剖次数
        ['isShow.CASAntigenIsShow']: false,  //牛羊抗体检测次数
        ['isShow.CASVisceraIsShow']: false,  //牛羊脏器处理次数
      })
    }
  },

  /**
     * 初始化默认时间
     */
  initDefaultDate() {
    var initDefaultDate = dateUtils.getPreMonth(dateFormat(new Date, "yyyy-MM-dd"));
    this.setData({
      defaultDate: initDefaultDate
    })
  },

  /**
   * type == 1 未防疫 
   * type == 2 以防疫
   */
  antiAmountEvent(e){
    console.log(e)
    var that = this;
    var isTownAdmin =   app.isTownAdmin();
    var type = e.currentTarget.dataset.type;
    var antiepidemicDate = "";
    //判断qurey是否存在
    if(that.data.query){
      //判断month是否存在
      if (that.data.query.month){
        antiepidemicDate = that.data.query.month
      }else {
        antiepidemicDate = that.data.defaultDate;
      }
    }else{
      antiepidemicDate = that.data.defaultDate;
    }
    if (isTownAdmin){
      var protectorId = e.currentTarget.dataset.protector;
      if (protectorId){
        wx.navigateTo({
          url: '/pages/admin/home/dogInfo/dogInfo?type=' + type + '&antiepidemicDate=' + antiepidemicDate + '&protectorId=' + protectorId,
        })
      }else{
        var areaId = e.currentTarget.dataset.area;
        wx.navigateTo({
          url: '/pages/admin/home/dogInfo/dogInfo?type=' + type + '&antiepidemicDate=' + antiepidemicDate + '&areaId=' + areaId,
        })
      }
    }else{
      var areaId = e.currentTarget.dataset.area; 
      wx.navigateTo({
        url: '/pages/admin/home/dogInfo/dogInfo?type=' + type + '&antiepidemicDate=' + antiepidemicDate + '&areaId=' + areaId,
      })
    }

    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getStatisticsList();
    this.isShow();
    this.initDefaultDate();

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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})