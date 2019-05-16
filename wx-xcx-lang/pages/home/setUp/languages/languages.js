const { $Message } = require('../../../../dist/base/index');

Page({
  data: {
    visible: false,

    languages: {
      language: '',
      langIndex:0,
      langArray:[
        {
          name: '中文',
        },
        {
          name: '藏文'
        },
        {
          name: '蒙文',
        }
      ]
    }
  },


  handleOpen() {
    this.setData({
      visible: true
    });
  },


  handleCancel() {
    this.setData({
      visible: false
    });
  },

  handleClickItem(event){
    var detail = event.detail;
    var langIndex = detail.index;
    this.setData({	
      ["languages.langIndex"]: langIndex
    });
    wx.T.setLocaleByIndex(langIndex);
    


    console.log("langIndex: " + langIndex)
  },


  setLanguage() {
    this.setData({
      ["languages.language"]: wx.T.getLanguage()
    });
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
});