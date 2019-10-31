var image = require("../../../apis/image.js")
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
    //标记是否是选择页面进入的详情
    booleanFlag: false,
    editImage: '/assets/icon/index/edit.png',
    antiepidemicLength: 2,
    antiepidemicArray: [{
        drugId: 1,
        antiepidemicDate: 1,
        antiepidemicPeriod: 1,
        deviceNo: "xxx",
        description: "xxx",
        type: 1,
        orgId: 1,
        protector: 1,
        epidemicPic: image.dog1Image,
      },
      {
        drugId: 1,
        antiepidemicDate: 1,
        antiepidemicPeriod: 1,
        deviceNo: "xxx",
        description: "xxx",
        type: 1,
        orgId: 1,
        protector: 1,
        epidemicPic: image.dog1Image,
      }
    ],
  },

  toEditPage(e) {
    var dogInfo = e.currentTarget.dataset.doginfo;
    var dogPicList = this.data.dogPicList;
    wx.navigateTo({
      url: '/pages/home/dogInfo/edit/edit?dogInfo=' + JSON.stringify(dogInfo) + '&dogPicList=' + JSON.stringify(dogPicList),
    })
  },


  isShowDogDes() {
    var that = this;
    that.setData({
      ["dogInfo.isShowDogDes"]: !that.data.dogInfo.isShowDogDes
    })
  },

  isShowDogOwnerDes() {
    var that = this;
    that.setData({
      ["dogInfo.isShowDogOwnerDes"]: !that.data.dogInfo.isShowDogOwnerDes
    })
  },

  isShowDesForManure(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var key = 'manureArray[' + index + '].isShowDes';
    that.setData({
      [key]: !that.data.manureArray[index].isShowDes
    })
  },

  isShowModeDesForManure(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var key = 'manureArray[' + index + '].isShowModeDes';
    that.setData({
      [key]: !that.data.manureArray[index].isShowModeDes
    })
  },

  /**显示防疫信息描述详情 */
  isShowMicDesForMic(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var key = 'antiepidemicArray[' + index + '].isShowDes';
    that.setData({
      [key]: !that.data.antiepidemicArray[index].isShowDes
    })
  },

  /**
   * 获取扫一扫详情信息
   */
  getInfo(traceId,deviceNo) {
    var that = this;
    var url = config.url.BASE_URL + "/dogInfo/findOne3";
    var token = app.globalData.token;
    var params = {};
    if (traceId != undefined){
      params.traceId = traceId
    }
    if (deviceNo){
      params.deviceNo = deviceNo
    }
    http.httpGet(url, token, params, function(res) {
      if (!res.data) return;
      var antiepidemicArray = res.data.antiepidemic;
      var manureArray = res.data.manureList;
      for (var index in antiepidemicArray) {
        antiepidemicArray[index].antiepidemicDate = dateFormat(antiepidemicArray[index].antiepidemicDate, "yyyy-MM-dd HH:mm")
        antiepidemicArray[index].isShowDes = true;
        if (antiepidemicArray[index].type == 0) {
          antiepidemicArray[index].type = "春防";
        } else if (antiepidemicArray[index].type == 1) {
          antiepidemicArray[index].type = "秋防";
        } else if (antiepidemicArray[index].type == 2) {
          antiepidemicArray[index].type = "月月投药";
        }
      }
      for (var index in manureArray) {
        manureArray[index].dealTime = dateFormat(manureArray[index].dealTime, "yyyy-MM-dd HH:mm");
        manureArray[index].isShowDes = true;
        manureArray[index].isShowModeDes = true;
      }
      var dogInfo = res.data.dogInfo;
      dogInfo.createDate = dateFormat(dogInfo.createDate, "yyyy-MM-dd HH:mm");
      dogInfo.birthDay = dateFormat(dogInfo.birthDay, "yyyy-MM-dd");
      dogInfo.dowcreateDate = dateFormat(dogInfo.dowcreateDate, "yyyy-MM-dd HH:mm");
      dogInfo.isShowDogDes = true;
      dogInfo.isShowDogOwnerDes = true;
      //获取防疫长度
      var antiepidemicLength = antiepidemicArray.length;
      if (antiepidemicLength >= 2) {
        antiepidemicLength = 2
      }
      //获取防疫长度
      var manureLength = manureArray.length;
      if (manureLength >= 2) {
        manureLength = 2
      }

      that.setData({
        manureArray: manureArray,
        manureLength: manureLength,

        antiepidemicArray: antiepidemicArray,
        antiepidemicLength: antiepidemicLength,

        dogInfo: dogInfo,
        dogPicList: res.data.dogPicList,
        ownerPicList: res.data.ownerPicList,
      })
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.setNavigationBarTitle({ title: wx.T.get("chakanxiangqing") })

    //获取获取溯源id
    var type = options.type;
    var traceId = options.traceId;
    var deviceNo = options.deviceNo;
    var flag = "";
    var toPage ="";
    var booleanFlag = false;
    if(type){
      booleanFlag=true;
      if (type == 1) {
        flag = "antiepidemic-add1";
        toPage = "toAntiepidemicAddPage"
      } else if (type == 2) {
        flag = "manure-add1";
        toPage = "toManureAddPage"
      } else if (type == 3) {
        flag = "corpse-add1";
        toPage = "toCorpseAddPage"
      } else if (type == 4) {
        flag = "device-change1";
        toPage = "toDeviceChangePage"
      } else if (type == 5) {
        flag = "dog-logout1";
        toPage = "toDogLogoutAddPage"
      };
    }
    this.setData({
      traceId: traceId,
      deviceNo:deviceNo,
      toPage: toPage,
      flag: flag,
      booleanFlag: booleanFlag
    })
    this.getInfo(traceId, deviceNo);
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
  

    this.getInfo(this.data.traceId);
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

  toAntiepidemicAddPage() {
    wx.navigateTo({
      url: '/pages/home/antiepidemic/add/add?traceId=' + this.data.traceId,
    })
  },

  toManureAddPage() {
    wx.navigateTo({
      url: '/pages/home/manure/add/add?traceId=' + this.data.traceId,
    })
  },

  toCorpseAddPage() {
    wx.navigateTo({
      url: '/pages/home/corpse/add/add?dogIndex=' + JSON.stringify(this.data.dogInfo),
    })
  },

  toDeviceChangePage() {
    wx.navigateTo({
      url: '/pages/home/neckletChange/add/add?dogIndex=' + JSON.stringify(this.data.dogInfo),
    })
  },

  toDogLogoutAddPage() {
    wx.navigateTo({
      url: '/pages/home/dogCancel/add/add?dogIndex=' + JSON.stringify(this.data.dogInfo),
    })
  },



  //预览图片
  previewImageDog(e) {
    //获取当前图片的下标
    var index = e.target.dataset.index;
    //所有图片
    var imageList = this.data.dogPicList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },

  //预览图片
  previewImageOwner(e) {
    //获取当前图片的下标
    var index = e.target.dataset.index;
    //所有图片
    var imageList = this.data.ownerPicList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },


  //预览图片
  previewImageAntiepidemic(e) {
    //获取当前图片的下标
    var antiepidemicindex = e.target.dataset.antiepidemicindex;
    var index = e.target.dataset.index;

    var antiepidemic = this.data.antiepidemicArray[antiepidemicindex];
    var imageList = antiepidemic.picList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },

  //预览图片
  previewImageManure(e) {
    //获取当前图片的下标
    var manureindex = e.target.dataset.manureindex;
    var index = e.target.dataset.index;
    var manure = this.data.manureArray[manureindex];
    var imageList = manure.urlList;
    var tempFilePaths = [];
    for (var i in imageList) {
      tempFilePaths.push(imageList[i].url)
    }
    wx.previewImage({
      //当前显示图片
      current: tempFilePaths[index],
      //所有图片
      urls: tempFilePaths
    })
  },
})