// pages/home/dogInfo/edit/edit.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fence: {
      fenceIndex: 0,
      fenceArray: ["2012", "2013"],
    },
    breed: {
      breedIndex: 0,
      breedArray: ["拉布拉多", "德牧"]
    },
    sex: {
      sexIndex: 0,
      sexArray: ["公", "母"]
    },
  },

  formSubmit: function (e) {
    var that = this;
    var dogInfo = e.detail.value;
    console.log(e.detail)
    if (!this.WxValidate.checkForm(dogInfo)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/dogInfo/save';
    var token = app.globalData.token;
    http.httpGet(url, token, dogInfo, function (res) {
      if (res.status == 200) {
        var dogInfoId = res.data;
        //上传照片(待定)
        that.uploadImage(dogInfoId, imageType.dogImage);
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function () {
          wx.redirectTo({
            url: '/pages/home/dogInfo/dogInfo',
          })
        }, 1000)
      }
    })
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

  },

  bindFenceChange: function (e) {
    var that = this;
    that.setData({
      ['fence.fenceIndex']: e.detail.value
    })
  },

  bindBreedChange: function (e) {
    var that = this;
    that.setData({
      ['breed.breedIndex']: e.detail.value
    })
  },

  bindAgeChange: function (e) {
    var that = this;
    that.setData({
      ['age.ageIndex']: e.detail.value
    })
  },

  bindSexChange: function (e) {
    var that = this;
    that.setData({
      ['sex.sexIndex']: e.detail.value
    })
  },


  //报错 
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },
  //获得围栏列表(根据防疫员id)
  getFenceByProtectorId(protectorId) {
    var that = this;
    var url = config.url.BASE_URL + "/dogInfo/getFence";
    var token = app.globalData.token;
    var params = {}
    http.httpGet(url, token, params, function (res) {
      console.log(res);
      that.setData({
        ["fence.fenceArray"]: res.data
      })
    })
  },

  //获得种类列表()
  getDogBreed() {
    var that = this;
    app.getDogBreed(function (res) {
      that.setData({
        ["breed.breedArray"]: res.data
      })
    })
  },

  //验证函数
  initValidate: function () {
    // 验证字段的规则
    var rules = {
      deviceNo: {
        required: true,
      },
      dogName: {
        required: true,
      },
      age: {
        required: true,
        digits: true,
        range: [0, 50]
      },
      hairColor: {
        required: true,
      },
      weight: {
        required: true,
        number: true,
      }

    };
    var messages = {
      deviceNo: {
        required: "请填写溯源id",
      },
      dogName: {
        required: "请填写犬名",
      },
      age: {
        required: "请填写年龄",
        digits: "年龄必须为数字",
        range: "年龄必须在0~50之间"
      },
      hairColor: {
        required: "请填写毛色",
      },
      weight: {
        required: "请填写体重",
        number: "请正确填写体重",
      }
    };
    this.WxValidate = new WxValidate(rules, messages)
  },
})