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
      
    },
    ishasData: true,
    isShow: {
      stateIsShow: true,
      countyIsShow: true,
      townIsShow: true
    }
  },

  initDefaultDate() {
    var initDefaultDate = dateFormat(new Date, "yyyy-MM");
    this.setData({
      defaultDate: initDefaultDate
    })
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

          ["corpseTimes.corpseTimesArray"]: [],
          ["corpseTimes.length"]: 0,
          ["corpseTimes.isOpen"]: false,

          ["manureTimes.manureTimesArray"]: [],
          ["manureTimes.length"]: 0,
          ["manureTimes.isOpen"]: false,


          ["query.protectId"]: epidemicer.id
        })
      }else {
        that.setData({
          ["antieTimes.antieTimesArray"]: [],
          ["antieTimes.length"]: 0,
          ["antieTimes.isOpen"]: false,

          ["corpseTimes.corpseTimesArray"]: [],
          ["corpseTimes.length"]: 0,
          ["corpseTimes.isOpen"]: false,

          ["manureTimes.manureTimesArray"]: [],
          ["manureTimes.length"]: 0,
          ["manureTimes.isOpen"]: false,


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

          ["corpseTimes.corpseTimesArray"]: [],
          ["corpseTimes.length"]: 0,
          ["corpseTimes.isOpen"]: false,

          ["manureTimes.manureTimesArray"]: [],
          ["manureTimes.length"]: 0,
          ["manureTimes.isOpen"]: false,


          ["antigenTimes.antigenTimesArray"]: [],
          ["antigenTimes.length"]: 0,
          ["antigenTimes.isOpen"]: false,

          ["anatomyTimes.anatomyTimesArray"]: [],
          ["anatomyTimes.length"]: 0,
          ["anatomyTimes.isOpen"]: false,

          ["antibodyTimes.antibodyTimesArray"]: [],
          ["antibodyTimes.length"]: 0,
          ["antibodyTimes.isOpen"]: false,

          ["infectionTimes.infectionTimesArray"]: [],
          ["infectionTimes.length"]: 0,
          ["infectionTimes.isOpen"]: false,

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

        ["corpseTimes.corpseTimesArray"]: [],
        ["corpseTimes.length"]: 0,
        ["corpseTimes.isOpen"]: true,

        ["manureTimes.manureTimesArray"]: [],
        ["manureTimes.length"]: 0,
        ["manureTimes.isOpen"]: true,

        ["antigenTimes.antigenTimesArray"]: [],
        ["antigenTimes.length"]: 0,
        ["antigenTimes.isOpen"]: true,

        ["anatomyTimes.anatomyTimesArray"]: [],
        ["anatomyTimes.length"]: 0,
        ["anatomyTimes.isOpen"]: true,
        
        ["antibodyTimes.antibodyTimesArray"]: [],
        ["antibodyTimes.length"]: 0,
        ["antibodyTimes.isOpen"]: true,

        ["infectionTimes.infectionTimesArray"]: [],
        ["infectionTimes.length"]: 0,
        ["infectionTimes.isOpen"]: true,

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
      console.log("xxx",res);
      if (JSON.stringify(res) != "{}") {
        if (res.antieTimesList && JSON.stringify(res.antieTimesList) != "{}"){
          that.setData({
            ["antieTimes.antieTimesArray"]: res.antieTimesList,
            ["antieTimes.length"]: res.antieTimesList.length,
            ["antieTimes.isOpen"]: true,
          })
        }
        if (res.corpseTimesList && JSON.stringify(res.corpseTimesList) != "{}") {
          that.setData({
            ["corpseTimes.corpseTimesArray"]: res.corpseTimesList,
            ["corpseTimes.length"]: res.corpseTimesList.length,
            ["corpseTimes.isOpen"]: true,
          })
        }
        if (res.manureTimesList && JSON.stringify(res.manureTimesList) != "{}") {
          that.setData({
            ["manureTimes.manureTimesArray"]: res.manureTimesList,
            ["manureTimes.length"]: res.manureTimesList.length,
            ["manureTimes.isOpen"]: true,
          })
        }
        if (res.antigenTimesList && JSON.stringify(res.antigenTimesList) != "{}") {
          that.setData({
            ["antigenTimes.antigenTimesArray"]: res.antigenTimesList,
            ["antigenTimes.length"]: res.antigenTimesList.length,
            ["antigenTimes.isOpen"]: true,
          })
        }
        if (res.anatomyTimesList && JSON.stringify(res.anatomyTimesList) != "{}") {
          that.setData({
            ["anatomyTimes.anatomyTimesArray"]: res.anatomyTimesList,
            ["anatomyTimes.length"]: res.anatomyTimesList.length,
            ["anatomyTimes.isOpen"]: true,
          })
        }
        if (res.antibodyTimesList && JSON.stringify(res.antibodyTimesList) != "{}") {
          that.setData({
            ["antibodyTimes.antibodyTimesArray"]: res.antibodyTimesList,
            ["antibodyTimes.length"]: res.antibodyTimesList.length,
            ["antibodyTimes.isOpen"]: true,
          })
        }
        if (res.infectionTimesList && JSON.stringify(res.infectionTimesList) != "{}") {
          that.setData({
            ["infectionTimes.infectionTimesArray"]: res.infectionTimesList,
            ["infectionTimes.length"]: res.infectionTimesList.length,
            ["infectionTimes.isOpen"]: true,
          })
        }
      }else{
        that.setData({
          ["antieTimes.antieTimesArray"]: [],
          ["antieTimes.length"]: 0,
          ["antieTimes.isOpen"]: true,

          ["corpseTimes.corpseTimesArray"]: [],
          ["corpseTimes.length"]: 0,
          ["corpseTimes.isOpen"]: true,

          ["manureTimes.manureTimesArray"]: [],
          ["manureTimes.length"]: 0,
          ["manureTimes.isOpen"]: true,

          ["antigenTimes.antigenTimesArray"]: [],
          ["antigenTimes.length"]: 0,
          ["antigenTimes.isOpen"]: true,

          ["anatomyTimes.anatomyTimesArray"]: [],
          ["anatomyTimes.length"]: 0,
          ["anatomyTimes.isOpen"]: true,

          ["antibodyTimes.antibodyTimesArray"]: [],
          ["antibodyTimes.length"]: 0,
          ["antibodyTimes.isOpen"]: true,

          ["infectionTimes.infectionTimesArray"]: [],
          ["infectionTimes.length"]: 0,
          ["infectionTimes.isOpen"]: true,
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
    } else if (type == 2) {
      that.setData({
        ['corpseTimes.isOpen']: !that.data.corpseTimes.isOpen
      })
    } else if (type == 3) {
      that.setData({
        ['manureTimes.isOpen']: !that.data.manureTimes.isOpen
      })
    } else if (type == 4) {
      that.setData({
        ['antigenTimes.isOpen']: !that.data.antigenTimes.isOpen
      })
    } else if (type == 5) {
      that.setData({
        ['anatomyTimes.isOpen']: !that.data.anatomyTimes.isOpen
      })
    } else if (type == 6) {
      that.setData({
        ['antibodyTimes.isOpen']: !that.data.antibodyTimes.isOpen
      })
    } else if (type == 7) {
      that.setData({
        ['infectionTimes.isOpen']: !that.data.infectionTimes.isOpen
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("tongjixinxi") })

    this.initDefaultDate();
    var query = {};
    query.month = this.data.defaultDate;
    this.getStatisticsList(query);
    this.isShow();

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