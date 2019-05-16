//index.js
var image = require("../../../apis/image.js")
var icon = require("../../../apis/icon.js")
var adminIcon = require("../../../apis/adminIcon.js")
var config = require("../../../apis/config.js")
var _scanCode = require("../../../common/scanCode.js")
var dateFormat = require("../../../apis/dateFormat.js")
var http = require("../../../apis/request.js")
var httpSync = require("../../../apis/request_sync.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userIcon: icon.user,
    name: "张三",
    roleName: "防疫员",
    isTownshipAdmin: false,//是否是乡级管理员
    icon: {
      scanning: icon.scanning,
      //犬主犬只
      dogAndOwner: adminIcon.dogAndOwner,
      ownerIcon: adminIcon.ownerIcon,
      dogIcon: adminIcon.dogIcon,
      strayDogIcon: adminIcon.strayDogIcon,
      statisticsIcon: adminIcon.statisticsIcon,

      //防疫管理
      eqidemicManager: adminIcon.eqidemicManager,
      epidemicIcon: adminIcon.epidemicIcon,
      manureIcon: adminIcon.manureIcon,
      corpseIcon: adminIcon.corpseIcon,
      antigenIcon: adminIcon.antigenIcon,
      CattleandsheepAntigen: adminIcon.CattleandsheepAntigen,
      anatomyIcon: adminIcon.anatomyIcon,
      VisceraOfCattleAndSheep: adminIcon.VisceraOfCattleAndSheep,

      //物资管理
      materialManager: adminIcon.materialManager,
      materialIcon: adminIcon.materialIcon,
      deviceIcon: adminIcon.deviceIcon,

      //宣传培训
      propagandaManager: adminIcon.propagandaManager,
      propagandaIcon: adminIcon.propagandaIcon,
      trainIcon: adminIcon.trainIcon,

      //消息提醒
      messageIcon: adminIcon.remindIcon
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var isTownAdmin = app.isTownAdmin();
    that.setData({
      isTownshipAdmin: isTownAdmin
    })
    app.getUserInfo(function (userInfo) {
      console.log(userInfo);
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
      that.getUnreadMsgs();
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
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      if (res.total > 0){
        that.setData({
          ['icon.messageIcon']: adminIcon.remindActiveIcon
        })
      }
      
    })
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

  },

  /**
   * 跳转到个人信息设置页
   */
  toUserInfoPage(){
    wx.navigateTo({
      url: '/pages/admin/home/setUp/setUp',
    })
  },

  /**
   * 跳转到消息提醒页面
   */
  toMessagePage(){
    wx.navigateTo({
      url: '/pages/admin/home/message/message',
    })
  }
})