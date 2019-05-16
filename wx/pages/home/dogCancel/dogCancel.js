var image = require("../../../apis/image.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    component: {
      placeholderText: "溯源号/犬主身份证号/手机号"
    },
    dogCancelArray: [
      {
        dogCancelImage: image.dogImage,
        deviceNo: "GDQ201707006214",
        reason: "送往外省",  //死亡原因
        canselDate: "2018-05-21 12:45"
      },
      {
        dogCancelImage: image.dogImage,
        deviceNo: "GDQ201707006214",
        reason: "疾病",  //防疫药品名称
        canselDate: "2018-05-21 12:45"
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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