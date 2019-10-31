var image = require("../../../../apis/image.js")
var icon = require("../../../../apis/icon.js")
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
      placeholderText: "犬主名/犬名",
      isShow: false
    },
    showSearch: false,
    showContrail: false,
    longitude: "100.901",
    latitude: "36.856",
    markers: [],
  },

  /**
   * 获取周围犬只定位
   */
  getList() {
    var that = this;
    that.getCurrentLocation(function() {
      var lat = that.data.latitude;
      var lng = that.data.longitude;
      var url = config.url.BASE_URL + "/position/selectDogInfosByRange"
      var token = app.globalData.token;
      var params = {
        lat: lat,
        lng: lng
      };
      http.httpPost(url, token, params, (res) => {
        console.log("xxx",res);
        var markers = that.data.markers;
        if (res && res.length > 0) {
          res.forEach((item, index) => {
            console.log(item);
            markers.push({
              id: index,
              longitude: item.lng,
              latitude: item.lat,
              iconPath: icon.dog_mark_b,
              width: 30,
              height: 30,
              title: item.traceId,
              callout: {
                content: item.dogMasterName + "\t" + item.dogName + "\n" + dateFormat(item.dateTime,"yyyy-MM-dd HH:mm"),
                fontSize: 10,
                bgColor: "#49C8D5",
                borderRadius: 10,
                display: "ALWAYS",
                textAlign: "left",
                padding: 5
              }
            })
          })
          that.setData({
            markers: markers
          })
        }
      })
    })
  },


  /**
   * marker点击事件
   */
  markerClick(e) {
    var that = this;
    var markerId = e.markerId;
    var markers = that.data.markers;
    var marker = {};
    for (var index in markers) {
      if (markers[index].id == markerId) {
        marker = markers[index];
        break;
      }
    }
    wx.navigateTo({
      url: '/pages/admin/home/petInfo/petInfo?traceId=' + marker.title,
    })
  },

  /**
   * 获取操作人当前位置
   */
  getCurrentLocation(fun) {
    var that = this;
    var markers = that.data.markers;
    if (!markers) {
      markers = []
    }
    wx.getLocation({
      success: function(res) {
        console.log(res);
        markers.push({
          longitude: res.longitude,
          latitude: res.latitude,
          iconPath: icon.mylocation,
          width: 30,
          height: 30,
          callout: {
            content: "我的位置",
            fontSize: 10,
            bgColor: "#49C8D5",
            borderRadius: 10,
            display: "ALWAYS",
            textAlign: "center",
            padding: 5
          }
        })
        that.setData({
          markers: markers,
          longitude: res.longitude,
          latitude: res.latitude,
        })
        typeof fun == "function" && fun();
      },
    })
  },

  /**
   * 获取所有犬只定位
   */
  getDogLocation() {
    var that = this;
    var url = config.url.BASE_URL + "/position/selectDogInfosByRange";
    var token = app.globalData.token;
    var params = {};
    http.httpPost(url, token, params, function(res) {
      console.log("xxxx", res);
      //添加markers
      var markers = that.data.markers;
      if (!res || !res.data || res.data.length == 0) {
        wx.getLocation({
          success: function(res) {
            markers.push({
              longitude: res.longitude,
              latitude: res.latitude,
              iconPath: icon.mylocation,
              width: 30,
              height: 30,
              callout: {
                content: "我的位置",
                fontSize: 10,
                bgColor: "#49C8D5",
                borderRadius: 10,
                display: "ALWAYS",
                textAlign: "center",
                padding: 5
              }
            })
            that.setData({
              markers: markers,
              longitude: res.longitude,
              latitude: res.latitude,
            })
          },
        })
      } else {
        for (var index in res.data) {
          var dataTime = dateFormat(res.data[index].dogRefDeviceRecordMax.maxdateTime, "yyyy-MM-dd HH:mm")
          res.data[index].dogName = res.data[index].dogName ? res.data[index].dogName : '无';
          if (index == 0) {
            that.setData({
              longitude: res.data[index].dogRefDeviceRecordMax.lng,
              latitude: res.data[index].dogRefDeviceRecordMax.lat
            })
          }
          markers.push({
            id: index,
            longitude: res.data[index].dogRefDeviceRecordMax.lng,
            latitude: res.data[index].dogRefDeviceRecordMax.lat,
            iconPath: icon.dog_mark_b,
            width: 30,
            height: 30,
            title: res.data[index].dogRefDeviceRecordMax.traceId,
            callout: {
              content: res.data[index].ownerName + "\t" + res.data[index].dogName + "\n" + dataTime,
              fontSize: 10,
              bgColor: "#49C8D5",
              borderRadius: 10,
              display: "ALWAYS",
              textAlign: "left",
              padding: 5
            }
          })
        }
        that.setData({
          markers: markers,
        });
      }
    })
  },

  getDate() {
    var that = this;
    return dateFormat(new Date(), "yyyy-MM-dd");
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.getList();
    var nowDate = that.getDate();
    that.setData({
      contrailDate: nowDate
    })
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

  },

  /**
   * 定位查询
   */
  toLocationPage: function() {
    var that = this;
    that.setData({
      showSearch: !that.data.showSearch
    })
    if (that.data.showSearch && that.data.showContrail) {
      that.setData({
        showContrail: false
      })
    }
  },

  /**
   * 轨迹查询
   */
  toContrailPage: function() {
    var that = this;
    that.setData({
      showContrail: !that.data.showContrail
    })
    if (that.data.showSearch && that.data.showContrail) {
      that.setData({
        showSearch: false
      })
    }
  }

})