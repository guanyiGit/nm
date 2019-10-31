//app.js
var config = require("/apis/config.js")
var config = require("/apis/config.js")
var dateFormat = require("/apis/dateFormat.js")
var http = require("/apis/request.js")
var httpSync = require("/apis/request_sync.js")


App({
  onLaunch: function() {
    var that = this;
    that.checkNewVersion();
    const token = wx.getStorageSync("token");
    //将token添加到全局变量中
    if (token) {
      that.globalData.token = token;
    }
    //将用户信息放入app
    var userInfo = wx.getStorageSync("userInfo");
    if (userInfo) {
      that.globalData.userInfo = userInfo;
    }
  },

  //检测小程序是否有新版本
  checkNewVersion: function() {
    //获取全局唯一的版本更新管理器，用于管理小程序更新
    const updateManager = wx.getUpdateManager();

    //监听向微信后台请求检查更新结果事件。微信在小程序冷启动时自动检查更新，不需由开发者主动触发。
    updateManager.onCheckForUpdate(function(res) {
      // 请求完新版本信息的回调
      if (res.hasUpdate) {
        wx.showLoading({
          title: '下载中',
        })
      }
    })

    //监听小程序有版本更新事件。客户端主动触发下载（无需开发者触发），下载成功后回调
    updateManager.onUpdateReady(function() {
      wx.hideLoading();
      wx.showModal({
        title: '更新提示',
        content: '新版本已下载完毕，请点击确定更新',
        showCancel: false,
        success: (res) => {
          if (res.confirm) {
            //强制小程序重启并使用新版本。在小程序新版本下载完成后（即收到 onUpdateReady 回调）调用。
            updateManager.applyUpdate();
          }
        }
      })
    })

    // 新版本下载失败
    updateManager.onUpdateFailed(function() {
      wx.hideLoading();
      wx.showModal({
        title: '更新提示',
        content: '新版本下载失败，请检查网络并重新更新',
        showCancel: false,
        success: (res) => {
          if (res.confirm) {
            console.log("下载新版本失败")
          }
        }
      })
    })

  },

  getUserInfo: function(fun) {
    //将用户信息放入app
    var userInfo = wx.getStorageSync("userInfo");
    if (userInfo) {
      this.globalData.userInfo = userInfo;
    }
    fun(userInfo);
  },

  isTownAdmin(){
    var userType = this.globalData.userInfo.roles[0].type;
    if (userType == 3){
        this.globalData.isTownAdmin = true;
        return true;
    }else {
      return false;
    }
  },

  /**
   * 判断是否是县级管理员
   */
  isCountyAdmin() {
    var userType = this.globalData.userInfo.roles[0].type;
    if (userType == 5) {
      this.globalData.isCountyAdmin = true;
      return true;
    } else {
      return false;
    }
  },

  /**
   * 判断是否是州级管理员
   */
  isStateAdmin() {
    var userType = this.globalData.userInfo.roles[0].type;
    if (userType == 6) {
      this.globalData.isStateAdmin = true;
      return true;
    } else {
      return false;
    }
  },

  //获得犬只种类列表
  getDogBreed(fun) {
    var that = this;
    var url = config.url.BASE_URL + "/dogInfo/getDogBreed";
    var token = that.globalData.token;
    var params = {}
    http.httpGet(url, token, params, function(res) {
      return typeof fun == "function" && fun(res);
    })
  },


  globalData: {
    userInfo: {
      userId: 1,
      protector: 1,
      orgId: 1
    },
    token: null,
    isTownAdmin:false
  }
})