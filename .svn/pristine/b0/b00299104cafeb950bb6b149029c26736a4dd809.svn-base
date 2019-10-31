//index.js
var image = require("../../apis/image.js")
var icon = require("../../apis/icon.js")
var config = require("../../apis/config.js")
var _scanCode = require("../../common/scanCode.js")
var dateFormat = require("../../apis/dateFormat.js")
var http = require("../../apis/request.js")
var httpSync = require("../../apis/request_sync.js")
var QR_code = require("../../apis/QR_code.js")

//获取应用实例
const app = getApp()

Page({
  data: {
    userIcon: icon.user,
    name: "张三",
    roleName: "防疫员",

    icon: {
      dogInfo: icon.dogInfo,
      ownerInfo: icon.ownerInfo,
      antiepidemic: icon.antiepidemic,
      feaces: icon.feaces,
      corpse: icon.corpse,
      stray: icon.stray,
      dogOwnerChange: icon.dogOwnerChange,
      dogDeviceChange: icon.dogDeviceChange,
      ownerLogOff: icon.ownerLogOff,
      statistics: icon.statistics,
      location: icon.location,
      message: icon.message,
      scanning: icon.scanning,
      setUp: icon.setUp
    },
    messageLength: 2
  },

  toSetUpPage() {
    wx.navigateTo({
      url: '/pages/home/setUp/setUp',
    })
  },

  toUserInfoPage() {
    wx.navigateTo({
      url: '/pages/home/userInfo/userInfo',
    })
  },

  //检查deviceNo是否绑定有犬只
  checkDevice(deviceNo, fun) {
    var that = this;
    //表单提交
    var url = config.url.BASE_URL + '/dogInfo/checkDeviceIsUse';
    var token = app.globalData.token;
    var params = {
      deviceNo: deviceNo
    }
    http.httpGet(url, token, params, function(res) {
      //如果返回false,则设备未被绑定，提醒用户设备未关联犬只
      if (res.code == 9002) {
        that.setData({
          deviceNo: deviceNo,
        })
        typeof fun == "function" && fun();
      } else if (res.code == 0) {
        wx.showModal({
          // title: '提示',
          title: wx.T.get("tishi"),
          // content: "该设备未绑定犬只",
          content: wx.T.get("gaishebeiweibangdinquanzhi"),
          showCancel: false,
          confirmText: wx.T.get("quedin"),
        })
      } else {
        var msg = "";
        if (res.code == 9002) {
          msg = wx.T.get("gaishebeiyibeibangdin")
        } else if (res.code == 9003) {
          msg = wx.T.get("gaishebeiyidiushi")
        } else if (res.code == 9004) {
          msg = wx.T.get("gaishebeiyisunhuai")
        }
        wx.showModal({
          // title: '提示',
          title: wx.T.get("tishi"),
          content: msg,
          showCancel: false,
          confirmText: wx.T.get("quedin"),
        })
      }
    })
  },


  //二维码扫描
  scanCode() {
    QR_code.scan_code(true, config.url.BASE_URL, app.globalData.token)
      .then(res => {
        //该设备未绑定犬只
        if (res && res.code == 0) {
          wx.showModal({
            title: '提示',
            title: wx.T.get("tishi"),
            confirmText: wx.T.get("quedin"),
            content: wx.T.get("gaishebeiweibangdinquanzhi"),
            showCancel: false,
          })
        } else if (res && res.code == '9002') {
          wx.navigateTo({
            url: '/pages/home/petInfo/petInfo?deviceNo=' + res.deviceNo,
          })
        } else {
          var msg = "";
          if (res.code == 9003) {
            msg = wx.T.get("gaishebeiyidiushi")
          } else if (res.code == 9004) {
            msg = wx.T.get("gaishebeiyisunhuai")
          }
          wx.showModal({
            // title: '提示',
            title: wx.T.get("tishi"),
            content: msg,
            showCancel: false,
            confirmText: wx.T.get("quedin"),
          })
        }
      })
  },

  /**
   * 获取所有未读消息
   */
  getUnreadMsgs() {
    var that = this;
    var url = config.url.BASE_URL + "/homePage/getUnreadMsgs";
    var token = app.globalData.token;
    var id = app.globalData.userInfo.userId
    var params = {
      offset: 0,
      limit: 6,
      id: id
    };
    http.httpGet(url, token, params, function(res) {
      that.setData({
        messageLength: res.total
      })
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.setData({
      L: wx.T
    })


    this.getUnreadMsgs();
  },

  onLoad: function() {
    wx.setNavigationBarTitle({
      title: wx.T.get("quanfangyi")
    })

    var that = this;
    app.getUserInfo(function(userInfo) {
      if (!userInfo || userInfo == "") {
        wx.redirectTo({
          url: '/pages/login/login',
        })
        return;
      }
      that.setData({
        name: userInfo.name,
        roleName: userInfo.roles[0].roleName
      })
    })


    //   var that = this;
    //   app.getUserInfo(function (userInfo) {
    //     console.log(userInfo);
    //     if (!userInfo || userInfo == "") {
    //       wx.redirectTo({
    //         url: '/pages/login/login',
    //       })
    //       return;
    //     }else{
    //       if (userInfo.roles[0].type == 2) {
    //         that.setData({
    //           name: userInfo.name,
    //           roleName: userInfo.roles[0].roleName
    //         })

    //         return;
    //       } else {
    //         wx.redirectTo({
    //           url: '/pages/admin/index/index',
    //         })
    //       }
    //     }
    //   })
  }
})