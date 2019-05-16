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
    placeholderText: {
      type: String, // 类型（必填），目前接受的类型包括：String, Number, Boolean, Object, Array, null（表示任意类型）
      value: '', // 属性初始值（可选），如果未指定则会根据类型选择一个
    },
    isShow: {
      type: Boolean,
      value: true,
    },
  },

  /**
   * 组件的初始数据
   */
  data: {
    dateshow: false,
    isfull: false,
  },

  /**
   * 组件的方法列表
   */
  methods: {
    searchActivity(e){
      var that = this;
      var val = e.detail.val; //通过这个传递数据
      var searchVal = {
        val: val
      }// detail对象，提供给事件监听函数
      that.triggerEvent("searchEvent", searchVal)
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

    //change事件
    changeEvent(e){
      var that = this;
      var index = e.detail.value;
      that.setData({
        ['org.orgIndex']: index,
        ['org.isChoose']: true,
      })
      var eventDetail = {};
      if (index == 0){
        eventDetail = {
          val: ""
        }
      }else{
        eventDetail = {
          val: that.data.org.orgArray[index]
        }
      }
      that.triggerEvent("orgEvent", eventDetail);
    },

    initOrgList() {
      var that = this;
      var url = config.url.BASE_URL + "/goodsInfo/initOrgSelectWx"
      var token = app.globalData.token;
      var params = {};
      http.httpGet(url, token, params, function (res) {
        var orgArray = [{
          departName:"不限"
        }]
        for(var i in res){
          orgArray.push(res[i])
        };
        that.setData({
          ['org.orgArray']: orgArray
        })
      })
    },

    checkDate: function () {
      var that  = this;
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

    reset() {
      var that = this;
      that.setData({
        startDate: "",
        endDate: "",
      })
    },

    confirm() {
      var that = this;
      var startDate = that.data.startDate;
      var endDate = that.data.endDate;
      var dateString = "";
      if (!startDate) {
        wx.showModal({
          title: '提示',
          content: '请选择开始时间',
          showCancel: false,
        })
        return;
      } else {
        dateString += startDate
      }
      if (!endDate) {
        dateString += "   -   至今"
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
    }
  },

  //生命周期函数
  attached: function () {
    this.setData({ L: wx.T })
    var that = this;
    var roleType = app.globalData.userInfo.roles[0].type;
    if (roleType == 3) {
      that.setData({
        isTownAdmin: true
      })
    };
    //如果是乡级管理员，加载防疫员下拉列表
    if (!that.data.isTownAdmin) {
      that.initOrgList();
    }
  },

})
