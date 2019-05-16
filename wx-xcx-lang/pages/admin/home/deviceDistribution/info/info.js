// pages/admin/home/deviceDistribution/info/info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    corpse: {
      deviceId: "GDQ201707006214",
      causeOfDeath: "疾病",  //死亡原因
      processingMethod: "填埋",
      methodDescription: "填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋填埋",
      dealTime: "2018-05-21 12:45",
      operator: "王军",
      description: "xxxxxx"
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("chakanxiangqing") })

    var deviceDistribution_index = JSON.parse(options.deviceDistribution_index);
    console.log(deviceDistribution_index);
    if (deviceDistribution_index.receiverName || deviceDistribution_index.receiverName==''){
      deviceDistribution_index.ishasReceiver = false
    }

    this.setData({
      deviceDistribution: deviceDistribution_index
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
    this.setData({ L: wx.T })
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