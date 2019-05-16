// pages/admin/home/material/info/info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    proShow:false,
    orgShow:false,
    material:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({ title: wx.T.get("chakanxiangqing") })

    var that = this;
    var material_index = JSON.parse(options.material_index);
    console.log(material_index);
    that.setData({
      material: material_index,
    });
    if (material_index.receiverName!=null){
      that.setData({
        proShow: true,
      });
    };
    if (material_index.orgName != null) {
      that.setData({
        orgShow: true,
      });
    }
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