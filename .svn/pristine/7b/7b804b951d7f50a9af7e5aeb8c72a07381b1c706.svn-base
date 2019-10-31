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
      placeholderText: "溯源号/犬主姓名",
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
    console.log(e);
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
          ["query.pro"]: ""
        });
      } else {
        that.setData({
          dogInfos: [],
          maxLength: 6,
          params: {
            pageNum: 1,
            pageSize: 6,
          },
          ["query.pro"]: epidemicer.id
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
      query: e.detail.val,
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
      query: e.detail.val,
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
    var url = config.url.BASE_URL + "/dogInfo/findAll";
    var token = app.globalData.token;
    var params = that.data.params;
    if (query != undefined) {
      if (query.string != undefined) {
        params.string = query.string
      }
      if (query.pro != undefined) {
        params.pro = query.pro
      }
      if (query.orgId != undefined) {
        params.orgId = query.orgId
      }
    }
    http.httpGet(url, token, params, function(res) {
      console.log(res);
      var data = res.data.list;
      for (var index in data) {
        if (data[index].createDate != '' && data[index].createDate != null) {
          data[index].createDate = dateFormat(data[index].createDate, "yyyy-MM-dd")
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
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
    this.getList();
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
    var deviceNo = that.data.dogInfos[index].deviceNo;
    console.log(deviceNo);
    wx.navigateTo({
      url: '/pages/admin/home/petInfo/petInfo?deviceNo=' + deviceNo,
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