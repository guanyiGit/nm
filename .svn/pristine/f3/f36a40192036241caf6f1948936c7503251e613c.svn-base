var image = require("../../../../../apis/image.js")
var config = require("../../../../../apis/config.js")
var dateFormat = require("../../../../../apis/dateFormat.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    antiepidemic: {
      dogPic: "/assets/image/dog/dog.png",
      deviceNo: "GDQ201707006214",
      drugName: "比硅酮咀嚼片", //防疫药品名称
      antiepidemicDate: "2018-05-21 12:45",
      'type': 0,
      antiepidemicPeriod: "12个月",
      protector: "王军",
      dogVideo: "",
      description: "xxxxxx"
    }
  },


  //预览图片
  previewImage(e) {
    //获取当前图片的下标
    console.log(e);
    var index = e.target.dataset.index;
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

  getAntiepidemic(id, type) {
    var that = this;
    var url = config.url.BASE_URL + "/antiepidemic/edit2";
    var token = app.globalData.token;
    var params = {
      id: id
    }
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      var antiepidemic = res.data.antiepidemic;
      if (type == 0) {
        antiepidemic.type = "春防"
      } else if (type == 1) {
        antiepidemic.type = "秋防"
      } else if (type == 2) {
        antiepidemic.type = "月月投药"
      }
      antiepidemic.antiepidemicDate = dateFormat(antiepidemic.antiepidemicDate, "yyyy-MM-dd hh:mm:ss");
      that.setData({
        antiepidemic: antiepidemic,
        picList: res.data.picList,
        videoList: res.data.VideoList
      })
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    console.log(options);
    var antiepidemic_index = JSON.parse(options.antiepidemic_index);
    that.setData({
      dogName: antiepidemic_index.dogName,
      ownerName: antiepidemic_index.ownerName
    })
    that.getAntiepidemic(antiepidemic_index.id, antiepidemic_index.type);
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