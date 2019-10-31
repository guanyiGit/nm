// pages/admin/test/test.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    components: {
      isTownAdmin: false,
      isMaterial: true,
      isDetection: true,
      placeholderText: "接收单位"
    }

  },

  getdata(e) {
    console.log(e);
  },

  searchMaterial(e) {
    console.log(e);
  },

  orgEvent(e) {
    console.log(e);
  },

  dateEvent(e) {
    console.log(e);
  },

  chooseImage() {
    wx.chooseImage({
      success: function(res) {
        console.log(res);
        var url = res.tempFiles[0].path;
        wx.getFileInfo({
          filePath: url,
          success: function(res) {
            console.log(res);
          }
        })

      },
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

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

  }
})