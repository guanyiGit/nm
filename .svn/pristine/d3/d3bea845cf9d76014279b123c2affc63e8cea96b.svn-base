var image = require("../../../../apis/image.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var config = require("../../../../apis/config.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var icon = require("../../../../apis/icon.js")
var imageType = require("../../../../apis/imageType.js")

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    strayDog: {
      strayDogImage: "/assets/image/dog/dog.png",
      no: "GDQ201707006214",
      breed: "藏犬",
      age: 3,
      sex: 0,
      hairColor: "白色",
      weight:3,
      dealTime: "2018-05-21 12:45",
      processMode:"捕杀",
      description:"xxxxx",
      operator:"张三"
    }
  },

  getInfo(id){
    var that = this;
    var url = config.url.BASE_URL + "/strayDog/wxfindOne";
    var token = app.globalData.token;
    var params = {
      Id:id
    }
    http.httpGet(url,token,params,function(res){
      console.log(res);
      var strayDog = res.data.strayDog;
      strayDog.dealTime = dateFormat(strayDog.dealTime,"yyyy-MM-dd HH:mm")
      that.setData({
        strayDog: strayDog,
        picList:res.data.picList
      })
    })
  },

  //预览图片
  previewImage(e) {
    //获取当前图片的下标
    console.log(e);
    var index = e.currentTarget.dataset.index;
    //所有图片
    var imageList = this.data.picList;
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var strayDog_index = JSON.parse(options.strayDog_index);
    console.log(strayDog_index);
    this.getInfo(strayDog_index.id);

    // this.setData({
    //   strayDog: strayDog_index
    // })
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