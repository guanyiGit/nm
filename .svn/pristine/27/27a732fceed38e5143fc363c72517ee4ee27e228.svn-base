var dateFormat = require("../../apis/dateFormat.js")
var config = require("../../apis/config.js")
var http = require("../../apis/request.js")
var httpSync = require("../../apis/request_sync.js")

var app = getApp();
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    //判断是否是物资管理
    isMaterial: {
      type: Boolean,
      value: false
    },
    //是否是检测管理
    isDetection: {
      type: Boolean,
      value: false
    },
    //搜索框是否显示
    searchIsShow:{
      type: Boolean,
      value: true
    },
    
    placeholderText: {
      type: String, // 类型（必填），目前接受的类型包括：String, Number, Boolean, Object, Array, null（表示任意类型）
      value: '', // 属性初始值（可选），如果未指定则会根据类型选择一个
    },
  },

  /**
   * 组件的初始数据
   */
  data: {
    org: {
      isChoose: false,
      orgIndex: 0,
      orgArray: []
    },
    isShow:false
  },

  /**
   * 组件的方法列表
   */
  methods: {
    
    searchMaterial(e) {
      var that = this;
      var val = e.detail.val; //通过这个传递数据
      var searchVal = {
        val: val
      }// detail对象，提供给事件监听函数
      that.triggerEvent("searchMaterial", searchVal)
    },

    /**是否是县级管理员 */
    isCountyAdmin() {
      var isCountyAdmin = app.isCountyAdmin();
      this.setData({
        isCountyAdmin: isCountyAdmin
      })
    },

    /**是否是乡级管理员 */
    isTownAdmin() {
      var isTownAdmin = app.isTownAdmin();
      this.setData({
        isTownAdmin: isTownAdmin
      })
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

    checkDate: function () {
      var that = this;
      that.setData({
        dateshow: !that.data.dateshow,
        isfull: !that.data.isfull,
      })
    },

    hidebg: function (e) {
      this.setData({
        dateshow: false,
        isfull: false,
      })
    },

    confirm() {
      var that = this;
      var startDate = that.data.startDate;
      var endDate = that.data.endDate;
      var dateString = "";
      if (!startDate) {
        wx.showModal({
          title: wx.T.get("tishi"),
          // content: '请选择开始时间',
          content: 'qingxuanzekaishishijian',
          showCancel: false,
        })
        return;
      } else {
        dateString += startDate
      }
      if (!endDate) {
        dateString += "   -   "+wx.T.get("zhijin")
      } else {
        dateString += "   -   " + endDate
      }
      that.setData({
        ["date.isChoose"]: true,
        ["date.dateString"]: dateString,
        dateshow: false,
        isfull: false,
      })
      var eventDetail = {
        val: {
          startDate: startDate,
          endDate: endDate
        }
      }
      this.triggerEvent("dateEvent", eventDetail);
    },


    //单位下拉
    changeEvent(e) {
      var that = this;
      var index = e.detail.value;
      that.setData({
        ['org.orgIndex']: index,
        ['org.isChoose']: true,
      });
      var eventDetail = {
        val: that.data.org.orgArray[index]
      }
      that.triggerEvent("orgEvent", eventDetail);
    },
    
    //加载检测组织下拉
    initDetectionOrgList() {
      var that = this;
      var url = config.url.BASE_URL + "/manureAntigen/findOrgList"
      var token = app.globalData.token;
      var params = {};
      http.httpGet(url, token, params, function(res) {
        console.log(res);
        var orgArray = [{
          // departName: "不限"
          departName: wx.T.get("buxian")
        }]
        for (var i in res) {
          orgArray.push(res[i])
        };
        that.setData({
          ['org.orgArray']: orgArray
        })
      })
    },

    //加载物资组织下拉
    initMaterialOrgList(){
      var that = this;
      var url = config.url.BASE_URL + "/goodsInfo/initOrgSelectWx"
      var token = app.globalData.token;
      var params = {};
      http.httpGet(url,token,params,function(res){
        var orgArray = [{
          departName: wx.T.get("buxian")
        }]
        for (var i in res) {
          orgArray.push(res[i])
        };
        that.setData({
          ['org.orgArray']: orgArray
        })
      })
    },

    initProtectorList() {
      var that = this;
      var url = config.url.BASE_URL + "/protector/initProtectorSel2"
      var token = app.globalData.token;
      var params = {};
      http.httpGet(url, token, params, function(res) {
        console.log(res);
        that.setData({
          ['protector.protectorArray']: res
        })
      })
    }
  },

  //生命周期函数
  attached: function() {
    this.setData({ L: wx.T })
    var that = this;
    that.isCountyAdmin();
    that.isTownAdmin();
    console.log("properties",that.properties);
    if (that.properties.isDetection){
      if (!that.data.isCountyAdmin){
        that.initDetectionOrgList()
      }
    }else{
      that.initMaterialOrgList();
    }
  }
})