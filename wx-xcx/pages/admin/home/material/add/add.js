// pages/home/dogInfo/add/dog_add.js
var config = require("../../../../../apis/config.js")
var http = require("../../../../../apis/request.js")
var httpSync = require("../../../../../apis/request_sync.js")
var icon = require("../../../../../apis/icon.js")
var imageType = require("../../../../../apis/imageType.js")

import WxValidate from '../../../../../utils/WxValidate.js'

var app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
    orgShow:false,//乡级以上登录
    proShow: false,//乡级登录
    material: {
      materialIndex: 0,
      materialType: []
    },
    org: {
      orgIndex: 0,
      orgArray: [],
    },
    receiver: {
      receiverIndex: 0,
      receiverArray: []
    } 
  },

  formSubmit: function (e) {
    var that = this;
    var materialInfo = e.detail.value;
    console.log(e.detail)
    if (!this.WxValidate.checkForm(materialInfo)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    //表单提交
    var url = config.url.BASE_URL + '/goodsInfo/save';
    var token = app.globalData.token;
    http.httpPost(url, token, materialInfo, function (res) {
      console.log(res);
      if (res.code == 0) {
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function () {
          wx.navigateBack()
          wx.redirectTo({
            url: '/pages/admin/home/material/material',
          })
        }, 1500)
      }
    })
  },

  //检查deviceNo是否绑定有犬只
  checkDeviceNo(deviceNo) {
    var that = this;
    //表单提交
    var url = config.url.BASE_URL + '/goodsInfo/save';
    var token = app.globalData.token;
    var params = {
      deviceNo: deviceNo
    }
    http.httpGet(url, token, params, function (res) {
      //如果返回false,则设备未被绑定，提醒用户设备未关联犬只
      if (res.data) {
        wx.showModal({
          title: '提示',
          content: '该设备已被绑定，请更换设备',
          showCancel: false,
        })
      } else {
        that.setData({
          deviceNo: deviceNo,
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
   var roleType= app.globalData.userInfo.roles[0].type;
    if (roleType == 3 || roleType == 8){
      that.setData({
        ['proShow']: true
      })
    }else{
      that.setData({
        ['orgShow']: true
      })
      
    }
    this.initValidate();//验证规则函数
    var userInfo = app.globalData.userInfo;
    this.getMaterialType();
    this.getReceiveOrg();
    this.getReceiver();
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
  bindMaterialTypeChange: function (e) {
    var that = this;
    that.setData({
      ['material.materialIndex']: e.detail.value
    })
  },
  bindReceiveOrgChange: function (e) {
    var that = this;
    that.setData({
      ['org.orgIndex']: e.detail.value
    })
  },
  bindReceiverChange: function (e) {
    var that = this;
    that.setData({
      ['receiver.receiverIndex']: e.detail.value
    })
  },

  //获得物资类型
  getMaterialType() {
    var that = this;
    var url = config.url.BASE_URL + "/manureDisposal/initSelectData";
    var token = app.globalData.token;
    var params = {
      type: "goods_type"
    }
    http.httpGet(url, token, params, function (res) {
      that.setData({
        ["material.materialType"]: res
      })
    })
  },

  //获得接收单位
  getReceiveOrg() {
    var that = this;
    var url = config.url.BASE_URL + "/orgInfo/initOrgSelect4";
    var token = app.globalData.token;
    var params = {
    }
    http.httpGet(url, token, params, function (res) {
      that.setData({
        ["org.orgArray"]: res
      })
    })
  },

  //获得接收人
  getReceiver() {
    var that = this;
    var url = config.url.BASE_URL + "/protector/initProtectorSel2";
    var token = app.globalData.token;
    var params = {
    }
    http.httpGet(url, token, params, function (res) {
      that.setData({
        ["receiver.receiverArray"]: res
      })
    })
  },


  //报错 
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },


  //验证函数
  initValidate: function () {
    // 验证字段的规则
    var rules = {
      goodsName: {
        required: true,
      },
      goodsAmount: {
        required: true,
        digits: true,
      },
      // age: {
      //   required: true,
      //   digits: true,
      //   range: [0, 50]
      // },
    

    };
    var messages = {
      goodsName: {
        required: "请填写物资名称",
      },
      goodsAmount: {
        required: "请填写发放数量",
        digits: "数量填写不正确",
      },
      // age: {
      //   required: "请填写年龄",
      //   digits: "年龄必须为数字",
      //   range: "年龄必须在0~50之间"
      // }, 
     
    };
    this.WxValidate = new WxValidate(rules, messages)
  },
})