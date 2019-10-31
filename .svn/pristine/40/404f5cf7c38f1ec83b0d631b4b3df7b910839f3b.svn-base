var image = require("../../../apis/image.js")
var icon = require("../../../apis/icon.js")
var config = require("../../../apis/config.js")
var dateFormat = require("../../../apis/dateFormat.js")
var http = require("../../../apis/request.js")
var httpSync = require("../../../apis/request_sync.js")

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
    showContrail:false,
    longitude: "113.9037323",
    latitude: "22.5800915",
    markers: [],
  },

  /**出生日期change事件 */
  bindDataChange: function (e) {
    var that = this;
    that.setData({
      ["contrailDate"]: e.detail.value
    });
  },

  /**获取当前时间 */
  getDate: function () {
    var nowData = dateFormat(new Date(), "yyyy-MM-dd");
    return nowData;
  },

  formSubmit(e){
    console.log(e);
    var traceId = e.detail.value.traceId;
    var date = e.detail.value.date;
    this.getContrailByTraceIdAndDate(traceId, date);
  },
  
  /**
   * 根据溯源id查询犬只
   */
  searchDog(e) {
    console.log(e);
    var that = this;
    that.setData({
      showSearch: false,
    })
    var string = e.detail.val;
    var url = config.url.BASE_URL + "/position/getDogLocation" 
    var token = app.globalData.token;
    var params = {
      string: string
    };
    http.httpGet(url, token, params, function(res) {
      var markers = [];
      var dogInfos = res.data;
      if (dogInfos.length == 0){
        wx.getLocation({
          success: function (res) {
            console.log(res);
            wx.showModal({
              // title: '提示',
              title: wx.T.get("tishi"),
              // content: '无对应犬只或者犬主',
              content: wx.T.get("wuduiyinquanzhiquanzhu"),
              showCancel:false,
              confirmText: wx.T.get("quedin"),
            })
            markers.push({
              longitude: res.longitude,
              latitude: res.latitude,
              iconPath: icon.mylocation,
              width: 30,
              height: 30,
              callout: {
                // content: "我的位置",
                content: wx.T.get("wodeweizhi"),
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
        return;
      }
      var ishasLocation = false;
      for (var index in dogInfos){
        //判断犬只是否有定位数据
        if (dogInfos[index].dogRefDeviceRecordMax){
          ishasLocation = true;
          var dataTime = dateFormat(dogInfos[index].dogRefDeviceRecordMax.maxdateTime, "yyyy-MM-dd HH:mm")
          if (index == 0) {
            that.setData({
              longitude: dogInfos[index].dogRefDeviceRecordMax.lng,
              latitude: dogInfos[index].dogRefDeviceRecordMax.lat
            })
          }
          markers.push({
            id: index,
            longitude: dogInfos[index].dogRefDeviceRecordMax.lng,
            latitude: dogInfos[index].dogRefDeviceRecordMax.lat,
            iconPath: icon.dog_mark_b,
            width: 30,
            height: 30,
            title: dogInfos[index].dogRefDeviceRecordMax.deviceNo,
            callout: {
              content: dogInfos[index].ownerName + "\t" + dogInfos[index].dogName + "\n" + dataTime,
              fontSize: 10,
              bgColor: "#49C8D5",
              borderRadius: 10,
              display: "ALWAYS",
              textAlign: "left",
              padding: 5
            }
          });
        }
      }
      if (!ishasLocation){
         wx.showModal({
          //  title: '提示',
           title: wx.T.get("tishi"),
          //  content: '无犬只定位信息',
           content: wx.T.get("wuquanzhidinweixinxi"),
           showCancel:false,
           confirmText: wx.T.get("quedin"),
         })
      };
      that.setData({
        markers: markers,
      })
    })
  },

  /**
  * 获取犬只轨迹
  */
  getContrailByTraceIdAndDate(traceId, date) {
    var that = this;
    var url = config.url.BASE_URL + "/position/getContrailByTraceIdAndDate";
    var token = app.globalData.token;
    var params = {
      traceId: traceId,
      date: date
    }
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      //获取当前对象设备数据
      var deviceDatas = res.data;
      if (deviceDatas == null || deviceDatas.length <= 0) {
        wx.showToast({
          // title: '当天无数据',
          title: wx.T.get("wudangtianshuju"),
          image: '/assets/icon/message/nodata.png',
          duration: 1500,
          mask: true,
        })
        return;
      }
      //第一条数据作为起点，添加remark
      var firstData = deviceDatas[0];
      //添加markers
      var markers = [];
      //获取起点显示图标
      var iconPathStart = "/assets/icon/message/start.png";
      markers.push({
        longitude: firstData.lng,
        latitude: firstData.lat,
        iconPath: iconPathStart,
        width: 30,
        height: 40,
      });
      //将起点坐标定为中心经纬度
      that.setData({
        longitude: firstData.lng,
        latitude: firstData.lat,
      })
      //获取最后一条数据为终点，添加remark
      var lastData = deviceDatas[deviceDatas.length - 1];
      //获取起点显示图标
      var iconPathEnd = "/assets/icon/message/end.png";
      markers.push({
        longitude: lastData.lng,
        latitude: lastData.lat,
        iconPath: iconPathEnd,
        width: 30,
        height: 40,
      });
      var points = [];
      for (var i = 0; i < deviceDatas.length; i++) {
        var deviceData = deviceDatas[i];
        //添加折线顶点
        points.push({
          longitude: parseFloat(deviceData.lng),
          latitude: parseFloat(deviceData.lat)
        })
        //添加折线顶点标记
        //添加markers
        var spotIcon = "/assets/icon/message/spot.png";
        markers.push({
          longitude: parseFloat(deviceData.lng),
          latitude: parseFloat(deviceData.lat),
          iconPath: spotIcon,
          width: 20,
          height: 20,
          anchor: {
            x: 0.5,
            y: 0.5
          }
        });
      }
      that.setData({
        markers: markers
      })
      console.log(that.data.markers);
      //插入折线数据
      that.setData({
        polyline: [{
          points: points,
          color: "#4DDC26",
          width: 6
        }]
      });
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
      url: '/pages/home/petInfo/petInfo?traceId=' + marker.title,
    })
  },

  /**
   * 获取所有犬只定位
   */
  getDogLocation() {
    var that = this;
    var url = config.url.BASE_URL + "/position/getdogPosition";
    var token = app.globalData.token;
    var params = {};
    http.httpGet(url, token, params, function(res) {
      console.log("xxxx", res);
      //添加markers
      var markers = that.data.markers;
      if (!res || !res.data || res.data.length == 0) {
        wx.getLocation({
          success: function(res) {
            markers.push({
              id: index,
              longitude: res.longitude,
              latitude: res.latitude,
              iconPath: icon.mylocation,
              width: 30,
              height: 30,
              callout: {
                // content: "我的位置",
                content: wx.T.get("wodeweizhi"),
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
      }else{
        for (var index in res.data) {
          var dataTime = dateFormat(res.data[index].dogRefDeviceRecordMax.maxdateTime, "yyyy-MM-dd HH:mm")    
          res.data[index].dogName = res.data[index].dogName ? res.data[index].dogName:'无';
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

 

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("dinweiguiji") })

    var that = this;
    that.getDogLocation();
    var nowDate =   that.getDate();
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
    this.setData({ L: wx.T })
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
    if (that.data.showSearch && that.data.showContrail){
      that.setData({
        showContrail : false
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