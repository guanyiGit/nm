var config = require("../../../../apis/config.js")
var dateFormat = require("../../../../apis/dateFormat.js")
var http = require("../../../../apis/request.js")
var httpSync = require("../../../../apis/request_sync.js")

var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    ishasData: true,
    isLoad:false,
    component: {
      placeholderText: "溯源ID/项圈编号/犬主姓名/手机号/身份证号",
      isShow: false
    },
    searchDog: '',
    dogInfos: [],
    maxLength: 6, //默认最大显示8个
    params: {
      pageNum: 1,
      pageSize: 6,
    }
  },

  // 根据组织或者防疫员搜索犬主
  searchDogByBox(e) {
    console.log("box",e);
    var that = this;
    if (app.globalData.isTownAdmin) {
      var epidemicer = e.detail.val; //防疫员 
      if (!epidemicer) {
        that.setData({
          dogInfos: [],
          maxLength: 6,
          params: {
            pageNum: 1,
            pageSize: 6,
          },
          ["query.protector"]: ""
        });
      } else {
        that.setData({
          dogInfos: [],
          maxLength: 6,
          params: {
            pageNum: 1,
            pageSize: 6,
          },
          ["query.protector"]: epidemicer.id
        });
      }
      var query = that.data.query;
      that.getList(query);
    } else {
      var org = e.detail.val;
      that.setData({
        dogInfos: [],
        maxLength: 6,
        params: {
          pageNum: 1,
          pageSize: 6,
        },
        ["query.orgId"]: org.id
      });
      var query = that.data.query;
      that.getList(query);
    }
  },


  // 扫码搜索
  scanSearchDog:function(e){
    var that = this;
    that.setData({
      dogInfos: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6,
      },
      ["query.string"]: e.detail.val
    })
    var query = that.data.query;
    this.getList(query);
  },

  // 输入框搜索
  searchDog: function(e) {
    var that = this;
    that.setData({
      dogInfos: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6,
      },
      ["query.string"]: e.detail.val
    })
    var query = that.data.query;
    this.getList(query);
  },



  //获取列表
  getList(query, fun) {
    var that = this;
    console.log(query);
    var isTownAdmin =  app.isTownAdmin();
    var url = config.url.BASE_URL + "/dogInfo/wxfindAll";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.string != undefined) {
        params.string = query.string
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      } 
      if (isTownAdmin){
        if (query.type != undefined && query.antiepidemicDate != undefined ) {
          if (query.protector != undefined || query.areaId != undefined){
            url = config.url.BASE_URL + "/dogInfo/wxfindAllIsAnt";
            params.type = query.type;
            params.antiepidemicDate = query.antiepidemicDate;
            if (query.protector != undefined){
              params.protector = query.protector;
            } else if (query.areaId != undefined){
              params.areaId = query.areaId;
            }
          }
        } else if (query.protector != undefined){
          params.protector = query.protector;
        }
      }else {
        if (query.type != undefined && query.antiepidemicDate != undefined && query.areaId != undefined) {
          url = config.url.BASE_URL + "/dogInfo/wxfindAllIsAnt";
          params.type = query.type;
          params.antiepidemicDate = query.antiepidemicDate;
          params.areaId = query.areaId;
        }
      }
      
      
    }
    http.httpGet(url, token, params, function(res) {
      console.log(res);
      var data = res.data.list;
      for (var index in data) {
        if (data[index].createDate != '' && data[index].createDate != null) {
          data[index].createDate = dateFormat(data[index].createDate, "yyyy-MM-dd")
        }
        if (data[index].deviceNo && data[index].deviceNo != '') {
          data[index].isbind = true
        }
      }
      if (data.length > 0) {
        that.setData({
          dogInfos: that.data.dogInfos.concat(data),
          lastPage: res.data.lastPage
        })
      }
      typeof fun == "function" && fun(data)
    })
  },

  /**
   * type == 1 未防疫 
   * type == 2 以防疫
   */
  setNavigationBarTitle(type,date){
    if (type == 1){
      wx.setNavigationBarTitle({
        title: date+' 未防疫犬只',
      })
    } else if (type == 2) {
      wx.setNavigationBarTitle({
        title: date + ' 已防疫犬只',
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    var isTownAdmin = app.isTownAdmin();
    that.setData({
      dogInfos: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6
      },
      isLoad:true
    });
    var query = that.data.query;
    if (!query){
      query = {};
    }
    if (isTownAdmin){
      if (options.type && options.antiepidemicDate) {
        query.type = options.type;
        query.antiepidemicDate = options.antiepidemicDate;
        if (options.protectorId){
          query.protector = options.protectorId;
        } else if (options.areaId){
          query.areaId = options.areaId;
        }
        that.setNavigationBarTitle(options.type, options.antiepidemicDate);

      }
    }else {
      if (options.type && options.antiepidemicDate && options.areaId) {
        query.type = options.type;
        query.antiepidemicDate = options.antiepidemicDate;
        query.areaId = options.areaId;
        that.setNavigationBarTitle(options.type, options.antiepidemicDate);
      }
    }
    
    that.setData({
      query: query
    })
    that.getList(query, function (data) {
      if (!data || data.length == 0) {
        that.setData({
          ishasData: false
        })
        return;
      }
    });

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
    var that = this;
    if (that.data.isLoad) {
      that.setData({
        isLoad: false
      })
      return;
    }
    that.setData({
      dogInfos: [],
      maxLength: 6,
      params: {
        pageNum: 1,
        pageSize: 6
      }
    });
    var query = that.data.query;
    that.getList(query, function (data) {
      if (data && data.length > 0) {
        that.setData({
          ishasData: true
        })
        return;
      }
    });
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
    var that = this;
    var query = that.data.query;
    var pageNum = that.data.params.pageNum + 1;
    if (pageNum > that.data.lastPage) {
      return;
    }
    that.setData({
      maxLength: that.data.maxLength * 2,
      params: {
        pageNum: that.data.params.pageNum + 1,
        pageSize: 6
      }
    });
    this.getList(query);
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  /**
   * 跳转到犬只详情
   */
  toDogPage(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    console.log(that.data.dogInfos[index]);
    var traceId = that.data.dogInfos[index].traceId;
    console.log(traceId);
    wx.navigateTo({
      url: '/pages/admin/home/petInfo/petInfo?traceId=' + traceId,
    })
  },
  

  /**
   * 跳转到犬只添加
   */
  toDogAddPage() {
    wx.navigateTo({
      url: '/pages/home/dogInfo/add/dog_add',
    })
  },
})