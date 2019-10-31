// pages/home/ownerInfo/add/add.js
var image = require("../../../../apis/image.js")
var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")
var imageType = require("../../../../apis/imageType.js")
import WxValidate from '../../../../utils/WxValidate.js'

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ownerPic: "/assets/image/owner.png",
    ownerId: "6326231000010001",
    name: "东宝",
    nation: "藏族",
    createData: "2018-05-21 12:45",
    area: "果洛州甘德县上贡麻乡旺日呼村",
    no: "",
    grassLandArea: 320,
    cardType: 0,
    cardNum: "430987198809216576",
    phoneNum: "13800138000",
    address: "果洛州甘德县上贡麻乡旺日呼村",
    pastoralCommittee: {
      pastoralCommitteeIndex: 0,
      pastoralCommitteeArray: ["上贡麻乡旺日呼牧委会", "xxxxx牧委会"]
    },
    sex: {
      sexIndex: 0,
      sexArray: ["男", "女"]
    },
    birthday: {
      date:"1988-09-21" 
    },
    nation:{
      nationIndex: 0,
      nationArray: ["汉族", "藏族"]
    },
    degreeOfEducation:{
      degreeOfEducationIndex:0,
      degreeOfEducationArray: ["小学", "初中", "高中/中专","大专","本科及以上"]
    },
    cardType:{
      cardTypeIndex:0,
      cardTypeArray:["居民身份证"]
    }
  },

  formSubmit: function (e) {
    var that = this;
    console.log(e);
    var dogOwner = e.detail.value;
    if (!this.WxValidate.checkForm(dogOwner)) {
      const error = this.WxValidate.errorList[0];
      this.showModal(error);
      return false;
    }
    var url = config.url.BASE_URL +"/dogOwner/addDogOwner";
    var token = app.globalData.token;
    http.httpGet(url, token, dogOwner, function(res){
      console.log("ssss",res);
      if (res.code == 0) {
        if (res.id == -2){
          wx.showModal({
            title: '失败',
            content: '用户电话或证书号码重复，请重新输入',
            showCancel:false,
          })
          return;
        }
        var dogOwnerId = res.id;
        //上传照片(待定)
        that.uploadImage(dogOwnerId, imageType.dogOwnerImage);
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1500
        });
        setTimeout(function(){
          wx.navigateBack()
        }, 1500)
      }else{
        wx.showModal({
          title: '失败',
          content: '操作失败，请稍后再试！！',
          showCancel: false,
          success: function (res) {
            if (res.confirm) {
              wx.navigateBack();
            }
            return;
          }
        })
      };
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getDate();
    this.getpastoralCommittee();
    this.getTotalNation();
    this.initValidate();
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

  },

  uploadImage(id, type) {
    var that = this;
    //上传照片(待定)
    that.selectComponent("#image-upload").uploadImage(id, type);
  },

  /**获取当前时间 */
  getDate: function () {
    var nowData = dateFormat(new Date(), "yyyy-MM-dd");
    return nowData;
  },




  /**村委会change事件 */
  bindSexChange: function (e) {
    var that = this;
    that.setData({
      ["sex.sexIndex"]: e.detail.value
    });
    //生成有机编号(待定)

  },

  /**性别change事件 */
  bindPastoralCommitteeChange: function (e) {
    var that = this;
    that.setData({
      ["pastoralCommittee.pastoralCommitteeIndex"]: e.detail.value
    });
  },

  /**出生日期change事件 */
  bindDataChange: function (e) {
    var that = this;
    that.setData({
      ["birthday.date"]: e.detail.value
    });
  },

  /**民族change事件 */
  bindnationChange: function (e) {
    var that = this;
    that.setData({
      ["nation.nationIndex"]: e.detail.value
    });
  },

  /**文化程度change事件 */
  binddegreeOfEducationChange: function (e) {
    var that = this;
    that.setData({
      ["degreeOfEducation.degreeOfEducationIndex"]: e.detail.value
    });
  },

  /**证件类型change事件 */
  bindcardTypeChange: function (e) {
    var that = this;
    that.setData({
      ["cardType.cardTypeIndex"]: e.detail.value
    });
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
      no: {
        required: true,
      },
      name: {
        required: true,
      },
      phoneNum: {
        required: true,
        tel: true,
      },
      cardNum: {
        required: true,
        idcard: true,
      }

    };
    var messages = {
      no: {
        required: "请填写门牌号",
      },
      name: {
        required: "请填写犬主姓名",
      },
      phoneNum: {
        required: "请填写电话号码",
        tel: "请输入正确的电话号码",
      },
      cardNum:{
        required: "请填写证件号码",
        idcard: "请输入正确的证件号码",
      }
    };
    this.WxValidate = new WxValidate(rules, messages)
  },

  /**
   * 获取牧委会列表
   */
  getpastoralCommittee(areaId){
    var that = this; 
    var url = config.url.BASE_URL + "/pastoralCommittee/findPastoralByAreaId";
    var token = app.globalData.token;
    var params = {}
    http.httpGet(url, token, params,function(res){
        that.setData({
          ["pastoralCommittee.pastoralCommitteeArray"]:res
        })
    })
  },

  /**
   * 获取名族列表
   */
  getTotalNation(){
    var that = this;
    var url = config.url.BASE_URL + "/protector/findTotalNation";
    var token = app.globalData.token;
    var params = { };
    http.httpGet(url,token,params,function(res){
        that.setData({
          ["nation.nationArray"]: res
        })
    })

  }

})